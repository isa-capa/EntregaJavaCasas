package com.airtamarindo.service;

import com.airtamarindo.model.*;
import com.airtamarindo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepo;
    private final ClienteRepository clienteRepo;
    private final ProductoRepository productoRepo;
    private final FacturaRepository facturaRepo;

    public ReservaService(ReservaRepository r, ClienteRepository c, ProductoRepository p, FacturaRepository f){
        this.reservaRepo = r; this.clienteRepo = c; this.productoRepo = p; this.facturaRepo = f;
    }

    public List<Reserva> findAll(){ return reservaRepo.findAll(); }
    public Reserva findById(Long id){ return reservaRepo.findById(id).orElseThrow(); }
    public void delete(Long id){ reservaRepo.deleteById(id); }

    @Transactional
    public Reserva crearReservaConFactura(Long clienteId, Set<Long> productoIds){
        Cliente cliente = clienteRepo.findById(clienteId).orElseThrow();
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setFecha(LocalDate.now());
        reserva.setProductos(productoRepo.findAllById(productoIds).stream().collect(Collectors.toSet()));
        reserva = reservaRepo.save(reserva);

        double total = reserva.getProductos().stream().mapToDouble(p->p.getPrecio()).sum();
        Factura factura = new Factura();
        factura.setFechaEmision(LocalDate.now());
        factura.setTotal(total);
        factura.setReserva(reserva);
        facturaRepo.save(factura);
        reserva.setFactura(factura);
        return reserva;
    }
}
