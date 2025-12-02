DROP TABLE IF EXISTS comprobante_linea;
DROP TABLE IF EXISTS comprobante;
DROP TABLE IF EXISTS reserva_producto;
DROP TABLE IF EXISTS factura;
DROP TABLE IF EXISTS reserva;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
  id IDENTITY PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  email VARCHAR(160),
  telefono VARCHAR(40)
);

CREATE TABLE producto (
  id IDENTITY PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  precio DOUBLE,
  stock INT NOT NULL
);

CREATE TABLE reserva (
  id IDENTITY PRIMARY KEY,
  fecha DATE,
  cliente_id BIGINT NOT NULL,
  CONSTRAINT fk_reserva_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE factura (
  id IDENTITY PRIMARY KEY,
  fecha_emision DATE,
  total DOUBLE,
  reserva_id BIGINT UNIQUE,
  CONSTRAINT fk_factura_reserva FOREIGN KEY (reserva_id) REFERENCES reserva(id) ON DELETE CASCADE
);

CREATE TABLE reserva_producto (
  reserva_id BIGINT NOT NULL,
  producto_id BIGINT NOT NULL,
  PRIMARY KEY (reserva_id, producto_id),
  CONSTRAINT fk_rp_reserva FOREIGN KEY (reserva_id) REFERENCES reserva(id) ON DELETE CASCADE,
  CONSTRAINT fk_rp_producto FOREIGN KEY (producto_id) REFERENCES producto(id) ON DELETE RESTRICT
);

CREATE TABLE comprobante (
  id IDENTITY PRIMARY KEY,
  fecha TIMESTAMP,
  total DOUBLE,
  cantidad_total_productos INT,
  cliente_id BIGINT NOT NULL,
  CONSTRAINT fk_comprobante_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE comprobante_linea (
  id IDENTITY PRIMARY KEY,
  cantidad INT NOT NULL,
  precio_unitario DOUBLE,
  subtotal DOUBLE,
  producto_id BIGINT NOT NULL,
  comprobante_id BIGINT NOT NULL,
  CONSTRAINT fk_cl_producto FOREIGN KEY (producto_id) REFERENCES producto(id),
  CONSTRAINT fk_cl_comprobante FOREIGN KEY (comprobante_id) REFERENCES comprobante(id) ON DELETE CASCADE
);