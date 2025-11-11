package com.airtamarindo.controller;

import com.airtamarindo.model.Producto;
import com.airtamarindo.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService service;
    public ProductoController(ProductoService service){ this.service = service; }

    @GetMapping public List<Producto> all(){ return service.findAll(); }
    @GetMapping("/{id}") public Producto byId(@PathVariable Long id){ return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@RequestBody Producto p){ return service.save(p); }
    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto p){ p.setId(id); return service.save(p); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ service.delete(id); }
}
