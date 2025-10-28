-- Script para resetear contraseñas de usuarios a texto plano
-- Ejecutar DESPUÉS de actualizar el código a NoOpPasswordEncoder

USE ecommerce_syc;

-- Limpiar tokens de password reset para evitar errores de foreign key
DELETE FROM password_reset_tokens;

-- Actualizar contraseñas de usuarios existentes a texto plano
-- Usuario ID 1: admin -> contraseña: admin
UPDATE usuarios SET password = 'admin' WHERE id = 1;

-- Usuario ID 3: prueba1 -> contraseña: 123456
UPDATE usuarios SET password = '123456' WHERE id = 3;

-- Usuario ID 6: admin1 -> contraseña: admin
UPDATE usuarios SET password = 'admin' WHERE id = 6;

-- Verificar las contraseñas actualizadas
SELECT id, username, password, rol, email, nombre FROM usuarios ORDER BY id;

-- NOTA: Ahora puedes iniciar sesión con:
-- Usuario: admin | Password: admin
-- Usuario: prueba1 | Password: 123456
-- Usuario: admin1 | Password: admin
