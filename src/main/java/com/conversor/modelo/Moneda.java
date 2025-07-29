// Paquete donde organizo clases de modelo (representación de datos)
package com.conversor.modelo;

import java.util.Map;

/**
 * Representa la respuesta de la API de ExchangeRate.
 * Utilizamos un 'record' de Java para una definición concisa de una clase de datos inmutable.
 *
 * @param base_code El código de la moneda base de la consulta.
 * @param conversion_rates Un mapa que contiene los códigos de moneda y sus tasas de cambio.
 */

public record Moneda(String base_code, Map<String, Double> conversion_rates) {
}
