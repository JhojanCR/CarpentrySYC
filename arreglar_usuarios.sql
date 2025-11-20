-- Script para arreglar usuarios existentes y actualizar contraseñas con BCrypt
-- Este script soluciona el problema de contraseñas sin encriptar

USE ecommerce_syc;

-- Paso 1: Eliminar todos los tokens de password reset para evitar conflictos de foreign key
DELETE FROM password_reset_tokens;

-- Paso 2: Actualizar las contraseñas de los usuarios existentes con hash BCrypt
-- La contraseña "admin" encriptada con BCrypt es: $2a$10$N.zmdr9k7uOCQvjhQJK/5.LPuC3LhGJPxk/.v6NNqtzs7qx3f8m2a

-- Actualizar usuario admin (id 1) con contraseña encriptada "admin"
UPDATE usuarios
SET password = '$2a$10$N.zmdr9k7uOCQvjhQJK/5.LPuC3LhGJPxk/.v6NNqtzs7qx3f8m2a',
    username = 'admin'
WHERE id = 1;

-- Actualizar usuario admin1 (id 6) con contraseña encriptada "admin"
UPDATE usuarios
SET password = '$2a$10$N.zmdr9k7uOCQvjhQJK/5.LPuC3LhGJPxk/.v6NNqtzs7qx3f8m2a'
WHERE id = 6;

-- Actualizar usuario prueba1 (id 3) con contraseña encriptada "123456"
-- Hash BCrypt de "123456" es: $2a$10$8qCkN6d8PFLbY.2PQHtpJ.NB9GZnx6xL5h9qr0vdGxF4xK7FHkQHa
UPDATE usuarios
SET password = '$2a$10$8qCkN6d8PFLbY.2PQHtpJ.NB9GZnx6xL5h9qr0vdGxF4xK7FHkQHa'
WHERE id = 3;

-- Paso 3: Verificar que los usuarios quedaron correctamente actualizados
SELECT id, nombre, username, email, rol,
       SUBSTRING(password, 1, 20) as password_hash_inicio
FROM usuarios
ORDER BY id;

-- Información de usuarios actualizados:
-- Usuario 1: username=admin, password=admin
-- Usuario 3: username=prueba1, password=123456
-- Usuario 6: username=admin1, password=admin
