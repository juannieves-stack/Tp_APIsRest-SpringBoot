package com.utn.productos_api.exception;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un producto.
 * Extiende de RuntimeException para evitar 'checked exceptions'.
 */
public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
