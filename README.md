# CubillosSteven_pruebatec4
Prueba técnica de HACK A BOSS con Spring Boot

![creación de un proyecto en laravel](https://github.com/user-attachments/assets/31296e83-fa4b-4111-b879-eceb6d8f1e93)




# Nueva Agencia - API REST

Esta API REST está enfocada para una agencia de viajes que permite gestionar la búsqueda y reserva de hoteles y vuelos. Se ha usado Spring Boot, Java, y sigue las mejores prácticas de desarrollo, incluyendo el uso de Spring Security, JPA, Hibernate, y Swagger para la documentación de los endpoints.

## Requisitos Técnicos

- **Java 17**
- **Spring Boot 3.4.2 (Spring Security, Spring Data JPA)**
- **MySQL 8.0**
- **Maven**
- **Hibernate**
- **Swagger para documentación de API**
- **Postman para pruebas**

# Estructura del Repositorio #

![image](https://github.com/user-attachments/assets/c6e6ad20-9f85-484f-94ce-8d6b2900accc)




## Estructura del Proyecto

El proyecto está organizado en las siguientes capas:

- **Controladores**: Gestión de las solicitudes HTTP y devuelven las respuestas.
- **Servicios**: Contienen la lógica de negocio y se comunican con los repositorios.
- **Repositorios**: Interactúan con la base de datos para realizar operaciones CRUD.
- **Modelos**: Son las representaciones de las entidades de la base de datos..
- **DTOs**: Objetos de transferencia de datos usados para enviar y recibir información entre el cliente y el servidor.
- 

## Configuración del Proyecto

### Base de Datos

Dentro de la carpeta `resources` se encuentra el archivo SQL lista para importar:


- En caso de que no se haya exportado bien, puede crearla manualmente con `CREATE DATABASE nagencia;` .



### Postman

En la misma carpeta de `resources`. Se dispone de una colección de peticiones HTTP listo para usar con Postman con ejemplos.

### Configuración de la Aplicación

En el archivo `application.properties` está la configuración necesaria para conectar la aplicación con la base de datos. Asegúrate de ajustar las credenciales, asi como el usuario y contraseña de su BBDD.
```
# Base de datos
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/nagencia?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```
# Ejecución del Proyecto

Para descargar el proyecto, se recomienda usar Git para clonar
```
1. git clone https://github.com/rosmander/BlancoPerez_pruebatec4.git](https://github.com/StevenCubillos22/CubillosSteven_pruebatec4.git ANueva
cd ANueva
```
2. Ejecuta la aplicación utilizando Maven:

```
mvn clean install
mvn spring-boot:run

```
6. Accede a la documentación de la API con el uso de Swagger UI: `http://localhost:8080/doc/swagger-ui.html.`


# Endpoints

## Hoteles

- GET /hoteles: Obtiene un listado de todos los hoteles registrados.

- GET /hoteles/disponibles: Obtiene un listado de habitaciones de hoteles disponibles según las fechas y destino que le pase como parametro.

- POST /hoteles/nuevo: Realiza una reserva de habitación.

- DELETE /hoteles/borrar/{id}: Realiza una reserva de habitación.

- PUT /hoteles/actualizar/{id}: Crea un nuevo hotel (requiere autenticación).

- DELETE /reserva-hotel/borrar/{id}: Elimina un hotel (requiere autenticación).

- GET /reserva-hotel: Elimina un hotel (requiere autenticación).

- POST /reserva-hotel/crear: Elimina la reserva de hotel (requiere autenticación).
  

## Vuelos

-  PUT /vuelos/actualizar/{id}: Actualiza un vuelo existente (requiere autenticación).

-  POST /vuelos/nuevo: Crea un nuevo vuelo (requiere autenticación).

-  GET /vuelos: Obtiene un listado de todos los vuelos registrados. (No requiere autenticación)
-  GET /vuelos/disponibles:Obtiene un listado de vuelos disponibles en un rango de fechas y según origen y destino. (No requiere autenticación).
-  DELETE /vuelos/borrar/{id}: Elimina un vuelo (requiere autenticación).

-  POST /reserva-vuelo/nuevo: Realiza una nueva reserva de vuelo (requiere autenticación).
-  GET /reserva-vuelo: EObtiene el estado de una reserva de vuelo (requiere autenticación).
-  DELETE /reserva-vuelo/borrar/{id}: Elimina un vuelo (requiere autenticación).

## Personas

-  POST /personas/nuevo: Crea una nueva persona (requiere autenticación).
-  PUT /personas/actualizar/{id}: Actualiza la información de una persona existente (requiere autenticación).
-  GET /personas: Obtiene un listado de todas las personas registradas (No requiere autenticación).
-  DELETE /personas/borrar/{id}: Elimina una persona (requiere autenticación).


# Pruebas en Postman

Puedes hacer uso de la colección de Postman ubicada en la carpeta Postman para probar los endpoints de la API. Esta colección contiene ejemplos de solicitudes y respuestas para cada uno de los endpoints.

![image](https://github.com/user-attachments/assets/5ec0e7c7-75a4-43e1-839f-cbb29c89a926)


# Seguridad y Autenticación

-Es importante definir las credenciales para aquellos endpoints que lo usan en application.properties:

![image](https://github.com/user-attachments/assets/b44b9cd6-82a1-4648-b67b-2107b45cae57)

Y luego en Postman ingresar sus credenciales:

![image](https://github.com/user-attachments/assets/aa9ca893-ad2c-484e-9ab8-a996de4f95ef)


