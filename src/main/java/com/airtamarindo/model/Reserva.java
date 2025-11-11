package com.airtamarindo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "reserva_producto",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productos = new HashSet<>();

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private Factura factura;

    public Reserva(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public LocalDate getFecha(){ return fecha; }
    public void setFecha(LocalDate fecha){ this.fecha = fecha; }
    public Cliente getCliente(){ return cliente; }
    public void setCliente(Cliente cliente){ this.cliente = cliente; }
    public Set<Producto> getProductos(){ return productos; }
    public void setProductos(Set<Producto> productos){ this.productos = productos; }
    public Factura getFactura(){ return factura; }
    public void setFactura(Factura factura){ this.factura = factura; }
}
