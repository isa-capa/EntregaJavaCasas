package com.airtamarindo.controller;

import com.airtamarindo.model.Cliente;
import com.airtamarindo.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService service){ this.service = service; }

    @GetMapping public List<Cliente> all(){ return service.findAll(); }
    @GetMapping("/{id}") public Cliente byId(@PathVariable Long id){ return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente c){ return service.save(c); }
    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente c){ c.setId(id); return service.save(c); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ service.delete(id); }
}
