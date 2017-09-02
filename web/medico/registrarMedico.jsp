<%-- 
    Document   : medico
    Created on : 24-mar-2017, 13:01:43
    Author     : Byron
--%>

<%@page import="mvc.modelo.smDaoImp.MedicoEspecialidadDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.MedicoEspecialidad"%>
<%@page import="mvc.modelo.smDao.MedicoEspecialidadDao"%>
<%@page import="mvc.controlador.entidades.sm.MedicoEspecialidad"%>
<%@page import="mvc.controlador.entidades.sm.Medico"%>
<%@page import="mvc.modelo.smDaoImp.MedicoDaoImp"%>
<%@page import="mvc.modelo.smDao.MedicoDao"%>
<%@page import="java.util.List"%>
<%@page import="mvc.modelo.smDaoImp.EspecialidadDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.Especialidad"%>
<%@page import="mvc.controlador.entidades.sm.Especialidad"%>
<%@page import="mvc.modelo.smDao.EspecialidadDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>





<!DOCTYPE html>

<div class="contenedor-tabs">
    <div class="container-fluid">
        <div class="row">
            <div class="form-horizontal" >
                    <div class="tab-content" id="tabMedicoRegistro">
                        <div class="row ">
                            <div class="col-md-12">
                                <div class="pull-right">
                                    <button class="btn btn-info" id="btnCancelar">Cancelar</button>
                                    <button class="btn btn-info" id="btnGuardar" data-id="0">Guardar</button>
                                </div>       
                            </div>
                        </div>
                         
                    
                <hr/>
        <div class="row">
            <div class="form-horizontal" >
                <div class="row" style="padding-bottom: 12px">  
                    <!-- Primera columna -->
                    <div class="col-md-5">
                            <div class="form-group">
                                <label for="inputUserName" class="control-label col-md-4">Cédula *</label>
                                <div class="col-md-8">
                                    <input  onblur="validarCedula()" type="text" class="form-control" validate="text" id="txtCedula" tabindex="1"  maxlength="10" id="inputUserName" placeholder="Cedula">
                                </div>
                            </div>   
                        
                        <div class="form-group">                                  
                            <label for="inputUserName" class="control-label col-md-4">Primer Nombre</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="txtPrimerNombre" tabindex="3" validate="text" placeholder="Primer nombre">                                    
                            </div>
                        </div>
                        
                        <div class="form-group">                                  
                            <label for="inputUserName" class="control-label col-md-4">Primer Apellido</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="txtPrimerApellido" tabindex="5" validate="text" placeholder="Primer apellido">
                            </div>
                        </div>
                        
                        <div class="form-group">   
                            <label for="inputUserName" class="control-label col-md-4" >Ciudad</label>   
                            <div class="col-md-8">
                                <input type="text" class="form-control" maxlength="10" validate="text" tabindex="7" id="txtCiudad" placeholder="Ciudad">
                            </div>
                        </div>
                        
                        <div class="form-group">
                                <label for="inputUserName" class="control-label col-md-4">Teléf. Domicilio</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" validate="text" tabindex="9" maxlength="10" id="txtTelefonoDomicilio" placeholder="Teléf. Domicilio">
                                </div>
                        </div>
                    
                        <div class="form-group">
                            <label for="inputUserName" class="control-label col-md-4">Teléf. Movil</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" validate="text" tabindex="11" maxlength="10" id="txtTelefonoMovil" placeholder="Teléf. Movil">
                            </div>
                        </div>
                    
                        
                    </div>
                    
                    <!-- Fin Primera columna --> 
                    
                    <!-- Segunda columna -->
                    <div class="col-md-5">
                       <div class="form-group">
                            <label for="inputUserName" class="control-label col-md-4">Especialidad</label>
                            <div class="col-md-8">
                                <select class="selectpicker" validate="select" tabindex="2" data-live-search="true" id="cboEspecialidad" data-width="100%" multiple >
                                <%
                                    EspecialidadDao esp = new EspecialidadDaoImp();
                                    List<Especialidad> list = esp.list();
                                    for (Especialidad elem : list) {%>
                                <option value="<%= elem.getId()%>"><%=elem.getDescripcion()%></option>
                                <% }%>
                                </select>
                            </div>
                        </div> 
                        
                         <div class="form-group">
                            <label for="inputUserName" class="control-label col-md-4">Segundo Nombre</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" tabindex="4" validate="text" id="txtSegundoNombre" placeholder="Segundo nombre">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="inputUserName" class="control-label col-md-4">Segundo Apellido</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" validate="text" tabindex="6" id="txtSegundoApellido" placeholder="Segundo apellido">
                            </div>
                        </div>
                                
                        <div class="form-group">
                            <label for="inputUserName" class="control-label col-md-4">Domicilio</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" validate="text" tabindex="8" id="txtDomicilio" placeholder="Domicilio">
                            </div>
                        </div>
                                
                        <div class="form-group">
                            <label for="inputUserName" class="control-label col-md-4">Teléf. Oficina</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" validate="text" tabindex="10" maxlength="10" id="txtTelefonoOficina" placeholder="Teléf. Oficina">
                            </div>
                        </div>   
                        
                        <div class="form-group">
                            <label for="inputUserName" class="control-label col-md-4">Email</label>
                            <div class="col-md-8">
                                <input type="email" class="form-control" maxlength="50" validate="email" tabindex="12" id="txtEmail" placeholder="E-mail">
                            </div>
                        </div>  
                                
                                
                    </div>
                                <!--Fin de la segunda column-->
                    
                </div>
          </div>
        </div>
                                </div>
           

            </div>
                </div>
            </div>
        </div>
   

<script src="medico/js/registrarMedico.js" ></script>	