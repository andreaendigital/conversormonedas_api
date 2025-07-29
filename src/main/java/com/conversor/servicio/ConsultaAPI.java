// Paquete donde organizo clases de servicio
package com.conversor.servicio;

// Importaciones necesarias para hacer solicitudes HTTP y manejar errores
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Clase responsable de realizar la consulta a la API de tasas de cambio.
 */
public class ConsultaAPI {
    /**
     * Realiza una solicitud a la API para obtener las tasas de cambio para una moneda específica.
     *
     * @param codigoMoneda El código de la moneda base (ej: "USD", "BRL").
     * @return El cuerpo de la respuesta de la API en formato JSON (String).
     */
    public String obtenerTasasDeCambio(String codigoMoneda) {

        String apiKey = "AQUI_API_KEY"; // <-- ¡IMPORTANTE: Aquí se reemplaza con apiKey propia
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + codigoMoneda);

        // Creamos un cliente HTTP. Es como abrir un "navegador" para el programa.
        HttpClient client = HttpClient.newHttpClient();

        // Creamos una solicitud HTTP GET hacia la dirección de la API.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            // Se envía la solicitud y se obtiene una respuesta.
            // HttpResponse.BodyHandlers.ofString() le dice que se quiere la respuesta como un texto (String).
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // La respuesta de la API viene en el "cuerpo" (body) del mensaje. Se retorna.
            return response.body();
        } catch (IOException | InterruptedException e) {
            // Si ocurre un error de red o la conexión es interrumpida, lanzamos una excepción.
            throw new RuntimeException("Error al consultar la API: " + e.getMessage());
        }
    }
}
