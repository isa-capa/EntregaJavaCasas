package com.airtamarindo.dto;

import java.util.List;

public class ComprobanteRequest {

    private ClienteRef cliente;
    private List<LineaRequest> lineas;

    public ClienteRef getCliente() { return cliente; }
    public void setCliente(ClienteRef cliente) { this.cliente = cliente; }

    public List<LineaRequest> getLineas() { return lineas; }
    public void setLineas(List<LineaRequest> lineas) { this.lineas = lineas; }

    public static class ClienteRef {
        private Long clienteid;

        public Long getClienteid() { return clienteid; }
        public void setClienteid(Long clienteid) { this.clienteid = clienteid; }
    }

    public static class LineaRequest {
        private Integer cantidad;
        private ProductoRef producto;

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

        public ProductoRef getProducto() { return producto; }
        public void setProducto(ProductoRef producto) { this.producto = producto; }
    }

    public static class ProductoRef {
        private Long productoid;

        public Long getProductoid() { return productoid; }
        public void setProductoid(Long productoid) { this.productoid = productoid; }
    }
}
