package crud.consultorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class mInventario {

    public void Insertar(String cadenaInventario) {
        try {
            FileWriter archivo = new FileWriter("listado_inventario.txt", true);
            BufferedWriter buffer = new BufferedWriter(archivo);
            buffer.write(cadenaInventario);
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public ArrayList<String> Consultar() {
        ArrayList<String> listaRegistros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("listado_inventario.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length >= 6) {
                    String datoBonito = "Codigo: " + datos[0] + " | Descripcion: " + datos[1] + " | Cantidad: " + datos[2] + " | Precio: " + datos[3] + " | Caducidad: " + datos[4] + " | Refri: " + datos[5];
                    listaRegistros.add(datoBonito);
                }
            }
        } catch (IOException e) {
            System.out.println("Mensaje de error: " + e.getMessage());
        }
        return listaRegistros;
    }

    public void update(String lineActual, String lineaNueva, String archivoOriginal) {
        java.io.File fileOriginal = new java.io.File(archivoOriginal);
        java.io.File fileTemporal = new java.io.File("temporal_inventario.txt");

        boolean actualizado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemporal))) {

            String lineaLeida;
            while ((lineaLeida = br.readLine()) != null) {
                String[] datosLeidos = lineaLeida.split("\\|");
                String[] datosActuales = lineActual.split("\\|");

                if (datosLeidos.length > 0 && datosActuales.length > 0
                        && datosLeidos[0].trim().equals(datosActuales[0].trim())) {
                    bw.write(lineaNueva);
                    bw.newLine();
                    actualizado = true;
                } else {
                    bw.write(lineaLeida);
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }

        if (actualizado) {
            if (fileOriginal.delete()) {
                fileTemporal.renameTo(fileOriginal);
                System.out.println("Registro Actualizado");
            } else {
                System.out.println("Error: No se pudo borrar el archivo");
            }
        } else {
            fileTemporal.delete();
            System.out.println("No se encontro el registro");
        }
    }

    public void delete(String lineActual, String archivoOriginal) {
        java.io.File fileOriginal = new java.io.File(archivoOriginal);
        java.io.File fileTemporal = new java.io.File("temporal_inventario.txt");

        boolean eliminado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemporal))) {

            String lineaLeida;
            while ((lineaLeida = br.readLine()) != null) {
                String[] datosLeidos = lineaLeida.split("\\|");
                String[] datosActuales = lineActual.split("\\|");

                if (datosLeidos.length > 0 && datosActuales.length > 0
                        && datosLeidos[0].trim().equals(datosActuales[0].trim())) {
                    eliminado = true;
                } else {
                    bw.write(lineaLeida);
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al borrar: " + e.getMessage());
        }

        if (eliminado) {
            if (fileOriginal.delete()) {
                fileTemporal.renameTo(fileOriginal);
                System.out.println("Registro Eliminado");
            } else {
                System.out.println("Error: No se pudo borrar el archivo");
            }
        } else {
            fileTemporal.delete();
            System.out.println("No se encontro el registro");
        }
    }
}
