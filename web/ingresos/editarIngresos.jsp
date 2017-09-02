
<%@page import="java.util.ArrayList"%>
<%@page import="mvc.controlador.entidades.sm.Ingresos"%>
<%@page import="mvc.modelo.smDaoImp.IngresosDaoImp"%>
<%@page import="mvc.modelo.smDao.IngresosDao"%>
<%@page import="mvc.modelo.smDaoImp.EspecialidadEgresoDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.EspecialidadEgreso"%>
<%@page import="mvc.modelo.smDao.EspecialidadEgresoDao"%>
<%@page import="mvc.modelo.smDaoImp.EspecialidadDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.Especialidad"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<script src="resources/js/jquery.twbsPagination.js" type="text/javascript"></script>-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js" async="async" ></script>


<br>
<div class="contenedor-tabs" id="tabMantenimientoIngresos">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-8">
                <div class="row">
                    <div class="col-md-12">
                        <label for="inputName" style="padding-top: 10px" class="control-label col-xs-1">Ingreso</label>
                        <div class="col-md-4">
                             <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-date="2013-02-21T15:25:00Z">
                                            <input validate="date" class="form-control" id="dtpFechaIngresoIngresos" size="16" type="text" value="" readonly>
                                            <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                             </div>
                        </div>
                        <label for="inputName" style="padding-top: 10px" class="control-label col-xs-1">Salida</label>
                        <div class="col-md-4">
                            <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                                            <input validate="date" class="form-control" id="dtpFechaEgresoIngresos" size="16" type="text"  readonly>
                                            <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                             </div>
                        </div>
                        <button id="btnBuscar" type="button" class="btn btn-primary">Buscar</button>   
                    </div>                    
                </div>
            </div>            
        </div>
        <span class="label label-default"></span>
        <br>
        
        <div class="row">
            <div class="col-xs-12">
            <div class="table-responsive" style="margin: 0 auto; text-align:left; height:315px; ">
            <table id="tablaIngresos"  class="table table-bordered table-hover table-striped">
                <thead>
                    <tr>                                                       
                        <th style='display:none;'>No.</th>
                        <th >Cédula</th>
                        <th>Apellidos y Nombres</th>
                        <th style='display:none;' >idTipoIngregos</th>
                        <th style='display:none;'>idCaso</th>
                        <th style='display:none;'>Id. Espegreso</th>
                        <th style='display:none;'>E. Egreso</th>
                        <th>F. Ingreso</th>
                        <th>F. Egreso</th>
                        <th style='display:none;'>Hora</th>
                        <th  style='display:none;'>Cond. Egreso</th>
                        <th>D. Egreso</th>
                        <th style='display:none;' class='col-lg-1'>S. Egreso</th>
                        <th style='display:none;' class='col-lg-1'>S. Egreso 2</th>
                        <th style='display:none;' class='col-lg-1'>C. Externa</th>
                        <th >Cód.</th>
                        <th style='display:none;' class='col-lg-1'></th>
                        <th class='col-lg-3'>Acción.</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                         IngresosDao ingresos= new IngresosDaoImp();
                         List<Ingresos> lista= ingresos.listIngresos(); 
                         String valor="btn-group";
                         int i=0;
                         String res="";
                         String []diagnosticos = new String[5];
                         for (Ingresos elem : lista)
                         {
                             diagnosticos[i]=elem.getDefinitivoEgreso();
                             if(diagnosticos[i].length()>=15)
                                 res=diagnosticos[i].substring(0, 15)+"... ";                                 
                             else
                             res = diagnosticos[i];
                             if(i==4)
                                 valor="btn-group dropup";
                             %>
                             <tr>
                                 <td style='display:none;'><%=elem.getId()%></td>
                                 <td><%=elem.getUnPaciente().getCedula()%></td>
                                 <td><%=elem.getUnPaciente().getApellido1()+" "+elem.getUnPaciente().getApellido2()+" "+elem.getUnPaciente().getNombre1()+ ' '+elem.getUnPaciente().getNombre1()%></td>
                                 <td style='display:none;'><%=elem.getIdTipoIngreso().getId()%></td>
                                 <td style='display:none;'><%=elem.getIdCaso().getId()%></td>
                                 <td style='display:none;'><%=elem.getIdEspecialidadEgreso().getId() %></td>
                                 <td style='display:none;'><%=elem.getIdEspecialidadEgreso().getDescripcion() %></td>
                                 <td><%=elem.getFechaEntrada() %></td>
                                 <td><%=elem.getFechaSalida()%></td>
                                 <td style='display:none;'><%=elem.getHora() %></td>
                                 <td style='display:none;'><%=elem.getCondicionEgreso()%></td>
                                 <td class='tooltips' id='tooltipDefinitivoEgreso' data-toggle='tooltip' data-container='body' data-placement='bottom' title="<%=elem.getDefinitivoEgreso() %>"><%=res%></td>
                                 <td style='display:none;'><%=elem.getSecundarioEgreso() %></td>
                                 <td style='display:none;'><%=elem.getSecundarioEgreso2() %></td>
                                 <td style='display:none;'><%=elem.getCausaExterna()%></td>
                                 <td ><%=elem.getCodigoDiagnosticoDefinitivo() %></td>
                                 <td style='display:none;'><%=elem.getDefinitivoEgreso() %></td>
                                 <td >
                                     <button id='botonEditar' class='btn btn-primary'><span class='glyphicon glyphicon-pencil'></span> </button> 
                                     <button id='btnEliminar' class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span></a></button>
                                     <div class="<%=valor%>">
                                         <button id='btnMedicinas' type='button' class='btn btn-warning dropdown-toggle' data-toggle='dropdown'>Medicinas <span class='caret'></span></button>
                                         <ul class='dropdown-menu' role='menu'>
                                             <li id='opAgregarMedicina'><a href='#'>Agregar</a></li>
                                             <li id='opMantenimientoMedicina'><a href='#'>Editar</a></li>
                                         </ul>
                                     </div>
                                 </td>
                             </tr>
                             
                             
                             <%
                                 i++;
                         }
                    %>


                </tbody>
            </table>
            </div>
        </div>
        </div>  
            
        <div style="text-align: right; width: 100%;" id="paginacionIngresosEditar">
                    <ul class="pagination" >
                
                    </ul>
         </div>
    </div>



