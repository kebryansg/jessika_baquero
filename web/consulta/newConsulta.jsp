<%@page import="java.util.List"%>
<%@page import="mvc.modelo.smDaoImp.MedicoEspecialidadDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.MedicoEspecialidad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="contenedor-tabs">
    <div class="container-fluid" id="consulta_div">
        <!--<div class="row hidden">
            <div class="col-md-12">
                <div class="pull-right form-group">
                    <button class="btn btn-info" id="btnCancelarConsulta">Cancelar</button>
                    <button class="btn btn-info" id="btnGuardarConsulta">Guardar</button>
                </div>
            </div>
        </div>-->
        <div class="row">
            <div class="col-md-3">
                <div class="form-group">
                    <label for="casoId" class="control-label" >Nº Caso: </label>
                    <input type="text" class="form-control" readonly id="casoId" value="0">
                </div>
                <div class="form-group">
                    <label for="casoId" class="control-label">Paciente:</label>
                    <input type="text" class="form-control" data-hc="" readonly id="PacienteId">
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label class="control-label" for="con_Fecha">Fecha</label>
                    <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                        <input class="form-control" validate="date" id="con_Fecha" size="16" type="text" value="" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>    
                </div>
                <div class="form-group">
                    <label class="control-label" for="cboEspecialidadMedico">Especilidad</label>
                    <select class="form-control selectpicker" validate="select" id="cboEspecialidadMedico">
                        <option value="0">Seleccione</option>
                        <%
                            int id = Integer.parseInt(session.getAttribute("id").toString());
                            List<MedicoEspecialidad> list = new MedicoEspecialidadDaoImp().list(id);
                            for (MedicoEspecialidad m_e : list) {
                        %>
                        <option value="<%= m_e.getId()%>"><%= m_e.getIdEspecialidad().getDescripcion()%></option>
                        <%
                            }
                        %>  
                    </select>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label class="control-label" for="cboTipoConsulta">Tipo Consulta:</label>
                    <select class="form-control selectpicker"  validate="select" id="cboTipoConsulta">
                        <!--<option value="1" >Ambulatoria</option>
                        <option value="2">Prevenciòn</option>-->
                    </select>
                </div>
                <div class="form-group" id="groupCausa">
                    <label class="control-label" for="cboCausa">Causa:</label>
                    <select class="form-control selectpicker with-ajax" data-live-search="true" data validate="select" id="cboCausa">
                    </select>
                </div>
                <div class="form-group" id="groupMetodos">
                    <label class="control-label" for="cboMetodos">Metodos:</label>
                    <select class="form-control selectpicker" data-size="6" data validate="select" id="cboMetodos">
                    </select>
                </div>
            </div>
            <div class="col-md-3 hidden">
                <div class="form-group has-error" id="showLabs">
                    <button class="btn btn-danger" onclick="openModal('estLab')">Estudios Laboratorios.</button>
                    <span class="help-block" data-exonerado> 0 Estudios Lab.</span>
                </div>
                <div class="form-group has-error" id="showImgs">
                    <button class="btn btn-danger" data-toggle="modal" data-target="#estImg">Estudios Imagenes.</button>
                    <span class="help-block" data-exonerado>0 Estudios Img.</span>
                </div>
            </div>
            <div class="col-md-3">
                <div class="pull-right form-group">
                    <button class="btn btn-danger" id="btnCancelarConsulta">Cancelar</button>
                    <button class="btn btn-info" id="btnGuardarConsulta">Guardar</button>
                </div>
            </div>

        </div>
        <div class="row">
            <!--<div class="col-md-6">
                <div class="form-group">
                    <label class="control-label">Motivo</label>
                    <textarea class="form-control" validate="text" rows="2" id="con_Motivo"></textarea>
                </div>
                <div class="form-group">
                    <label class="control-label">Sintomas</label>
                    <textarea class="form-control" validate="text" rows="2" id="con_Sintomas"></textarea>
                </div>
            </div>-->
            <div class="col-md-6">
                <div class="form-group">
                    <label class="control-label">Diagnostico</label>
                    <textarea class="form-control" validate="text" rows="2" id="con_Diagnostico"></textarea>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="control-label">Prescripciòn</label>
                    <textarea class="form-control" validate="text" rows="2" id="con_Prescripcion"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label class="control-label">Observaciones:</label>
                    <textarea class="form-control" rows="2" id="con_Observacion"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="" id="sv_id"  data-id="0">
                <div class="form-group col-md-2">
                    <label class="form-control-label">Peso:</label>
                    <input  type="text" class="form-control " placeholder="kg - lb" id="sv_Peso">
                </div>
                <div class="form-group col-md-2">
                    <label class="form-control-label">Talla:</label>
                    <input  type="text" class="form-control " placeholder="cm" id="sv_Talla">
                </div>
                <div class="form-group col-md-2">
                    <label class="form-control-label">Temp.:</label>
                    <input  type="text" class="form-control " placeholder="ºC" id="sv_Temperatura">
                </div>
                <div class="form-group col-md-2">
                    <label class="form-control-label">F. Cardìaca:</label>
                    <input  type="text" class="form-control " placeholder="x Min." id="sv_Frecuencia">
                </div>
                <div class="form-group col-md-2">
                    <label class="form-control-label">Presiòn Arterial:</label>
                    <input  type="text" class="form-control " placeholder="mmHg" id="sv_Presion">
                </div>
                <div class="clearfix"></div>
                <div id="div_femenino">
                    <div class="form-group col-md-4">
                        <label class="form-control-label">FUM:</label>
                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                            <input class="form-control" validate="date" id="sv_FUM" size="16" type="text" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>  
                    </div>
                    <div class="form-group col-md-4">
                        <label class="form-control-label">FUC:</label>
                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                            <input class="form-control" validate="date" id="sv_FUC" size="16" type="text" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>  
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" for="sv_Periodo">Periodo:</label>
                        <select class="form-control selectpicker" validate="select" id="sv_Periodo">
                            <option value="0">Ninguna</option>
                            <option value="1">Prenatal</option>
                            <option value="2">Parto</option>
                            <option value="3">Post-parto</option>
                        </select>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="estLab" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <!--<button type="button" data-dismiss="modal"  class="btn btn-primary" style="float: right;">Guardar</button>-->
                <h4 class="modal-title">Estudios de Laboratorio</h4>
            </div>
            <div class="modal-body">

            </div>
            <!--<div class="modal-footer">

                <button id="btnActualizar" type="button"  class="btn btn-primary">Guardar</button>
            </div>-->
        </div>
    </div>
