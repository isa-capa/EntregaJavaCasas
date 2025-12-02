package com.airtamarindo.controller;

import com.airtamarindo.dto.ComprobanteRequest;
import com.airtamarindo.dto.ComprobanteResponse;
import com.airtamarindo.service.ComprobanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    private final ComprobanteService comprobanteService;

    public ComprobanteController(ComprobanteService comprobanteService) {
        this.comprobanteService = comprobanteService;
    }

    @PostMapping
    public ResponseEntity<ComprobanteResponse> crear(@RequestBody ComprobanteRequest request) {
        ComprobanteResponse resp = comprobanteService.crearComprobante(request);
        if (resp.isExito()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } else {
            // devolvemos 400 con los errores de validación
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
