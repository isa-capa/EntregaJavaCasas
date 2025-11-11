
INSERT INTO cliente (nombre, email, telefono) VALUES
('Ana Torres','ana@example.com','555-111-2222'),
('Luis Pérez','luis@example.com','555-333-4444');

INSERT INTO producto (nombre, precio) VALUES
('Vuelo Tamarindo-PVR', 2990.00),
('Exceso de Equipaje', 150.00),
('Traslado Hotel-FBO', 120.00),
('Seguro de viaje', 80.00);

INSERT INTO reserva (fecha, cliente_id) VALUES (CURRENT_DATE, 1);
INSERT INTO reserva_producto (reserva_id, producto_id) VALUES (1,1),(1,3);
INSERT INTO factura (fecha_emision, total, reserva_id) VALUES (CURRENT_DATE, 3110.00, 1);
