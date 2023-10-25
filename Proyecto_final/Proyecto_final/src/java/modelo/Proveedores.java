/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel
 */
public class Proveedores extends Persona {
    private String proveedor,nit,direccion;
    private int id;
    private Conexion cn; 

    public Proveedores (){}

    public Proveedores(String proveedor, String nit, String direccion, int id, Conexion cn) {
        this.proveedor = proveedor;
        this.nit = nit;
        this.direccion = direccion;
        this.id = id;
        this.cn = cn;
    }

    public Proveedores(String proveedor, String nit, String direccion, int id, Conexion cn, String nombres, String apellidos, String telefono, int genero) {
        super(nombres, apellidos, telefono, genero);
        this.proveedor = proveedor;
        this.nit = nit;
        this.direccion = direccion;
        this.id = id;
        this.cn = cn;
    }
   
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public Conexion getCn() {
        return cn;
    }

    public void setCn(Conexion cn) {
        this.cn = cn;
    }
    
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "Select idproveedores as id,proveedor,nit,direccion, telefono from proveedores;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Proveedor","Nit","Direccion","Telefono"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [] = new String[5];
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("proveedor");
                datos[2] = consulta.getString("nit");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                tabla.addRow(datos);
               System.out.println(  consulta.getString("proveedor"));
            }
                
            cn.cerrar_conexion();
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            
        }
        
        return tabla;
    }
    
    @Override
    public int agregar(){
        int retorno = 0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "INSERT INTO Proveedores(proveedor,nit,direccion,telefono) VALUES(?,?,?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getProveedor());
            parametro.setString(2,getNit());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
    
            retorno = parametro.executeUpdate();            
            System.out.println("Se inserto: "+ Integer.toString(retorno)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
    
    return retorno;
    
    }
    
    public int modificar(){
      int executar=0;
      try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "update proveedores set proveedor=?,nit=?,direccion=?,telefono=? where idproveedores=?";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getProveedor());
            parametro.setString(2,getNit());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setInt(5, getId());
            executar = parametro.executeUpdate();
            System.out.println("se Actualizo: "+ Integer.toString(executar)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return executar;
    }
    
    @Override
    public int eliminar(){
        int executar =0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "delete from proveedores where idproveedores=?;";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            executar = parametro.executeUpdate();
            System.out.println("se Actualizo: "+ Integer.toString(executar)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return executar;
}  
    
 
      public static void main(String [] args){
          
        System.out.println("Hola Loca");
        Proveedores cl = new Proveedores();
        
         
        cl.setId(5);
        cl.setProveedor("Cerveceria Guatemala");
        cl.setNit("2454621");
        cl.setDireccion("15av 9-54 zona 8");
        cl.setTelefono("58446306");
        
        System.out.println("El resultado es"+cl.eliminar());
    }
      
      
}
