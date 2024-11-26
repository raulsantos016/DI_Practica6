CREATE DATABASE tiendecitaRSR CHARSET utf8mb4 COLLATE utf8mb4_spanish2_ci;
USE tiendecitaRSR;
CREATE TABLE articulos (
	idArticulo INT AUTO_INCREMENT,
	descripcionArticulo VARCHAR(100),
	precioArticulo DECIMAL(8,2),
	stockArticulo INT,
	PRIMARY KEY (idArticulo)
);
CREATE TABLE tickets (
	idTicket INT AUTO_INCREMENT,
	fechaTicket DATE,
	precioTotalTicket DECIMAL(8,2),
	PRIMARY KEY (idTicket)
);
CREATE TABLE pertenecer (
	idPertenecer INT AUTO_INCREMENT,
	idArticuloFK INT,
	idTicketFK INT,
	cantidadArticulosTicket INT,
	PRIMARY KEY (idPertenecer),
    FOREIGN KEY(idArticuloFK)
    REFERENCES articulos(idArticulo),
    FOREIGN KEY(idTicketFK)
    REFERENCES tickets(idTicket)
);
SELECT * FROM articulos;
SELECT * FROM tickets;
SELECT * FROM pertenecer;