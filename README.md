# API REST para Gestión de Productos — Trabajo Práctico (Programación III)

Este repositorio contiene una API REST completa para la gestión de productos, desarrollada como Trabajo Práctico Integrador de la materia Programación III (UTN).
<div align="left" style="display: flex; align-items: center; justify-content: flex-start; gap: 20px; border: 2px solid #e1e4e8; border-radius: 12px; padding: 15px 25px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); width: fit-content;">

  <a href="https://github.com/juannieves-stack" target="_blank">
    <img src="https://github.com/juannieves-stack.png" width="90" style="border-radius:50%; box-shadow:0 0 10px rgba(0,0,0,0.15);" alt="Juan Nieves"/>
  </a>

  <div align="left" style="line-height:1.6;">
    <h3 style="margin: 0 0 4px 0;">👤 <b>Juan Nieves</b></h3>
    <sub style="font-size:14px;">📘 Legajo: <b>45277</b></sub>
  </div>

</div>

---

## Resumen

La aplicación propone un servicio RESTful que permite crear, listar, actualizar y eliminar productos. Está organizada según la arquitectura en capas (controlador, servicio y repositorio), usa DTOs para separar la capa de transporte del modelo de persistencia y cuenta con validaciones, manejo global de errores y documentación con OpenAPI.

---

## 🛠 Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 3.x**
* **Spring Web:** Para la creación de controladores REST.
* **Spring Data JPA:** Para la persistencia de datos y operaciones CRUD.
* **H2 Database:** Base de datos en memoria para desarrollo y pruebas.
* **Validation:** Para validaciones de DTOs con anotaciones (`@Valid`).
* **Lombok:** Para reducir código repetitivo (getters, setters, etc.).
* **Springdoc-OpenAPI (Swagger):** Para la documentación interactiva de la API.
* **Maven:** Como gestor de dependencias y proyecto.

---

## Instalación y Ejecución

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/juannieves-stack/Tp-APIsRest-SpringBoot.git
    ```

2.  **Entrar al proyecto:**
    ```bash
    cd productos-api
    ```

3.  **Ejecutar la aplicación:**
    * **Desde un IDE (Recomendado):** Abrir el proyecto con IntelliJ IDEA o VS Code y ejecutar la clase principal `ProductosApiApplication.java`.
    * **Desde la terminal (usando Maven):**
        ```bash
        mvn spring-boot:run
        ```

---

## 🌐 Rutas útiles (cuando la app corre en localhost:8080)

Una vez que la aplicación esté en ejecución, puedes acceder a:

* **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
* **Consola H2 (Base de Datos):** `http://localhost:8080/h2-console`
    * **Importante:** Asegúrate de usar la URL JDBC correcta al conectar: `jdbc:h2:mem:productosdb`
    * **Usuario:** `sa`
    * **Contraseña:** `[la que hayas configurado en application.properties, o déjalo vacío si no pusiste]`

---

## Principales endpoints

| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/productos` | Obtener todos los productos. |
| `GET` | `/api/productos/{id}` | Obtener producto por ID. |
| `GET` | `/api/productos/categoria/{categoria}` | Filtrar por categoría. |
| `POST` | `/api/productos` | Crear un producto. |
| `PUT` | `/api/productos/{id}` | Reemplazar/actualizar un producto. |
| `PATCH` | `/api/productos/{id}/stock` | Actualizar únicamente el stock. |
| `DELETE` | `/api/productos/{id}` | Eliminar un producto por ID. |

---

## Validaciones y manejo de errores

Los DTOs poseen anotaciones de validación (por ejemplo, `@NotBlank`, `@Positive`). La aplicación cuenta con un manejador global de excepciones que transforma las excepciones en respuestas HTTP claras (400, 404, 500, etc.) y devuelve un cuerpo con detalles del error.

---

## 📸 Pruebas y capturas

En la carpeta `CAPTURAS/` se encuentran imágenes con ejemplos reales de uso y comprobaciones realizadas durante el desarrollo:

- `POST-exitoso.jpg` — Ejemplo de creación (201 Created)
- `GET-todos.jpg` — Listado de productos (200 OK)
- `ERROR-400.jpg` — Error de validación (400 Bad Request)
- `ERROR-404.jpg` — Recurso no encontrado (404 Not Found)
- `H2-tabla.jpg` — Vista de la tabla `PRODUCTO` en la consola H2
- `GET-categoria.jpg`, `PUT-producto.jpg`, `PATCH-stock.jpg`, `DELETE-producto.jpg` — Pruebas adicionales de endpoints

---

