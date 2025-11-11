package com.airtamarindo.service;

import com.airtamarindo.model.Cliente;
import com.airtamarindo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repo;
    public ClienteService(ClienteRepository repo){ this.repo = repo; }
    public List<Cliente> findAll(){ return repo.findAll(); }
    public Cliente findById(Long id){ return repo.findById(id).orElseThrow(); }
    public Cliente save(Cliente c){ return repo.save(c); }
    public void delete(Long id){ repo.deleteById(id); }
}
