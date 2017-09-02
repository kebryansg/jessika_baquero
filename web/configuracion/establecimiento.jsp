<%-- 
    Document   : establecimiento
    Created on : 28-may-2017, 15:44:15
    Author     : Deivi
--%>

<%@page import="mvc.controlador.entidades.ip.Provincia"%>
<%@page import="java.util.List"%>
<%@page import="mvc.modelo.ipDao.ProvinciaDao"%>
<%@page import="mvc.modelo.ipDaoImp.ProvinciaDaoImp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="resources/bootstrap/css/fileinput.css" rel="stylesheet">
<link href="resources/bootstrap/css/fileinput.min.css" rel="stylesheet">
<script src="resources/bootstrap/js/fileinput.min.js" type="text/javascript" ></script>
<script src="resources/bootstrap/js/fileinput.js" type="text/javascript" ></script>
<script src="resources/bootstrap/js/fileinput/es.js" type="text/javascript" ></script>
<script src="configuracion/js/establecimiento.js" type="text/javascript" ></script>
<script>

</script>
<div class="container-fluid" id="tbEstablecimiento">
    <div class="form-horizontal">
        
    
        <div class="row ">                            
            <div class="col-md-12">
                <div class="pull-right">                                    
                    <button class="btn btn-info" id="btnCancelar">Cancelar</button>
                    <button class="btn btn-info" id="btnGuardar"  data-id="0">Guardar</button>
                </div>       
            </div>
        </div>
        <hr id="separador" />
        <div style="display:none;" id="msgAlert" class="alert alert-info">            
            <center><strong class="test">¡Información!</strong> Datos registrados correctamente.</center>
        </div>
        
        <form method="POST" id="establecimiento" name="establecimiento" enctype="multipart/form-data">
            
                   
        <div class="row">
           <div class="form-group col-md-6">
                <label class="control-label col-lg-2">Nombre</label>
                <div class="col-md-9">
                     <input validate="text" type="text" class="form-control" id="txtEstablecimiento" placeholder="Establecimiento">
                </div>
            </div>
            <div class="form-group col-md-6">
                <label  class="control-label col-lg-3">Encargado</label>
                <div class="col-md-9">
                     <input validate="text" type="text" class="form-control" id="txtEncargado" placeholder="Responsable">
                </div>
            </div>

        </div>
        

        <div class="row">
            <div class="form-group col-md-4">
                <label for="ejemplo_email_3" class="col-lg-3 control-label" name="nombre">Provincia</label>
                <div class="col-lg-9">
                    <select class="selectpicker" validate="select" data-width="100%" data-live-search="true" id="cboProvincia">
                    <option value="0">Seleccione</option>
                    <%
                        ProvinciaDao p = new ProvinciaDaoImp();
                        List<Provincia> ps = p.list();
                        for (Provincia provincia : ps) {%>
                        <option value="<%= provincia.getId()%>"><%= provincia.getDescripcion()%></option>  
                        <% }%>
                </select>
                </div>
            </div>
            <div class="form-group col-md-4">
                <label  for="ejemplo_email_3" class="col-lg-3 control-label">Cantón</label>
                <div class="col-lg-9">
                    <select class="selectpicker" validate="select" data-width="100%" data-live-search="true" id="cboCanton">
                     <option value="0">Seleccione</option>
                 </select>
                </div>
            </div>
            <div class="form-group col-md-4">
                <label  for="ejemplo_email_3" class="col-lg-3 control-label">Parroquia</label>
                <div class="col-lg-9">
                    <select class="selectpicker" validate="select" data-width="100%" data-live-search="true" id="cboParroquia">
                    <option value="0">Seleccione</option>
                </select>
                </div>
            </div>
        </div>
        <div class="row">
           <div class="form-group col-md-4">
                <label class="control-label col-lg-3">Dirección</label>
                <div class="col-md-9">
                     <input validate="text" type="text" class="form-control" id="txtDireccion" placeholder="Dirección">
                </div>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label col-lg-3">Telefono</label>
                <div class="col-md-9">
                     <input validate="text" type="text" class="form-control" id="txtTelefono" placeholder="Telefono">
                </div>
            </div>
            <div class="form-group col-md-4">
                <label class="control-label col-lg-3">Correo</label>
                <div class="col-md-9">
                     <input validate="email" type="text" class="form-control" id="txtEmail" placeholder="Correo">
                </div>
            </div>
        </div>            

         <div class="row">
           <div class="form-group col-md-12">
               <label class="control-label">&nbsp;&nbsp;&nbsp;Logo</label>
                <input id="input-es" name="inputes[]" type="file"  class="file-loading">
            </div>             

        </div>
                </form> 
    </div>
</div>
</div>

<script src="paciente/js/stylePaciente.js" type="text/javascript"></script>            
<script>
    $(".selectpicker").selectpicker().selectpicker("render");
    </script>