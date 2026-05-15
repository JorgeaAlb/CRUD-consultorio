package crud.consultorio;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Joel Flores
 */
public class clsCliente {
    // atributos del cliente
    private String idCliente;
    private String nombre;
    private String telefono;
    private String correo;
    private String fechaNacimiento;
    private boolean tieneSeguro;

    // constructor con parametros
    public clsCliente(String idCliente, String nombre, String telefono, String correo, String fechaNacimiento, boolean tieneSeguro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.tieneSeguro = tieneSeguro;
    }

    // constructor vacio
    public clsCliente() {
    }

    // convertir datos a texto con separador |
    public String aTexto() {
        return this.idCliente + "|" + this.nombre + "|" + this.telefono + "|" + this.correo + "|" + this.fechaNacimiento + "|" + this.tieneSeguro;
    }

    public String getNombre() {
        return this.nombre;
    }

    // guardar cliente en archivo
    public void guardar() {
        mClientes model = new mClientes();
        String textoCliente = this.aTexto();
        model.Insertar(textoCliente);
        System.out.println("Cliente guardado: " + textoCliente);
    }

    // llenar la lista con todos los clientes
    public DefaultListModel<String> llenarLista() {
        mClientes model = new mClientes();
        ArrayList<String> datos = model.Consultar();
        DefaultListModel<String> modelLista = new DefaultListModel<>();

        for (String registro : datos) {
            modelLista.addElement(registro);
        }

        return modelLista;
    }

    // actualizar datos de un cliente
    public void actualizar(String newId, String newNombre, String newTelefono, String newCorreo, String newFechaNac, boolean newSeguro) {
        String nuevaLinea = newId + "|" + newNombre + "|" + newTelefono + "|" + newCorreo + "|" + newFechaNac + "|" + newSeguro;

        String lineaOriginal = this.idCliente + "|" + this.nombre + "|" + this.telefono + "|" + this.correo + "|" + this.fechaNacimiento + "|" + this.tieneSeguro;

        //Imprimir los nuevos valores
        System.out.println("Nuevos valores: " + nuevaLinea);
        System.out.println("Valores Originales: " + lineaOriginal);

        //Solicita la actualizacion del registro
        mClientes mCli = new mClientes();
        mCli.Update(lineaOriginal, nuevaLinea, "listado_clientes.txt");
    }

    // eliminar un cliente
    public void eliminar() {
        String lineaOriginal = this.idCliente + "|" + this.nombre + "|" + this.telefono + "|" + this.correo + "|" + this.fechaNacimiento + "|" + this.tieneSeguro;

        System.out.println("Valores Originales: " + lineaOriginal);

        //Solicita la eliminacion del registro
        mClientes mCli = new mClientes();
        mCli.Delete(lineaOriginal, "listado_clientes.txt");
    }
}
