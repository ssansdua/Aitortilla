# Gestor de Reservas AirTortilla

Aplicación desarrollada en Java para la gestión, procesamiento y consulta de reservas de vuelos a partir de un archivo CSV (`MOCK_DATA_CON_CODIGO.csv`). El programa organiza la información de los pasajeros, genera localizadores únicos y exporta los datos divididos por destino y fecha de forma automática.

## Cómo funciona

El programa arranca con un menú interactivo en la consola que permite elegir entre dos modalidades principales:

1. **Generación masiva de archivos:**
   - Lee el CSV registro a registro.
   - Crea un localizador inequívoco para cada pasajero combinando su código ISO, su ID y su nombre.
   - Genera archivos de salida independientes (o los actualiza si ya existen) utilizando el formato obligatorio: `AirTortilla_XX_YYYY_MM_DD.csv` (donde `XX` es el código ISO del país y la fecha corresponde al día del vuelo).

2. **Consulta línea a línea:**
   - Diseñado para revisar las reservas de manera pausada y controlada.
   - Muestra en pantalla los datos detallados del pasajero actual y espera a que pulses **Enter** para pasar al siguiente registro, o que escribas `fin` para volver al menú principal.
     
## Tecnologías utilizadas

- **Java** (clases nativas para E/S de ficheros como `BufferedReader`, `PrintWriter`, `FileWriter` y `Scanner` para la interacción por consola).
- **CSV** para el almacenamiento estructurado de los datos de entrada y salida.