<div class="modal fade" id="modalEditarIngresos" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">          
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Editar Ingresos</h4>
            </div>
            <div class="modal-body">
                <div class="tab-pane fade in active" id="ip" style="padding-top: 10px;">
                    <div class="row">
                        <div class="col-lg-12">                                    


                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <div class="col-md-12">
                                        <label class="col-md-12" for="txtPaciente">Paciente</label>
                                        <div class="col-md-12">
                                            <input id="txtPaciente" type='text' class="form-control" />                                                            
                                        </div>                                                        
                                    </div>
                                </div>
                                <div class="form-group col-xs-4">
                                    <div class="col-md-10">
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
                                    <div class="col-md-12">
                                        <label class="col-md-12" for="cboEspecialidadEgreso">Especialidad del egreso</label>
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
                                        <label class="col-md-12" for="dtpFechaIngresoModal">F. Ingreso</label>
                                        <div class="col-md-12">
                                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                                            <input validate="date" class="form-control" id="dtpFechaIngresoIngresosModal" size="16" type="text" value="" readonly>
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
                                        <input validate="date" class="form-control" id="dtpFechaEgresoIngresosModal" size="16" type="text" value="" readonly>
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
                                            <textarea id="txtDefinitivoEgreso" validate="text" rows="3" class="form-control" placeholder=""></textarea>
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
                                            <textarea id="txtSecundarioEgreso2" rows="3" class="form-control" placeholder=""></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group col-xs-6">
                                    <div class="col-md-12">
                                        <label class="col-md-12" for="txtCausaExterna">Causa Externa</label>
                                        <div class="col-md-12">
                                            <textarea id="txtCausaExterna" rows="3" class="form-control" placeholder=""></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-10">

                                </div>                                       
                                <div class="form-group col-xs-2">                                       
                                    <div class="col-md-2">
                                        <button id="btnActualizar" type="button"  class="btn btn-primary">Guardar</button>
                                    </div>
                                </div>
                            </div>
                            </form>


                        </div>




                    </div>
                </div> 







            </div>

        </div>
    </div>
