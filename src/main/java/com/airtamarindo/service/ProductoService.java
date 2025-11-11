package com.airtamarindo.service;

import com.airtamarindo.model.Producto;
import com.airtamarindo.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repo;
    public ProductoService(ProductoRepository repo){ this.repo = repo; }
    public List<Producto> findAll(){ return repo.findAll(); }
    public Producto findById(Long id){ return repo.findById(id).orElseThrow(); }
    public Producto save(Producto p){ return repo.save(p); }
    public void delete(Long id){ repo.deleteById(id); }
}
