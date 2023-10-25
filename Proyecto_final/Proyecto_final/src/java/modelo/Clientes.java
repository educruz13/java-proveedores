/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Daniel
 */
public class Clientes extends Persona {
    private String nit,correo,fn_ingreso;
    private int id;
    private Conexion cn;
    
    public Clientes(){}

    public Clientes(String nit, String correo, String fn_ingreso, int id) {
        this.nit = nit;
        this.correo = correo;
        this.fn_ingreso = fn_ingreso;
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFn_ingreso() {
        return fn_ingreso;
    }

    public void setFn_ingreso(String fn_ingreso) {
        this.fn_ingreso = fn_ingreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clientes(String nit, String correo, String fn_ingreso, int id, String nombres, String apellidos, String telefono, int genero) {
        super(nombres, apellidos, telefono, genero);
        this.nit = nit;
        this.correo = correo;
        this.fn_ingreso = fn_ingreso;
        this.id = id;
    }
    
   
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "Select idclientes as id, nombres, apellidos, nit, genero,telefono,correo_electronico,fecha_ingreso from clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Nombres","Apellidos","Nit","Genero","Telefono","Correo","Fecha_Ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [] = new String[8];
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("Nombres");
                datos[2] = consulta.getString("Apellidos");
                datos[3] = consulta.getString("Nit");
                datos[4] = consulta.getString("Genero");
                datos[5] = consulta.getString("Telefono");
                datos[6] = consulta.getString("Correo_Electronico");
                datos[7] = consulta.getString("Fecha_Ingreso");
                tabla.addRow(datos);
            }
                
            cn.cerrar_conexion();
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            
        }
        
        return tabla;
    }
   
    /**
     *
     * @return 
     */
@Override
    public int agregar(){
        int retorno = 0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "INSERT INTO clientes(nombres,apellidos,nit,genero,telefono,correo_electronico,fecha_ingreso) VALUES(?,?,?,?,?,?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getNit());
            parametro.setInt(4,getGenero());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo());
            parametro.setString(7,getFn_ingreso());
            retorno = parametro.executeUpdate();            
            System.out.println("Se inserto: "+ Integer.toString(retorno)+ "Registro");
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
    
    return retorno;
    
    }

    @Override
  public int modificar(){
      int executar=0;
      try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "update clientes set nombres=?,apellidos=?,nit=?,genero=?,telefono=?,correo_electronico=?,fecha_ingreso=? where idclientes=?";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getNit());
            parametro.setInt(4,getGenero());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo());
            parametro.setString(7,getFn_ingreso());
            parametro.setInt(8, getId());
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
            query = "delete from clientes where idclientes=?;";
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
        Clientes cl = new Clientes();
        
        cl.setId(2);
        cl.setNombres("Nicolas");
        cl.setApellidos("mendez");
        cl.setNit("635638");
        cl.setGenero(1);
        cl.setTelefono("6547746");
        cl.setCorreo("jhdfkahdsf");
        cl.setFn_ingreso("2023-09-09");
        int resultado = cl.eliminar();
//        DefaultTableModel tabla = new DefaultTableModel();
//        tabla = cl.leer();
        System.out.println("El resultado es"+resultado);
//        System.out.println("El resultado es"+tabla);
    }
    
       
}
