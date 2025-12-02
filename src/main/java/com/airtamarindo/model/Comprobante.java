package com.airtamarindo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha/hora del comprobante (viene del servicio externo o del sistema)
    private LocalDateTime fecha;

    // Monto total de la venta
    private Double total;

    // Cantidad total de productos vendidos (suma de cantidades)
    private Integer cantidadTotalProductos;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComprobanteLinea> lineas = new ArrayList<>();

    public Comprobante() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Integer getCantidadTotalProductos() { return cantidadTotalProductos; }
    public void setCantidadTotalProductos(Integer cantidadTotalProductos) {
        this.cantidadTotalProductos = cantidadTotalProductos;
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ComprobanteLinea> getLineas() { return lineas; }
    public void setLineas(List<ComprobanteLinea> lineas) { this.lineas = lineas; }
}

