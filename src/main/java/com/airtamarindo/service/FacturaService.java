package com.airtamarindo.service;

import com.airtamarindo.model.Factura;
import com.airtamarindo.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {
    private final FacturaRepository repo;
    public FacturaService(FacturaRepository repo){ this.repo = repo; }
    public List<Factura> findAll(){ return repo.findAll(); }
    public Factura findById(Long id){ return repo.findById(id).orElseThrow(); }
    public Factura save(Factura f){ return repo.save(f); }
    public void delete(Long id){ repo.deleteById(id); }
}
