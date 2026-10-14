package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Aitortilla {
    public static void main(String[] args) {
        String archivoCSV = "MOCK_DATA_CON_CODIGO.csv";
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n--- MENÚ AIRTORTILLA ---");
            System.out.println("1. Generar todos los archivos (Modalidad Total)");
            System.out.println("2. Consultar reservas paso a paso (Línea a Línea)");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            if (entrada.hasNextInt()) {
                opcion = entrada.nextInt();
                entrada.nextLine();

                if (opcion == 1) {
                    generarArchivos(archivoCSV);
                } else if (opcion == 2) {
                    consultarPasoAPaso(archivoCSV, entrada);
                } else if (opcion == 3) {
                    System.out.println("Saliendo del programa...");
                } else {
                    System.out.println("Opción incorrecta, elige 1, 2 o 3.");
                }
            } else {
                System.out.println("Por favor, introduce un número válido.");
                entrada.next();
            }
        }

        entrada.close();
    }
    public static void generarArchivos(String archivoCSV) {
        int total = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 6) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    String destino = datos[2].trim();
                    String origen = datos[3].trim();
                    String codigoIso = datos[4].trim();
                    String fecha = datos[5].trim();

                    String localizador = codigoIso + "-" + id + "-" + nombre.replaceAll("\\s+", "");

                    String fechaConGuionesBajos = fecha.replace("-", "_");
                    String nombreArchivo = "AirTortilla_" + codigoIso + "_" + fechaConGuionesBajos + ".csv";

                    try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo, true))) {
                        escritor.println(id + "," + nombre + "," + origen + "," + destino + "," + localizador);
                    }

                    total++;
                }
            }
            System.out.println("Se han procesado " + total + " reservas correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer o guardar los datos: " + e.getMessage());
        }
    }

    public static void consultarPasoAPaso(String archivoCSV, Scanner entrada) {
        System.out.println("\n--- CONSULTA PASO A PASO ---");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            int contador = 1;

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 6) {
                    System.out.println("\nReserva #" + contador);
                    System.out.println("ID: " + datos[0].trim());
                    System.out.println("Pasajero: " + datos[1].trim());
                    System.out.println("Origen: " + datos[3].trim());
                    System.out.println("Destino: " + datos[2].trim() + " (" + datos[4].trim() + ")");
                    System.out.println("Fecha: " + datos[5].trim());

                    contador++;

                    System.out.print("\n[Pulsa ENTER para ver el siguiente o escribe 'fin' para salir]: ");
                    String respuesta = entrada.nextLine();

                    if (respuesta.equalsIgnoreCase("fin")) {
                        break;
                    }
                }
            }

            System.out.println("\nConsulta finalizada.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}