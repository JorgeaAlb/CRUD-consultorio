package crud.consultorio;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Joel Flores
 */
public class clsCitas {
    // atributos de la cita
    private String idCita;
    private String paciente;
    private String fecha;
    private String edad;
    private double costo;
    private boolean urgencia;

    // constructor con parametros
    public clsCitas(String idCita, String paciente, String fecha, String edad, double costo, boolean urgencia) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.fecha = fecha;
        this.edad = edad;
        this.costo = costo;
        this.urgencia = urgencia;
    }

    // constructor vacio
    public clsCitas() {
    }

    // convertir datos a texto con separador |
    public String aTexto() {
        return this.idCita + "|" + this.paciente + "|" + this.fecha + "|" + this.edad + "|" + this.costo + "|" + this.urgencia;
    }

    public String getPaciente() {
        return this.paciente;
    }

    // guardar cita en archivo
    public void guardar() {
        mCitas model = new mCitas();
        String textoCita = this.aTexto();
        model.Insertar(textoCita);
        System.out.println("Cita guardada: " + textoCita);
    }

    // llenar la lista con todas las citas
    public DefaultListModel<String> llenarLista() {
        mCitas model = new mCitas();
        ArrayList<String> datos = model.Consultar();
        DefaultListModel<String> modelLista = new DefaultListModel<>();

        for (String registro : datos) {
            modelLista.addElement(registro);
        }

        return modelLista;
    }

    // actualizar datos de una cita
    public void actualizar(String newId, String newPaciente, String newFecha, String newEdad, String newCosto, boolean newUrgencia) {
        String nuevaLinea = newId + "|" + newPaciente + "|" + newFecha + "|" + newEdad + "|" + newCosto + "|" + newUrgencia;

        String lineaOriginal = this.idCita + "|" + this.paciente + "|" + this.fecha + "|" + this.edad + "|" + this.costo + "|" + this.urgencia;

        //Imprimir los nuevos valores
        System.out.println("Nuevos valores: " + nuevaLinea);
        System.out.println("Valores Originales: " + lineaOriginal);

        //Solicita la actualizacion del registro
        mCitas mCit = new mCitas();
        mCit.Update(lineaOriginal, nuevaLinea, "listado_citas.txt");
    }

    // eliminar una cita
    public void eliminar() {
        String lineaOriginal = this.idCita + "|" + this.paciente + "|" + this.fecha + "|" + this.edad + "|" + this.costo + "|" + this.urgencia;

        System.out.println("Valores Originales: " + lineaOriginal);

        //Solicita la eliminacion del registro
        mCitas mCit = new mCitas();
        mCit.Delete(lineaOriginal, "listado_citas.txt");
    }
}
