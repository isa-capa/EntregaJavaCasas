package com.airtamarindo.controller;

import com.airtamarindo.model.Reserva;
import com.airtamarindo.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService service;
    public ReservaController(ReservaService service){ this.service = service; }

    @GetMapping public List<Reserva> all(){ return service.findAll(); }
    @GetMapping("/{id}") public Reserva byId(@PathVariable Long id){ return service.findById(id); }

    public static record CrearReservaRequest(Long clienteId, Set<Long> productoIds){}

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Reserva crear(@RequestBody CrearReservaRequest req){
        return service.crearReservaConFactura(req.clienteId(), req.productoIds());
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ service.delete(id); }
}
