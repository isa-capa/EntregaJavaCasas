package com.airtamarindo.service;

import com.airtamarindo.dto.ComprobanteRequest;
import com.airtamarindo.dto.ComprobanteResponse;
import com.airtamarindo.model.*;
import com.airtamarindo.repository.ClienteRepository;
import com.airtamarindo.repository.ComprobanteRepository;
import com.airtamarindo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComprobanteService {

    private final ComprobanteRepository comprobanteRepo;
    private final ClienteRepository clienteRepo;
    private final ProductoRepository productoRepo;
    private final ExternalTimeService externalTimeService;

    public ComprobanteService(ComprobanteRepository comprobanteRepo,
                              ClienteRepository clienteRepo,
                              ProductoRepository productoRepo,
                              ExternalTimeService externalTimeService) {
        this.comprobanteRepo = comprobanteRepo;
        this.clienteRepo = clienteRepo;
        this.productoRepo = productoRepo;
        this.externalTimeService = externalTimeService;
    }

    @Transactional
    public ComprobanteResponse crearComprobante(ComprobanteRequest request) {
        ComprobanteResponse resp = new ComprobanteResponse();
        List<String> errores = new ArrayList<>();

        // 1) Validar cliente existente
        if (request.getCliente() == null || request.getCliente().getClienteid() == null) {
            errores.add("El cliente es obligatorio.");
        }

        Cliente cliente = null;
        if (errores.isEmpty()) {
            Long clienteId = request.getCliente().getClienteid();
            cliente = clienteRepo.findById(clienteId).orElse(null);
            if (cliente == null) {
                errores.add("El cliente con id " + clienteId + " no existe.");
            }
        }

        // 2) Validar lineas
        if (request.getLineas() == null || request.getLineas().isEmpty()) {
            errores.add("Debe indicar al menos una línea de producto.");
        }

        List<ComprobanteLinea> lineas = new ArrayList<>();
        double total = 0.0;
        int cantidadTotal = 0;

        if (errores.isEmpty()) {
            for (ComprobanteRequest.LineaRequest lineaReq : request.getLineas()) {
                if (lineaReq.getProducto() == null || lineaReq.getProducto().getProductoid() == null) {
                    errores.add("Cada línea debe incluir un productoid.");
                    continue;
                }
                Long productoId = lineaReq.getProducto().getProductoid();
                Producto producto = productoRepo.findById(productoId).orElse(null);
                if (producto == null) {
                    errores.add("El producto con id " + productoId + " no existe.");
                    continue;
                }

                Integer cantidad = lineaReq.getCantidad();
                if (cantidad == null || cantidad <= 0) {
                    errores.add("La cantidad debe ser mayor a cero para el producto id " + productoId + ".");
                    continue;
                }

                // Validación de stock
                if (producto.getStock() == null || producto.getStock() < cantidad) {
                    errores.add("Stock insuficiente para el producto id " + productoId +
                            ". solicitado=" + cantidad + ", disponible=" + producto.getStock());
                    continue;
                }

                // Si pasa todas las validaciones, armamos la línea del comprobante
                ComprobanteLinea linea = new ComprobanteLinea();
                linea.setProducto(producto);
                linea.setCantidad(cantidad);
                linea.setPrecioUnitario(producto.getPrecio()); // se congela el precio aquí
                linea.setSubtotal(producto.getPrecio() * cantidad);

                lineas.add(linea);

                total += linea.getSubtotal();
                cantidadTotal += cantidad;

                // Reducir el stock
                producto.setStock(producto.getStock() - cantidad);
                productoRepo.save(producto);
            }
        }

        if (!errores.isEmpty()) {
            resp.setExito(false);
            resp.setErrores(errores);
            resp.setMensaje("No se pudo crear el comprobante por errores de validación.");
            return resp;
        }

        // 3) Si todo es válido, creamos el comprobante
        Comprobante comprobante = new Comprobante();
        LocalDateTime fecha = externalTimeService.obtenerFechaActual();
        comprobante.setFecha(fecha);
        comprobante.setCliente(cliente);
        comprobante.setTotal(total);
        comprobante.setCantidadTotalProductos(cantidadTotal);

        for (ComprobanteLinea l : lineas) {
            l.setComprobante(comprobante);
        }
        comprobante.setLineas(lineas);

        comprobante = comprobanteRepo.save(comprobante);

        resp.setExito(true);
        resp.setMensaje("Comprobante creado correctamente.");
        resp.setComprobante(comprobante);
        return resp;
    }
}
