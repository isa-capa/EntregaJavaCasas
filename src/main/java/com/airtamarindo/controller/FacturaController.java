package com.airtamarindo.controller;

import com.airtamarindo.model.Factura;
import com.airtamarindo.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {
    private final FacturaService service;
    public FacturaController(FacturaService service){ this.service = service; }

    @GetMapping public List<Factura> all(){ return service.findAll(); }
    @GetMapping("/{id}") public Factura byId(@PathVariable Long id){ return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Factura create(@RequestBody Factura f){ return service.save(f); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ service.delete(id); }
}
