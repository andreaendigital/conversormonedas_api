package com.conversor;

import com.conversor.modelo.Moneda;
import com.conversor.servicio.ConsultaAPI;
import com.google.gson.Gson;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        // Inicializar herramientas que se necesitan
        Scanner teclado = new Scanner(System.in);
        ConsultaAPI consulta = new ConsultaAPI();
        Gson gson = new Gson();

        // Bucle principal de la aplicación
        while (true) {
            // Se muestra el menú de opciones
            mostrarMenu();

            // Se lee la opción del usuario
            var opcion = teclado.nextInt();

            // Si el usuario elige la opción para salir, se termina el bucle
            if (opcion == 7) {
                System.out.println("Gracias por usar el Conversor de Moneda. ¡Hasta pronto! 👋");
                break;
            }

            // Validamos que la opción esté en el rango correcto
            if (opcion < 1 || opcion > 6) {
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
                continue; // Vuelve al inicio del bucle
            }

            // Pedimos al usuario que ingrese el valor a convertir
            System.out.println("Ingrese el valor que desea convertir:");
            double valorAConvertir = teclado.nextDouble();

            // Definimos las monedas de origen y destino según la opción elegida
            String monedaOrigen = "";
            String monedaDestino = "";

            switch (opcion) {
                case 1:
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                    break;
                case 2:
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                    break;
                case 3:
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                    break;
                case 4:
                    monedaOrigen = "BRL";
                    monedaDestino = "USD";
                    break;
                case 5:
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                    break;
                case 6:
                    monedaOrigen = "COP";
                    monedaDestino = "USD";
                    break;
            }

            try {
                // 1. Se Realiza la consulta a la API para obtener las tasas basadas en la moneda de origen
                String jsonResponse = consulta.obtenerTasasDeCambio(monedaOrigen);

                // 2. Se usa  Gson para convertir la respuesta JSON en nuestro objeto Moneda
                Moneda moneda = gson.fromJson(jsonResponse, Moneda.class);

                // 3. Se obtiene la tasa de cambio específica que necesitamos del mapa
                Double tasaDeCambio = moneda.conversion_rates().get(monedaDestino);

                // 4. Se realiza el cálculo de la conversión
                double valorConvertido = valorAConvertir * tasaDeCambio;

                // 5. Mostramos el resultado al usuario de forma clara y formateada
                System.out.println("*************************************************");
                System.out.printf("El valor de %.2f [%s] corresponde al valor final de ==> %.2f [%s]%n",
                        valorAConvertir, monedaOrigen, valorConvertido, monedaDestino);
                System.out.println("*************************************************");

            } catch (RuntimeException e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                System.out.println("Finalizando la aplicación.");
                break; // Sale del bucle si hay un error grave en la API
            }
        }

        // Se cierra el scanner al final para liberar recursos
        teclado.close();
    }

    /**
     * Método para mostrar el menú de opciones al usuario.
     */
    public static void mostrarMenu() {
        System.out.println("\n*************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
        System.out.println("\n1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileño");
        System.out.println("4) Real brasileño =>> Dólar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Salir");
        System.out.println("\nElija una opción válida:");
        System.out.println("*************************************************");
    }
}
