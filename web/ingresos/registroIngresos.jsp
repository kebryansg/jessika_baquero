<%@page import="mvc.modelo.smDaoImp.EspecialidadEgresoDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.EspecialidadEgreso"%>
<%@page import="mvc.modelo.smDao.EspecialidadEgresoDao"%>
<%@page import="mvc.modelo.smDaoImp.EspecialidadDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.Especialidad"%>
<%@page import="java.util.List"%>
<%@page import="mvc.modelo.smDao.EspecialidadDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="contenedor-tabs" id="tabIngresos">
    <div class="container-fluid">
        <div class="row ">
            <div class="col-md-12">
                <div class="pull-right">                                    
                    <button class="btn btn-info" id="btnCancelar">Cancelar</button>
                    <button class="btn btn-info" id="btnGuardar"  data-id="0">Guardar</button>
                </div>       
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col-md-12">
                <label for="inputName" style="padding-top: 10px" class="control-label col-xs-1">&nbsp;&nbsp;&nbsp;Cédula</label>
                <div class="col-xs-2">
                    <div>
                        <input type="text" class="form-control" id="txtCedula" placeholder="Cédula">
                    </div>                     
                </div>
                <button id="btnCargar" type="button" class="btn btn-primary">Cargar</button>    
                <button id="btnBuscar"  type="button" class="btn btn-primary">Buscar</button> 
            </div>

            <div class="col-md-6">
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-12">                                    


                <div class="row">
                    <div class="form-group col-xs-4">
                        <div class="col-md-12">
                            <label class="col-md-12" for="txtPaciente">Paciente</label>
                            <div class="col-md-12">
                                <input id="txtPaciente" readonly="" type='text' class="form-control" />                                                            
                            </div>                                                        
                        </div>
                    </div>
                    <div class="form-group col-xs-4">
                        <div class="col-md-8">
                            <label class="col-md-12" for="cboCondicionEgreso">Condición al egreso</label>
                            <div class="col-md-12">
                                <select id="cboCondicionEgreso" class="selectpicker">
                                    <option value="1">Vivo</option>
                                    <option value="2">Fallecido - 48 horas</option>
                                    <option value="3">Fallecido + 48 horas</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-4">
                        <div class="col-md-8">
                            <label class="col-md-12" for="cboEspecialidadEgreso">Especialidad Egreso</label>
                            <div class="col-md-12">
                                <select id="cboEspecialidadEgreso" data-live-search="true" class="selectpicker">
                                    <%
                                        EspecialidadEgresoDao esp = new EspecialidadEgresoDaoImp();
                                        List<EspecialidadEgreso> list = esp.list();
                                        for (EspecialidadEgreso elem : list) {%>
                                    <option value="<%= elem.getId()%>"><%=elem.getDescripcion()%></option>
                                    <% }%>                                                            
                                </select>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="row">
                    <div class="form-group col-xs-3">
                        <div class="col-md-12">
                            <label class="col-md-12" for="dtpFechaIngreso">F. Ingreso</label>
                            <div class="col-md-12">
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                                    <input validate="date" class="form-control" id="dtpFechaIngreso" size="16" type="text" value="" readonly>
                                    <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-3">
                        <div class="col-md-12">
                            <label class="col-md-12" for="dtpFechaEgreso">F. Egreso</label>
                            <div class="col-md-12">
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                                    <input validate="date" class="form-control" id="dtpFechaEgreso" size="16" type="text" value="" readonly>
                                    <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-xs-3">
                        <div class="col-md-12">
                            <label class="col-md-12" for="dtpHoraIngreso">Hora Ingreso</label>
                            <div class="col-md-12">
                                <div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-format="hh:ii">
                                    <input validate="date" class="form-control" id="dtpHoraIngreso" size="16" type="text" value="" readonly>
                                    <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-time"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-xs-3">
                        <div class="col-md-12">
                            <label class="col-md-12" for="dtpHoraIngreso">Código CIE - 10 </label>
                            <div class="col-md-12">
                                <div class='input-group date' id='dtpHoraIngreso'>
                                    <input id='txtCodigoCie' type='text' class="form-control" />                                                                
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-xs-6">
                        <div class="col-md-12">
                            <label class="col-md-12" for="txtDefinitivoEgreso">Definitivo de Egreso</label>
                            <div class="col-md-12">
                                <textarea id="txtDefinitivoEgreso" rows="3" class="form-control" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-xs-6">
                        <div class="col-md-12">
                            <label class="col-md-12" for="txtSecundarioEgreso">1. Secundarios de Egreso</label>
                            <div class="col-md-12">
                                <textarea id="txtSecundarioEgreso" rows="3" class="form-control" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-xs-6">
                        <div class="col-md-12">
                            <label class="col-md-12" for="txtSecundarioEgreso2">2. Secundarios de Egreso</label>
                            <div class="col-md-12">
                                <textarea id="txtSecundarioEgreso2" rows="2" class="form-control" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-xs-6">
                        <div class="col-md-12">
                            <label class="col-md-12" for="txtCausaExterna">Causa Externa</label>
                            <div class="col-md-12">
                                <textarea id="txtCausaExterna" rows="2" class="form-control" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-10">

                    </div>                                       

                </div>
                </form>


            </div>




        </div>
    </div>     



    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">          
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Búsqueda de Pacientes</h4>
                </div>
                <div class="modal-body" >
                    <!--<div style="padding-left: 12px; padding-right: 12px " class="row">
                        <div class="col-lg-12">


                            <div class="row">
                                <div style="padding-top: 10px" class="col-xs-1 col-md-1">Registros:</div>

                                <div style=" padding: 2;" class="col-md-1 col-xs-1">
                                    <select class="selectpicker"  id="cboMostrar" data-width="60px">
                                        <option value="5">5</option>
                                        <option value="10">10</option>                                        
                                    </select>

                                </div>

                                <div class="col-xs-4 col-md-4">
                                    <input type="text" class="form-control" id="txtBuscar"  placeholder="Buscar">
                                </div>
                            </div>
                            <div class="row" style="padding-bottom: 1%; text-align: right;" >
                                <div class="col-xs-12 col-sm-12 col-md-12"> 
                                    <div class="col-xs-12 col-sm-12 col-md-3"> 

                                    </div>

                                </div>
                            </div>

                            <div class="table-responsive">

                                <table id="tablaPacientes" class="table table-bordered table-hover table-striped">
                                    <thead>

                                    </thead>
                                    <tbody >


                                    </tbody>
                                </table>
                            </div>

                            <div style="text-align: right; width: 100%;" id="paginacionBuscarIngresos">
                                <nav aria-label="Page navigation">
                                    <ul class="pagination" id="paginacionBuscarI"></ul>
                                </nav>
                            </div>
                        </div>
                    </div>-->   
                </div>
            </div>
        </div>
    </div>


</div>

<script src="ingresos/js/registroIngresos.js"></script>
<script src="resources/js/configuracionInicial.js" type="text/javascript" ></script>
