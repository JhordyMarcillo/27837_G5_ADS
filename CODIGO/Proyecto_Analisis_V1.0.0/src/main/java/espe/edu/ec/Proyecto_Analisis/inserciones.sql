
INSERT INTO usuarios (username, password, nombre_completo, rol)
VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnutj8iAt6aBECYn.2alDj8/Z9.cezQc9G', 'Administrador Principal', 'ADMIN');

INSERT INTO usuarios (username, password, nombre_completo, rol)
VALUES ('invitado', '$2a$10$N.zmdr9k7uOCQb376NoUnutj8iAt6aBECYn.2alDj8/Z9.cezQc9G', 'Usuario Invitado', 'USER');

-- Donación 1: Mes pasado
INSERT INTO donaciones (donante, monto, descripcion, fecha_donacion, fecha_registro)
VALUES ('Empresa Tech Solutions', 5000.00, 'Aporte anual corporativo', '2025-11-15', '2025-11-15');

-- Donación 2: Este mes (reciente)
INSERT INTO donaciones (donante, monto, descripcion, fecha_donacion, fecha_registro)
VALUES ('Juan Pérez', 150.50, 'Donación voluntaria', '2025-12-01', '2025-12-01');

-- Donación 3: Este mes
INSERT INTO donaciones (donante, monto, descripcion, fecha_donacion, fecha_registro)
VALUES ('María García', 300.00, 'Campaña navideña', '2025-12-05', '2025-12-05');

-- Donación 4: Una fecha futura (para probar filtros)
INSERT INTO donaciones (donante, monto, descripcion, fecha_donacion, fecha_registro)
VALUES ('Fundación Global', 10000.00, 'Proyecto 2026', '2026-01-10', '2025-12-08');



-- Egreso 1: Mes pasado
INSERT INTO egresos (beneficiario, monto, descripcion, fecha_egreso, categoria)
VALUES ('Inmobiliaria Centro', 1200.00, 'Alquiler de oficinas Noviembre', '2025-11-30', 'ALQUILER');

-- Egreso 2: Este mes
INSERT INTO egresos (beneficiario, monto, descripcion, fecha_egreso, categoria)
VALUES ('Servicios Eléctricos S.A.', 245.80, 'Pago de luz Diciembre', '2025-12-02', 'SERVICIOS');

-- Egreso 3: Este mes
INSERT INTO egresos (beneficiario, monto, descripcion, fecha_egreso, categoria)
VALUES ('Proveedor de Papelería', 85.00, 'Insumos de oficina', '2025-12-06', 'SUMINISTROS');

-- Egreso 4: Mantenimiento
INSERT INTO egresos (beneficiario, monto, descripcion, fecha_egreso, categoria)
VALUES ('Técnico PC', 150.00, 'Reparación servidor', '2025-12-07', 'MANTENIMIENTO');