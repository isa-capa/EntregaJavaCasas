package com.airtamarindo.dto;

import com.airtamarindo.model.Comprobante;

import java.util.ArrayList;
import java.util.List;

public class ComprobanteResponse {

    private boolean exito;
    private String mensaje;
    private List<String> errores = new ArrayList<>();
    private Comprobante comprobante;

    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public List<String> getErrores() { return errores; }
    public void setErrores(List<String> errores) { this.errores = errores; }

    public Comprobante getComprobante() { return comprobante; }
    public void setComprobante(Comprobante comprobante) { this.comprobante = comprobante; }
}

