package com.utn.productos_api.service;

import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.exception.ProductoNotFoundException;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Inyección de dependencias por constructor
    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Implementación de los métodos

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        // findById devuelve un Optional. Usamos orElseThrow para lanzar nuestra
        // excepción personalizada si está vacío.
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con ID: " + id));
    }

    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public void eliminarProducto(Long id) {
        // Primero verificamos que exista. Si no, obtenerPorId lanzará la excepción 404.
        Producto producto = obtenerPorId(id);
        productoRepository.delete(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        // Reutilizamos obtenerPorId. Si no existe, lanzará 404.
        Producto productoExistente = obtenerPorId(id);

        // Actualizamos los campos
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(productoExistente);
    }

    public Producto actualizarStock(Long id, Integer nuevoStock) {
        // Reutilizamos obtenerPorId.
        Producto productoExistente = obtenerPorId(id);
        productoExistente.setStock(nuevoStock);
        return productoRepository.save(productoExistente);
    }


    /**
     * Convierte una Entidad Producto a un ProductoResponseDTO.
     */
    public ProductoResponseDTO convertirAProductoResponseDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }

    /**
     * Convierte un ProductoDTO (de creación) a una Entidad Producto.
     */
    public Producto convertirAProducto(ProductoDTO dto) {
        Producto producto = new Producto();
        // El ID se genera automáticamente, no se setea aquí
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }

    /**
     * Actualiza una entidad Producto existente con datos de un ProductoDTO.
     */
    public Producto actualizarEntidadDesdeDTO(Producto productoExistente, ProductoDTO dto) {
        productoExistente.setNombre(dto.getNombre());
        productoExistente.setDescripcion(dto.getDescripcion());
        productoExistente.setPrecio(dto.getPrecio());
        productoExistente.setStock(dto.getStock());
        productoExistente.setCategoria(dto.getCategoria());
        return productoExistente;
    }


}