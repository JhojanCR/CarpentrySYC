-- Script para crear usuario administrador
-- Username: admin
-- Password: admin
-- Rol: ADMIN

USE ecommerce_syc;

-- Eliminar usuario admin si existe (para evitar duplicados)
DELETE FROM usuarios WHERE username = 'admin';

-- Crear usuario admin
-- La contraseña 'admin' ya está encriptada con BCrypt
INSERT INTO usuarios (nombre, username, email, password, rol)
VALUES ('Administrador', 'admin', 'admin@syc.com', '$2a$10$N.zmdr9k7uOCQvjhQJK/5.LPuC3LhGJPxk/.v6NNqtzs7qx3f8m2a', 'ADMIN');

-- Verificar que se creó correctamente
SELECT id, nombre, username, email, rol FROM usuarios WHERE username = 'admin';
