package com.airtamarindo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Factura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaEmision;
    private Double total;

    @OneToOne
    @JoinColumn(name = "reserva_id", unique = true)
    private Reserva reserva;

    public Factura(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public LocalDate getFechaEmision(){ return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision){ this.fechaEmision = fechaEmision; }
    public Double getTotal(){ return total; }
    public void setTotal(Double total){ this.total = total; }
    public Reserva getReserva(){ return reserva; }
    public void setReserva(Reserva reserva){ this.reserva = reserva; }
}
