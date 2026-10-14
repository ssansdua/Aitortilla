package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class prueba {
    public static void main(String[] args) {
        String archivoCSV = "MOCK_DATA_CON_CODIGO.csv";
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("   INTERFAZ DE GESTIÓN DE AIRTORTILLA");
            System.out.println("========================================");
            System.out.println("1. Generar ficheros (Modalidad Total)");
            System.out.println("2. Consultar registros (Modalidad Línea a Línea)");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción (1-3): ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        generarArchivosTotal(archivoCSV);
                        break;
                    case 2:
                        consultarLineaALinea(archivoCSV, scanner);
                        break;
                    case 3:
                        System.out.println("¡Saliendo del programa. Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige entre 1 y 3.");
                }
            } else {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next(); // Limpiar entrada incorrecta
            }
        } while (opcion != 3);

        scanner.close();
    }

    // MODALIDAD TOTAL: Genera todos los ficheros de salida de golpe leyendo el CSV completo
    public static void generarArchivosTotal(String archivoCSV) {
        System.out.println("\n--- Procesando en Modalidad Total ---");
        String separador = ",";
        int totalProcesados = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(separador);
                if (datos.length >= 6) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    String destino = datos[2].trim();
                    String origen = datos[3].trim();
                    String codigoIso = datos[4].trim();
                    String fechaReserva = datos[5].trim();

                    String localizador = codigoIso + "-" + id + "-" + nombre.replaceAll("\\s+", "");
                    String nombreFichero = "AirTortilla_" + codigoIso + "_" + fechaReserva.replace("-", "_") + ".csv";

                    try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero, true))) {
                        pw.println(id + "," + nombre + "," + origen + "," + destino + "," + localizador);
                    }
                    totalProcesados++;
                }
            }
            System.out.println("¡Proceso total completado! Se han procesado " + totalProcesados + " registros correctamente.");

        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    // MODALIDAD LÍNEA A LÍNEA: Permite consultar el archivo registro a registro pausadamente
    public static void consultarLineaALinea(String archivoCSV, Scanner scanner) {
        System.out.println("\n--- Consulta en Modalidad Línea a Línea ---");
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            boolean primeraLinea = true;
            int contador = 1;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(separador);
                if (datos.length >= 6) {
                    System.out.println("\n[Registro #" + contador + "]");
                    System.out.println("ID: " + datos[0].trim());
                    System.out.println("Pasajero: " + datos[1].trim());
                    System.out.println("Destino: " + datos[2].trim() + " (ISO: " + datos[4].trim() + ")");
                    System.out.println("Origen: " + datos[3].trim());
                    System.out.println("Fecha Reserva: " + datos[5].trim());

                    contador++;

                    System.out.print("\nPresiona [ENTER] para ver el siguiente registro o escribe 'salir' para volver al menú: ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("salir")) {
                        break;
                    }
                }
            }
            System.out.println("\n--- Fin de la consulta línea a línea ---");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}