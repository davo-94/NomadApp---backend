# NomadApp---backend

# NomadApp Backend – Spring Boot

Backend de **NomadApp (Bitácora Nómada)**, una aplicación móvil Android para registrar publicaciones de viajes con texto e imágenes.

Este backend expone una **API REST** desarrollada en **Spring Boot**, encargada de la persistencia de publicaciones y la gestión de imágenes asociadas a cada post.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- H2 Database (en memoria)
- Maven
- Multipart File Upload
- REST API

---

## Funcionalidades principales

- Crear publicaciones (post)
- Listar publicaciones
- Editar publicaciones (texto)
- Eliminar publicaciones
- Subir imágenes asociadas a un post
- Servir imágenes vía HTTP

Cada publicación puede contener:
- Título
- Descripción
- Fecha
- Lista de URLs de imágenes

---

## Endpoints disponibles

### Crear post (sin imágenes)

POST /posts
Content-Type: application/json

{
  "title": "Mi viaje",
  "description": "Descripción del viaje",
  "date": "01/12/2025"
}

### Listar posts
GET /posts

### Editar post
PUT /posts/{id}

### Eliminar post
DELETE /posts/{id}

### Subir imágenes a un post
POST /posts/{id}/images
Content-Type: multipart/form-data

- Parámetro requerido: files
- Permite subir una o varias imágenes
- Las imágenes se almacenan en el servidor y se asocian al post
- El backend genera URLs públicas para cada imagen

###  Base de datos
- H2 en memoria
- No requiere configuración externa
- Ideal para desarrollo y demostración académica
- Al reiniciar la aplicación, los datos se reinician.

## Cómo ejecutar el proyecto
Requisitos
- Java 17
- Maven

### Pasos
mvn clean install
mvn spring-boot:run

La aplicación se levanta por defecto en:
http://localhost:8080

## Acceso a imágenes 
Las imágenes subidas quedan disponibles vía HTTP en rutas del tipo:
http://IP_DEL_SERVIDOR:8080/uploads/nombre_imagen.jpg
Estas URLs son consumidas directamente por la aplicación Android.

## Arquitectura

- Controlador REST (PostController)
- Servicio para lógica de negocio
- Persistencia con JPA
- Separación clara entre frontend móvil y backend
- El backend y el frontend Android se mantienen en repositorios independientes, permitiendo escalabilidad y despliegue separado.

## Mejoras futuras
- Persistencia en base de datos real (PostgreSQL / MySQL)
- Eliminación de imágenes individuales
- Autenticación y autorización
- Geolocalización persistente por publicación
- Deploy en servidor remoto
