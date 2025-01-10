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

INSERT INTO rol (nombre_rol, salario)
VALUES
    ('Gerente', 5000000),
    ('Supervisor', 3500000),
    ('Vendedor', 2000000),
    ('Cajero', 1800000),
    ('Auxiliar de Inventario', 1500000);

INSERT INTO empleado (apellido, cedula, direccion, email, estado, fecha_contratacion, horas_trabajadas, nombre, id_rol)
VALUES
    ('Gómez', '1020304050', 'Calle 123 #45-67', 'gomez@empresa.com', 1, '2023-01-15 08:30:00', 160, 'Carlos', 1),
    ('Pérez', '1020304051', 'Carrera 10 #20-30', 'perez@empresa.com', 1, '2023-02-01 09:00:00', 150, 'Andrea', 2),
    ('Rodríguez', '1020304052', 'Avenida 5 #10-15', 'rodriguez@empresa.com', 1, '2023-03-10 08:00:00', 140, 'Luis', 3),
    ('Martínez', '1020304053', 'Calle 50 #12-34', 'martinez@empresa.com', 1, '2023-04-20 07:45:00', 160, 'Ana', 4),
    ('Lopez', '1020304054', 'Carrera 15 #25-50', 'lopez@empresa.com', 1, '2023-05-05 08:15:00', 120, 'Diana', 5);


INSERT INTO usuario (contrasena, correo, estado, id_empleado)
VALUES
    ('password1', 'usuario1@correo.com', 1, 1),
    ('password2', 'usuario2@correo.com', 1, 2),
    ('password3', 'usuario3@correo.com', 1, 3),
    ('password4', 'usuario4@correo.com', 1, 4),
    ('password5', 'usuario5@correo.com', 1, 5);

INSERT INTO compra (cantidad_stock, estado, fecha_ingreso, precio, id_producto, id_proveedor, id_usuario)
VALUES
    (10.0, 1, '2024-08-01', 100000, 1, 1, 11),
    (20.0, 1, '2024-08-02', 160000, 2, 2, 12),
    (15.0, 1, '2024-08-03', 135000, 3, 3, 13),
    (8.0, 1, '2024-08-04', 96000, 4, 4, 14),
    (12.0, 1, '2024-08-05', 84000, 5, 5, 15);
