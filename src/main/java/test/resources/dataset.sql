INSERT INTO producto (codigo, estado, iva, nombre, precio_neto, precio_total, stock, tipo_producto, utilidad)
VALUES
    ('PROD001', 1, 19.0, 'Carne de Res', 10000, 11900, 50, 'RES', 19),
    ('PROD002', 1, 19.0, 'Carne de Pollo', 8000, 9520, 30, 'POLLO', 19),
    ('PROD003', 1, 19.0, 'Carne de Cerdo', 9000, 10710, 20, 'CERDO', 19),
    ('PROD004', 1, 19.0, 'Costilla de Res', 12000, 14280, 15, 'RES', 19),
    ('PROD005', 1, 19.0, 'Alas de Pollo', 7000, 8330, 40, 'POLLO', 19);

INSERT INTO proveedor (correo, direccion, estado, nombre, tipo_proveedor)
VALUES
    ('proveedor1@correo.com', 'Calle 123', 1, 'Proveedor Res', 'Carnes'),
    ('proveedor2@correo.com', 'Calle 456', 1, 'Proveedor Pollo', 'Aves'),
    ('proveedor3@correo.com', 'Calle 789', 1, 'Proveedor Cerdo', 'Carnes'),
    ('proveedor4@correo.com', 'Calle 101', 1, 'Proveedor Mixto', 'Mixto'),
    ('proveedor5@correo.com', 'Calle 202', 1, 'Proveedor Costillas', 'Carnes');

INSERT INTO usuario (contrasena, correo, estado, id_empleado)
VALUES
    ('password1', 'usuario1@correo.com', 1, 1),
    ('password2', 'usuario2@correo.com', 1, 2),
    ('password3', 'usuario3@correo.com', 1, 3),
    ('password4', 'usuario4@correo.com', 1, 4),
    ('password5', 'usuario5@correo.com', 1, 5);

INSERT INTO compra (cantidad_stock, estado, fecha_ingreso, precio, id_producto, id_proveedor, id_usuario)
VALUES
    (10.0, 1, '2024-08-01', 100000, 1, 1, 1),
    (20.0, 1, '2024-08-02', 160000, 2, 2, 2),
    (15.0, 1, '2024-08-03', 135000, 3, 3, 3),
    (8.0, 1, '2024-08-04', 96000, 4, 4, 4),
    (12.0, 1, '2024-08-05', 84000, 5, 5, 5);
