# Gestor de Reservas AirTortilla

Programa en Java para procesar un archivo CSV de reservas de vuelos, organizarlas y consultar la información de forma sencilla desde la consola.

## Funciones

El programa abre un menú con dos opciones principales:

1. **Generar archivos:** Lee el CSV entero, calcula un localizador único para cada pasajero y va creando o actualizando los archivos de salida separados por país y fecha (con el formato `AirTortilla_XX_YYYY_MM_DD.csv`).
2. **Consultar línea a línea:** Muestra las reservas una por una por pantalla. Tienes que pulsar Enter para ver la siguiente o escribir 'fin' si quieres salir de la consulta.

## Lo que usa

- Java básico (con `BufferedReader`, `PrintWriter` y `Scanner` para la consola).
- Archivos CSV para la entrada y salida de datos.
