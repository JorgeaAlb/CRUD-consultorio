package crud.consultorio;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class clsInventario {
    // atributos que necesito que tenga mi objeto
    private String codigo;
    private String descripcion;
    private int cantidad;
    private double precio;
    private String caducidad;
    private boolean refrigeracion;

    // constructor
    public clsInventario(String codigo, String descripcion, int cantidad, double precio, String caducidad, boolean refrigeracion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.caducidad = caducidad;
        this.refrigeracion = refrigeracion;
    }
    
    public clsInventario(){
    
    }
    
    // imprimir en consola los datos del inventario
    public String aTexto() {
        // Solo retornamos la cadena, no imprimimos aquí
        return this.codigo + "|" + this.descripcion + "|" + this.cantidad + "|" + this.precio + "|" + this.caducidad + "|" + this.refrigeracion;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }

    // guardar informacion
    public void guardar() {
        mInventario inventario = new mInventario();
        String textoInventario = this.aTexto(); // Obtenemos el texto una sola vez

        // Enviamos la cadena para guardar en el archivo
        inventario.Insertar(textoInventario);

        // Si quieres verlo en consola una sola vez:
        System.out.println(textoInventario);
    }
    
    public DefaultListModel<String> llenarLista(){
        mInventario MInventario = new mInventario();
        
        ArrayList<String> datos = MInventario.Consultar();
        
        DefaultListModel<String> modelLista = new DefaultListModel<>();
        
        for (String registro: datos){
            modelLista.addElement(registro);
        }
        
        return modelLista;
    }
    
    public void actualizar(String newCodigo, String newDescripcion, String newCantidad, String newPrecio, String newCaducidad, boolean newRefrigeracion){
        String nuevaLinea  = newCodigo + "|" + newDescripcion + "|" + newCantidad + "|" + newPrecio + "|" + newCaducidad + "|" + newRefrigeracion;
        
        String lineaOriginal = this.codigo + "|" + this.descripcion + "|" + this.cantidad + "|" + this.precio + "|" + this.caducidad + "|" + this.refrigeracion;
        
        //Imprimir los nuevos valores
        System.out.println("Nuevos valores" + nuevaLinea);
        System.out.println("Valores Originales:" + lineaOriginal);
        
        //Solicita la actualizacion del registro
        mInventario mInv = new mInventario();
        
        mInv.update(lineaOriginal, nuevaLinea, "listado_inventario.txt");
    }
    
    public void eliminar(){
        
        String lineaOriginal = this.codigo + "|" + this.descripcion + "|" + this.cantidad + "|" + this.precio + "|" + this.caducidad + "|" + this.refrigeracion;
        
        System.out.println("Valores Originales:" + lineaOriginal);
        
        //Solicita la aeliminacion del registro
        mInventario mInv = new mInventario();
        mInv.delete(lineaOriginal, "listado_inventario.txt");
    }
    
}