</div>
                                            
                                            
<!--Modal medicinas -->
<div class="modal fade" id="medicinas" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">          
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Medicamentos</h4>
            </div>
            <div class="modal-body">
                <div class="tab-pane fade in active" id="ip" style="padding-top: 10px;">
                    <div class="row">
                        <div class="col-lg-12">                                    


                           <div class="row">
                                <div class="form-group col-xs-6">
                                    <div class="col-md-12">
                                        <label class="col-md-12" for="dtpFechaIngresoModal">Fecha</label>
                                        <div class="col-md-12">
                                                <div class="input-group date form_date" data-date="" id="dtpFechaMedicamento" data-date-format="yyyy-mm-dd">
                                            <input validate="date" class="form-control" id="dtpFechaMedicamentoIngresosModal" size="16" type="text" value="" readonly>
                                            <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group col-xs-6">
                                    <div class="col-md-12">
                                        <label class="col-md-12" for="dtpFechaEgreso">Hora</label>
                                        <div class="col-md-12">
                                            <div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-format="hh:ii">
                                              <input validate="date" class="form-control" id="dtpHora" size="16" type="text" value="" readonly>
                                            <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-time"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                               
                            </div>
                             <div class="row">
                                  <div class="form-group col-xs-12">
                                    <div class="col-md-12">
                                        <label class="col-md-12" for="dtpHoraIngreso">Notas de evolucion</label>
                                        <div class="col-md-12">
                                           <textarea id="txtNotasEvolucion" validate="text" rows="3" class="form-control" placeholder=""></textarea> 
                                        </div>
                                    </div>
                                </div>
                                
                             </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <div class="col-md-12">
                                        <label class="col-md-12" for="txtDefinitivoEgreso">Prescripción Médica</label>
                                        <div class="col-md-12">
                                            <textarea id="txtPrescripcion" validate="text" rows="3" class="form-control" placeholder=""></textarea>
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







            </div>
            <div class="modal-footer">
                <div class="form-group col-xs-2">                                       
                                    <div class="col-md-2">
                                        <button id="btnGuardarMedicamento" type="button"  class="btn btn-primary">Guardar</button>
                                    </div>
                                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="mantenimientoMedicina" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">          
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Mantenimiento Medicamentos</h4>
            </div>
            <div class="modal-body">
                <div id="sinRegistro"></div>
                <div class="tab-pane fade in active" id="ip" style="padding-top: 10px;">
                    <div class="row">
                        <div class="col-lg-12">                                    
                               <div >         
                                    <div class="table-responsive">
                                        <table id="tablaMedicamentos" class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                </tr>

                                            </thead>
                                            <tbody >


                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            <div style="text-align: right; width: 100%;" id="paginacionBuscarIngresos">
                            <nav aria-label="Page navigation">
                                    <ul class="pagination" id="paginacionBuscarI"></ul>
                             </nav>
                    </div>        

                            
                            <div class="row">
                                <div class="form-group col-xs-10">

                                </div>                                       
                                
                            </div>
                            </form>


                        </div>




                    </div>
                </div> 







            </div>
            <div class="modal-footer">
                
                        <button onclick="closeModal('mantenimientoMedicina')" type="button"  class="btn btn-primary">Cerrar</button>
                    
                </div>
            </div>
        </div>
    </div>
</div>
                                            
               
</div>
<script src="resources/js/configuracionInicial.js" type="text/javascript" ></script>
<script src="ingresos/js/editarIngresos.js" type="text/javascript" async="async"></script>