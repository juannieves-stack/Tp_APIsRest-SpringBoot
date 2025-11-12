package com.utn.productos_api.repository;

import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Método personalizado para buscar productos por su categoría.
     * Spring Data JPA entenderá el nombre del método y generará la consulta automáticamente.
     *
     */
    List<Producto> findByCategoria(Categoria categoria);
}