</div>         
<div class="modal fade " id="estImg" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-lg" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <!--<button type="button" data-dismiss="modal"  class="btn btn-primary" style="float: right;">Guardar</button>-->
                <h4 class="modal-title">Estudios de Imagenes</h4>
            </div>
            <div class="modal-body">
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="signosVitales" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
                <button type="button" data-dismiss="modal"  class="btn btn-primary" style="float: right;">Guardar</button>
                <h4 class="modal-title">Signos Vitales</h4>
            </div>
            <div class="modal-body" id="sv_id" data-id="0">
                <div class="form-group col-md-3">
                    <label class="form-control-label">Peso:</label>
                    <input  type="text" class="form-control " placeholder="kg - lb" id="sv_Peso">
                </div>
                <div class="form-group col-md-3">
                    <label class="form-control-label">Talla:</label>
                    <input  type="text" class="form-control " placeholder="cm" id="sv_Talla">
                </div>
                <div class="form-group col-md-3">
                    <label class="form-control-label">Temp.:</label>
                    <input  type="text" class="form-control " placeholder="ºC" id="sv_Temperatura">
                </div>
                <div class="form-group col-md-3">
                    <label class="form-control-label">F. Cardìaca:</label>
                    <input  type="text" class="form-control " placeholder="x Min." id="sv_Frecuencia">
                </div>
                <div class="form-group col-md-3">
                    <label class="form-control-label">Presiòn Arterial:</label>
                    <input  type="text" class="form-control " placeholder="mmHg" id="sv_Presion">
                </div>
                <div class="clearfix"></div>
                <div id="div_femenino">
                    <div class="form-group col-md-4">
                        <label class="form-control-label">FUM:</label>
                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                            <input class="form-control" validate="date" id="sv_FUM" size="16" type="text" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>  
                    </div>
                    <div class="form-group col-md-4">
                        <label class="form-control-label">FUC:</label>
                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                            <input class="form-control" validate="date" id="sv_FUC" size="16" type="text" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>  
                    </div>
                    <div class="form-group col-md-4">
                        <label class="control-label" for="sv_Periodo">Periodo:</label>
                        <select class="form-control selectpicker" validate="select" id="sv_Periodo">
                            <option value="0">Ninguna</option>
                            <option value="1">Prenatal</option>
                            <option value="2">Parto</option>
                            <option value="3">Post-parto</option>
                        </select>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>
<script src="resources/js/configuracionInicial.js" type="text/javascript"></script>
<script src="consulta/js/styleConsulta.js" type="text/javascript"></script>