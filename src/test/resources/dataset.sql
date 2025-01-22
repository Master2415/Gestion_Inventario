SET NAMES 'utf8mb4';

INSERT INTO producto (codigo, estado, iva, nombre, precio_neto, precio_total, stock, tipo_producto, utilidad)
VALUES
    ('CAR123-0', 0, 19.0, 'Bife de Res', 12000, 14280, 100, 'Res', 22),
    ('POL567-0', 1, 19.0, 'Pechuga de Pollo', 9500, 11305, 80, 'Pollo', 20),
    ('CER890-0', 1, 19.0, 'Lomo de Cerdo', 11000, 13090, 60, 'Cerdo', 21),
    ('COST112-0', 1, 19.0, 'Costillas de Res', 13000, 15470, 45, 'Res', 23),
    ('ALAS223-0', 1, 19.0, 'Alitas de Pollo', 8500, 10115, 90, 'Pollo', 18);

INSERT INTO proveedor (correo, direccion, estado, nombre, tipo_proveedor)
VALUES
    ('proveedor_a@carnes.com', 'Avenida Central 120', 1, 'Distribuciones Carnes Res', 'Carnes'),
    ('proveedor_b@aves.com', 'Calle 45 #32-10', 1, 'Proveedor de Aves S.A.S.', 'Aves'),
    ('proveedor_c@cerdo.com', 'Calle 12 #40-50', 1, 'Proveedor Lomo Cerdo', 'Carnes'),
    ('proveedor_d@mixto.com', 'Calle 90 #45-30', 1, 'Proveedor Mixto', 'Mixto'),
    ('proveedor_e@costillas.com', 'Calle 110 #10-20', 1, 'Costillas de la Abuela', 'Carnes');

INSERT INTO rol (nombre_rol, salario)
VALUES
    ('Director de Ventas', 6000000),
    ('Supervisor de Produccion', 4000000),
    ('Asesor Comercial', 2500000),
    ('Cajero Principal', 2000000),
    ('Almacen y Logistica', 1700000);

INSERT INTO empleado (apellido, cedula, direccion, email, estado, fecha_contratacion, horas_trabajadas, nombre, id_rol)
VALUES
    ('Gomez', '1012341278', 'Calle 70 #23-45', 'carlos.gomez@empresa.com', 1, '2023-01-10 09:00:00', 160, 'Carlos', 1),
    ('Perez', '1022345679', 'Calle 85 #10-30', 'andrea.perez@empresa.com', 1, '2023-02-01 08:30:00', 150, 'Andrea', 2),
    ('Rodriguez', '1032345680', 'Avenida 5 #15-25', 'luis.rodriguez@empresa.com', 1, '2023-03-05 08:00:00', 140, 'Luis', 3),
    ('Martinez', '1042345681', 'Calle 20 #30-50', 'ana.martinez@empresa.com', 1, '2023-04-15 07:45:00', 160, 'Ana', 4),
    ('Lopez', '1052345682', 'Carrera 30 #40-60', 'diana.lopez@empresa.com', 1, '2023-05-10 08:15:00', 120, 'Diana', 5);

INSERT INTO usuario (contrasena, correo, estado, id_empleado)
VALUES
    ('clave123', 'usuario1@empresa.com', 1, 1),
    ('clave234', 'usuario2@empresa.com', 1, 2),
    ('clave345', 'usuario3@empresa.com', 1, 3),
    ('clave456', 'usuario4@empresa.com', 1, 4),
    ('clave567', 'usuario5@empresa.com', 1, 5);

INSERT INTO compra (cantidad_stock, estado, fecha_ingreso, precio, id_producto, id_proveedor, id_usuario)
VALUES
    (50.0, 1, '2024-07-01', 120000, 1, 1, 1),
    (60.0, 1, '2024-07-02', 95000, 2, 2, 2),
    (45.0, 1, '2024-07-03', 110000, 3, 3, 3),
    (30.0, 1, '2024-07-04', 130000, 4, 4, 4),
    (40.0, 1, '2024-07-05', 85000, 5, 5, 5);
