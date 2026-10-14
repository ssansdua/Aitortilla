package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej1 {
    public class Persona {
        String nombre;
        String apellido;
        String DNI;

        public Persona(String nombre, String apellido, String DNI) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.DNI = DNI;
        }

        public void Reservar() {
            String contenido = "Reserva confirmada a nombre de: " + nombre + apellido;
            Path ruta = Path.of("reserva.txt");

            try {
                Files.writeString(ruta, contenido);
                System.out.println("Archivo creado correctamente.");
            } catch (IOException e) {
                System.out.println("Ocurrió un error al crear el archivo: " + e.getMessage());
            }
        }
    }
        public void main(String[] args) {
            Persona persona1 = new Persona("Sergio", "Sans", "49773921D");

            persona1.Reservar();
        }
    }




