<%-- 
    Document   : index
    Created on : 16/10/2023, 22:45:53
    Author     : Daniel
--%>
<%@page import ="modelo.Proveedores" %>
<%@page import ="modelo.Proveedores" %>
<%@page import ="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedores</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    </head>
    <body>
        <h1>Formulario Proveedores</h1>
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_proveedores" onclick="limpiar()">Nuevo</button>
        <div class="container">
        <div class="modal fade" id="modal_proveedores" role="dialog">
            <div class="modal-dialog">
        
        <div class="modal-content">
        <div class="modal-body">
            
            <form action="sr_proveedores" method="post" class="form-group">
                <label for="lbl_idproveedores"><b>IDproveedores</b></label>
                <input type="text" name="txt_idproveedores" id="txt_idproveedores" class="form-control" readonly>
                <label for="lbl_proveedor"><b>proveedor:</b></label>
                <input type="text" name="txt_proveedor" id="txt_proveedor" class="form-control" placeholder="Ejemplo: Nombre1 Nombre 2" required>
                <label for="lbl_nit"><b>nit</b></label>
                <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="Ejemplo: 4354452-6" required>
                <label for="lbl_direccion"><b>direccion:</b></label>
                <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: M=0 F=1" required>
                <label for="lbl_telefono"><b>Telefono:</b></label>
                <input type="text" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 46758987" required>
                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-outline-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-outline-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-outline-danger" onclick="javascript:if(!confirm('Desea Eliminar?'))return false">Eliminar</button>
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Cerrar</button>
            </form>
                </div>
              </div>

            </div>
        </div>            
            
            <br>
            
                <table class="table table-dark table-hover" >
                    <thead>
                      <tr>
                        <th>proveedor</th>
                        <th>nit</th>
                        <th>direccion</th>
                        <th>telefono</th>
                        
                      </tr>
                    </thead>
                    <tbody id="tbl_proveedores">
                        <%
                            Proveedores Proveedores = new Proveedores();
                            DefaultTableModel tabla = new DefaultTableModel();
                            tabla = proveedores.leer();
                            for(int j=0;j<tabla.getRowCount();j++){
                                out.println("<tr data-id="+ tabla.getValueAt(j,0)+">");
                                    out.println("<td>"+ tabla.getValueAt(j,1)+"</td>");
                                    out.println("<td>"+ tabla.getValueAt(j,2)+"</td>");
                                    out.println("<td>"+ tabla.getValueAt(j,3)+"</td>");
                                   
                                out.println("</tr>");
                            }
                        %>   
                        
                    </tbody>
                  </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
        <a href="proveedor.jsp">Hola mundo</a>
       
        
        <script type="text/javascript">
            function limpiar(){
              $("#txt_idcliente").val(0);
              $("#txt_nombres").val('');
              $("#txt_apellidos").val('');
              $("#txt_nit").val('');
              $("#txt_genero").val('');
              $("#txt_telefono").val('');
              $("#txt_correo").val('');
              $("#txt_fn").val('');
            }
            
            $('#tbl_clientes').on('click','tr td',function(evt){
              var target,id,nombres,apellidos,nit,genero,telefono,correo,ingreso;
              target = $(event.target);
              id = target.parent().data('id');
              nombres = target.parent("tr").find("td").eq(0).html();
              apellidos = target.parent("tr").find("td").eq(1).html();
              nit = target.parent("tr").find("td").eq(2).html();
              genero = target.parent("tr").find("td").eq(3).html();
              telefono = target.parent("tr").find("td").eq(4).html();
              correo = target.parent("tr").find("td").eq(5).html();
              ingreso = target.parent("tr").find("td").eq(6).html();
              var fechaOriginal = ingreso.toString();
              var partesFechaHora = fechaOriginal.split(" ");
              var fechaSinHora = partesFechaHora[0];
              var partesFecha = fechaSinHora.split("-");
              var fechaISO = partesFecha[0] + "-" + partesFecha[1] + "-" + partesFecha[2];
              $("#fechaInput").val(fechaISO);
              $("#txt_idcliente").val(id);
              $("#txt_nombres").val(nombres);
              $("#txt_apellidos").val(apellidos);
              $("#txt_nit").val(nit);
              $("#txt_genero").val(genero);
              $("#txt_telefono").val(telefono);
              $("#txt_correo").val(correo);
              $("#modal_clientes").modal('show');
          });
          
          </script>
        
        
        
        
        
        
        
    </body>
</html>
