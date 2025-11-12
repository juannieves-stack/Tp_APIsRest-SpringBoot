package com.utn.productos_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.service.ProductoService;
import jakarta.validation.Valid; // Importante para @Valid
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Combina @Controller y @ResponseBody
@RequestMapping("/api/productos") // Define la ruta base para todos los endpoints en esta clase.
@Tag(name = "Gestión de Productos", description = "Endpoints para crear, leer, actualizar y eliminar productos") //
public class ProductoController {

    private final ProductoService productoService;

    // Inyección de dependencias por constructor
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Endpoint para listar todos los productos.
     * GET /api/productos
     * Retorna List<ProductoResponseDTO>
     */
    @Operation(summary = "Listar todos los productos", description = "Retorna una lista de todos los productos en la base de datos.") //
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente") //
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        // Convertimos la lista de Entidades a una lista de DTOs de respuesta
        List<ProductoResponseDTO> dtos = productos.stream()
                .map(productoService::convertirAProductoResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Endpoint para obtener un producto por su ID.
     * GET /api/productos/{id}
     * Retorna ProductoResponseDTO
     */
    @Operation(summary = "Obtener producto por ID", description = "Retorna un producto específico buscando por su ID.")
    @ApiResponses(value = { //
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado") //
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        // El servicio lanza la excepción si no lo encuentra.
        // El @ControllerAdvice se encargará de la respuesta 404.
        Producto producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(productoService.convertirAProductoResponseDTO(producto));
    }

    /**
     * Endpoint para filtrar productos por categoría.
     * GET /api/productos/categoria/{categoria}
     * Retorna List<ProductoResponseDTO>
     */
    @Operation(summary = "Filtrar productos por categoría")
    @ApiResponse(responseCode = "200", description = "Lista de productos filtrada exitosamente")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> filtrarPorCategoria(@PathVariable Categoria categoria) {
        List<Producto> productos = productoService.obtenerPorCategoria(categoria);
        List<ProductoResponseDTO> dtos = productos.stream()
                .map(productoService::convertirAProductoResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Endpoint para crear un nuevo producto.
     * POST /api/productos
     * Recibe ProductoDTO validado
     * Retorna ProductoResponseDTO con código 201
     */
    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"), //
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (error de validación)") //
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        // Convertimos el DTO de entrada a una entidad
        Producto producto = productoService.convertirAProducto(productoDTO);
        Producto productoGuardado = productoService.crearProducto(producto);

        // Convertimos la entidad guardada a un DTO de respuesta
        ProductoResponseDTO responseDTO = productoService.convertirAProductoResponseDTO(productoGuardado);

        // Creamos la URI del nuevo recurso (buena práctica REST)
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productoGuardado.getId())
                .toUri();

        // Respondemos 201 Created, con la ubicación y el DTO de respuesta
        return ResponseEntity.created(location).body(responseDTO);
    }

    /**
     * Endpoint para actualizar un producto completo.
     * PUT /api/productos/{id}
     * Recibe ProductoDTO validado
     */
    @Operation(summary = "Actualizar un producto completo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO) {

        Producto productoActualizado = productoService.convertirAProducto(productoDTO);
        // El servicio lanza 404 si no existe
        Producto productoGuardado = productoService.actualizarProducto(id, productoActualizado);
        return ResponseEntity.ok(productoService.convertirAProductoResponseDTO(productoGuardado));
    }

    /**
     * Endpoint para actualizar solo el stock.
     * PATCH /api/productos/{id}/stock
     * Recibe ActualizarStockDTO validado
     */
    @Operation(summary = "Actualizar solo el stock de un producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej. stock negativo)"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarStockDTO stockDTO) {

        // El servicio lanza 404 si no existe
        Producto productoGuardado = productoService.actualizarStock(id, stockDTO.getStock());
        return ResponseEntity.ok(productoService.convertirAProductoResponseDTO(productoGuardado));
    }

    /**
     * Endpoint para eliminar un producto.
     * DELETE /api/productos/{id}
     * Retorna código 204 (No Content)
     */
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto de la base de datos por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        // El servicio lanza 404 si no existe
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}