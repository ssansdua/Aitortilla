package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BucleCarpetas {
    public static void main(String[] args) {
        String rutaArchivo = "lista.json";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            String id = "";
            String nombreFull = "";
            String paisDestino = "";
            String paisOrigen = "";

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.contains("\"id\":")) {
                    id = extraerValor(linea);
                } else if (linea.contains("\"nombreFull\":") || linea.contains("\"nombre_full\":") || linea.contains("\"nombre\":")) {
                    nombreFull = extraerValor(linea);
                } else if (linea.contains("\"paisDestino\":") || linea.contains("\"pais_destino\":")) {
                    paisDestino = extraerValor(linea);
                } else if (linea.contains("\"paisOrigen\":") || linea.contains("\"pais_origen\":")) {
                    paisOrigen = extraerValor(linea);
                }

                // Cuando cierra un objeto del JSON ("}" o "},") imprimimos y limpiamos variables
                if (linea.startsWith("}") && !id.isEmpty()) {
                    System.out.println("ID: " + id);
                    System.out.println("Nombre completo: " + nombreFull);
                    System.out.println("País de Origen: " + paisOrigen);
                    System.out.println("País de Destino: " + paisDestino);
                    System.out.println("-----------------------------------");

                    // Reset de variables para el siguiente elemento
                    id = "";
                    nombreFull = "";
                    paisDestino = "";
                    paisOrigen = "";
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método auxiliar para limpiar las comillas y comas del JSON
    private static String extraerValor(String linea) {
        String[] partes = linea.split(":", 2);
        if (partes.length < 2) return "";
        return partes[1].replace("\"", "").replace(",", "").trim();
    }
}