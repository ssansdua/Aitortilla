package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EjercicioDefinitivo {
    public static void main(String[] args) {
        String rutaArchivo = "MOCK_DATA.csv";
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(separador);

                if (datos.length >= 4) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    String direccion = datos[2].trim();
                    String origen = datos[3].trim();

                    System.out.println("ID: " + id);
                    System.out.println("Nombre completo: " + nombre );
                    System.out.println("Dirección: " + direccion);
                    System.out.println("Origen: " + origen);
                    System.out.println("-----------------------------------");
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}

