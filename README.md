# Conversor de Monedas consumiendo API - Java

Construir un conversor de monedas en Java que consuma una API.

## Descripción del Proyecto :scroll:

Para nuestro actual desafío, hemos elegido la API "Exchange Rate API" por sus tasas de cambio en tiempo real, proporcionando información precisa y actualizada para nuestras conversiones de moneda. Es gratuita.

Incluirá interacción con el usuario, implementando una interfaz textual a través de la consola que presenta opciones de conversión de monedas. La estructura incluirá un menú dentro de un bucle de repetición, permitiendo al usuario seleccionar opciones numéricas y proporcionar datos para la conversión, utilizando la clase Scanner para capturar la entrada do usuário.

Al final, el programa mostrará el valor convertido según la elección del usuario. 

<img width="500" height="500" alt="Badge-Conversor" src="https://github.com/user-attachments/assets/97faf88d-d4a7-4be1-8605-86cb6b683bf6" />



## Sobre el proyecto 🚀

### ✨ 🚀 Estrategias de implementación: 

Para este proyecto, te recomiendo usar Maven. Es el estándar moderno en el ecosistema Java y tiene dos grandes ventajas para nosotros:

Gestión de Dependencias: Nos permitirá agregar la biblioteca Gson (y cualquier otra que necesitemos) de forma automática, sin tener que descargar archivos .jar manualmente.

Estructura Estándar: Maven define una organización de carpetas clara y consistente, lo cual es una excelente práctica profesional.


HttpClient: Es el objeto que se encarga de enviar las solicitudes y recibir las respuestas. Lo creamos una vez y podemos reutilizarlo.

HttpRequest: Representa la solicitud que queremos hacer. Aquí especificamos la URL (la URI) a la que queremos acceder.

HttpResponse: Contiene la respuesta del servidor, incluyendo el código de estado (ej: 200 si todo fue exitoso) y el cuerpo de la respuesta, que en nuestro caso es el JSON con las tasas de cambio.

try-catch: La comunicación por red puede fallar. Este bloque nos permite manejar posibles errores (como no tener conexión a internet) de una manera controlada.

La API nos devuelve un texto en formato JSON. Para que sea fácil de usar en Java, vamos a "traducir" o "mapear" ese JSON a un objeto de Java.

record Moneda(...): Esto crea automáticamente una clase Moneda con los campos base_code y conversion_rates, junto con un constructor, getters, toString(), equals() y hashCode(). Es perfecto para representar datos.

Map<String, Double> conversion_rates: Le decimos a Java que conversion_rates es un mapa donde la clave es un String (el código de la moneda, ej: "ARS") y el valor es un Double (la tasa de cambio). Esto coincide exactamente con la estructura del JSON.

Flujo de Conversión (dentro del try):

Llamamos a consulta.obtenerTasasDeCambio() para obtener el JSON.

Usamos gson.fromJson() para convertir ese JSON en un objeto Moneda. ¡Aquí ocurre la magia del análisis!

Accedemos al mapa conversion_rates con .get(monedaDestino) para filtrar y obtener la tasa exacta que necesitamos.

Calculamos el valorConvertido.

Mostramos el resultado final con System.out.printf() para darle un formato numérico bonito.

try-catch: Si la ConsultaAPI lanza una RuntimeException (por ejemplo, si la API Key es inválida o no hay internet), este bloque la captura y muestra un mensaje amigable en lugar de crashear el programa.


## Visuales :mage_woman:

<img width="628" height="412" alt="Captura de pantalla 2025-07-28 211627" src="https://github.com/user-attachments/assets/3b192b07-a3a9-4acb-bf7c-16d0cc5cd231" />

<img width="597" height="368" alt="Captura de pantalla 2025-07-28 211647" src="https://github.com/user-attachments/assets/b34241be-dcd7-4029-8684-8920d9232c96" />

<img width="528" height="188" alt="Captura de pantalla 2025-07-28 211658" src="https://github.com/user-attachments/assets/625bdcb5-9d5f-49b4-9c1b-85dd608141df" />



## Autores ⚡ 

- **Andrea Rosero** ⚡  - [Andrea Rosero](https://github.com/andreaendigital)
