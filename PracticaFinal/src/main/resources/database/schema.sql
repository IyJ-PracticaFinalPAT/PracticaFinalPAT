DROP TABLE IF EXISTS CUSTOMER;

CREATE TABLE  "CLIENTE" (
    "DNI" VARCHAR2(20) NOT NULL PRIMARY KEY,
	"NOMBRE" VARCHAR2(40) NOT NULL,
    "APELLIDOS" VARCHAR2(80) NOT NULL, 
	"TELEFONO" NUMBER,
    "CORREO" VARCHAR2(100) NOT NULL
);