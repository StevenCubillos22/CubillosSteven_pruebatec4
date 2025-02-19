# CubillosSteven_pruebatec4
Prueba técnica de HACK A BOSS con Spring Boot




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

En el archivo `application.properties` está la configuración necesaria para conectar la aplicación con la base de datos. Asegúrate de que tenga estas credenciales.
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
1. git clone https://github.com/rosmander/BlancoPerez_pruebatec4.git
cd agencia-de-turismo
```
2. Asegúrate de tener MySQL instalado y en ejecución.

4. Configura las credenciales de la base de datos en el archivo `application.properties.`

5. Ejecuta la aplicación utilizando Maven:

```
mvn clean install
mvn spring-boot:run

```
6. Accede a la documentación de la API en Swagger UI: `http://localhost:8080/doc/swagger-ui.html.`


# Endpoints

## Hoteles

- GET /agency/hotels: Obtiene un listado de todos los hoteles registrados.

- GET /agency/rooms: Obtiene un listado de habitaciones disponibles en un rango de fechas y destino específico.

- POST /agency/room-booking/new: Realiza una reserva de habitación.

- POST /agency/hotels/new: Crea un nuevo hotel (requiere autenticación).

- PUT /agency/hotels/edit/{id}: Actualiza un hotel existente (requiere autenticación).

- DELETE /agency/hotels/delete/{id}: Elimina un hotel (requiere autenticación).

## Vuelos

-  GET /agency/flights: Obtiene un listado de todos los vuelos registrados.

-  GET /agency/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=ciudad1&destination=ciudad2: Obtiene un listado de vuelos disponibles en un rango de fechas y según origen y destino.

-  POST /agency/flight-booking/new: Realiza una reserva de vuelo.

-  POST /agency/flights/new: Crea un nuevo vuelo (requiere autenticación).

-  PUT /agency/flights/edit/{id}: Actualiza un vuelo existente (requiere autenticación).

-  DELETE /agency/flights/delete/{id}: Elimina un vuelo (requiere autenticación).

# Pruebas

Puedes utilizar la colección de Postman proporcionada en la carpeta `Postman` para probar los endpoints de la API. La colección incluye ejemplos de solicitudes y respuestas para cada endpoint.

# Seguridad y Autenticación

- Acceso libre a los endpoints de consulta de hoteles y vuelos.

- Autenticación requerida para realizar reservas o gestionar datos.
  
- Seguridad básica para proteger los endpoints sensibles.

# Contribuciones

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.

2. Crea una nueva rama para tu contribución.

3. Realiza tus cambios y asegúrate de que todas las pruebas pasen.

4. Envía un pull request con una descripción detallada de los cambios realizados.
