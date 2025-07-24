README - Proyecto GetaBreak (Gestión de Vacaciones)
---------------------------------------------------

Este archivo incluye información útil sobre el uso de herramientas externas durante el desarrollo, así como instrucciones para desplegar la aplicación en entorno local.

---------------------------------------------------
HERRAMIENTAS UTILIZADAS
---------------------------------------------------

POSTMAN
-------
Postman se ha utilizado para probar todos los endpoints de la API REST expuesta por el backend. Se han realizado pruebas de los métodos GET, POST, PUT y PATCH con diferentes escenarios, validando respuestas, errores y comportamientos esperados.

Recomendación:
Importar la colección de peticiones (si se incluye) o configurar las siguientes llamadas:
- POST /login
- POST /employee
- GET /vacations
- PATCH /vacations/{id}/status
...

VISUAL PARADIGM
---------------
Se ha utilizado Visual Paradigm para crear los diagramas de arquitectura, clases, y relaciones entre entidades. Las relaciones se han modelado con cardinalidad explícita y nombres descriptivos, respetando el modelo real de la aplicación.

---------------------------------------------------
INSTRUCCIONES PARA DESPLEGAR LA APLICACIÓN
---------------------------------------------------

Requisitos previos:
- Java 17 o superior instalado
- Node.js y Angular CLI instalados
- MongoDB instalado y ejecutándose localmente (por defecto en puerto 27017)
- IDE recomendado: Spring Tool Suite (STS) para backend, VS Code para frontend

1. BACKEND (Spring Boot):
-------------------------

- Abrir el proyecto con STS o cualquier IDE compatible.
- Asegurarse de tener las dependencias de Maven actualizadas.
- Configurar MongoDB si no se está utilizando el valor por defecto.
- Ejecutar la aplicación como proyecto Spring Boot.

La API estará disponible por defecto en:
http://localhost:8080

2. FRONTEND (Angular):
----------------------

- Acceder al directorio del frontend desde la terminal.
- Ejecutar `npm install` para instalar las dependencias.
- Ejecutar `ng serve` para iniciar la aplicación.

La interfaz estará disponible por defecto en:
http://localhost:4200

---------------------------------------------------
NOTA FINAL
---------------------------------------------------

La base de datos no se reinicia automáticamente. Los datos iniciales, como los departamentos, se cargan solo si no existen en MongoDB.

Para la carga masiva de empleados, se puede utilizar el endpoint `/employee/batch-upload` mediante Postman, enviando un archivo Excel en formato `multipart/form-data`.

Para cualquier duda, revisar la documentación funcional y técnica incluida en el proyecto.