<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="contenedor-tabs">
    <div class="" id="consulta_div">
        <!--<div class="row panel-group">
            <div class="col-md-12 ">
                <div class="pull-right">
                    <button class="btn btn-danger" id="btnCancelarViewConsulta">Cancelar</button>
                    <button class="btn btn-success" id="btnModificarConsulta">Modificar</button>
                </div>
            </div>
        </div>   -->    
        <div class="row ">
            <div class="col-md-3">
                <div class="form-group">
                    <label class="control-label" for="con_Fecha">Fecha</label>
                    <input class="form-control" validate="date" id="con_Fecha" size="16" type="text" value="" readonly>
                    <!--<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                        <input class="form-control" validate="date" id="con_Fecha" size="16" type="text" value="" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>-->
                </div>
            </div>

            <div class="col-md-4">
                <div class="form-group">
                    <label for="casoId" class="control-label">Paciente:</label>
                    <input type="text" class="form-control" data-hc="" readonly id="PacienteId">
                </div>
            </div>
            <div class="col-md-5">
                <div class="form-inline pull-right" style="margin-top: 29px;">
                    <button class="btn btn-danger" id="btnCancelarViewConsulta">Cancelar</button>
                    <button class="btn btn-success" id="btnModificarConsulta">Modificar</button>
                </div>
                
                <!--<button class="btn btn-success" data-toggle="modal" data-target="#signosVitales">Signos Vitales</button>-->
            </div>
            <div class="col-md-2">
                
                <!--<button class="btn btn-success" data-toggle="modal" data-target="#signosVitales">Signos Vitales</button>-->
            </div>
        </div>
        <div class="row">
            <div class="col-md-3">
                <div class="form-group">
                    <label for="con_Especialidad" class="control-label">Especialidad:</label>
                    <input type="text" class="form-control" data-hc="" readonly id="con_Especialidad">
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="con_Especialidad" class="control-label">Tipo Consulta:</label>
                    <input type="text" class="form-control" readonly id="con_tipoConsulta">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="con_CausaMetodo" class="control-label">Causa - Metodo:</label>
                    <input type="text" class="form-control" data-hc="" readonly id="con_CausaMetodo">
                </div>
            </div>
        </div>
        <div class="row">
            <!--<div class="col-md-6">
                <div class="form-group">
                    <label class="control-label">Motivo</label>
                    <textarea class="form-control" validate="text"  rows="3" id="con_Motivo"></textarea>
                </div>
                <div class="form-group">
                    <label class="control-label">Sintomas</label>
                    <textarea class="form-control" validate="text"  rows="3" id="con_Sintomas"></textarea>
                </div>
            </div>-->
            <div class="col-md-6">
                <div class="form-group">
                    <label class="control-label">Diagnostico</label>
                    <textarea class="form-control" validate="text"  rows="3" id="con_Diagnostico"></textarea>
                </div>

            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="control-label">Prescripciòn</label>
                    <textarea class="form-control" validate="text"  rows="3" id="con_Prescripcion"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label class="control-label">Observacion</label>
                    <textarea class="form-control" rows="3" id="con_Observacion"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <ul class="list-group" id="listEstudiosLab">
                    <a href="javascript:;" class="list-group-item active">Estudios Laboratorio</a>
                </ul>
            </div>
            <div class="col-md-6">
                <ul class="list-group" id="listEstudiosImg">
                    <a href="javascript:;" class="list-group-item active">Estudios de Imagenes</a>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="signosVitales" tabindex="-1" role="dialog" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <!--<button type="button" data-dismiss="modal"  class="btn btn-primary" style="float: right;">Cerrar</button>-->
                <h4 class="modal-title">Signos Vitales</h4>
            </div>
            <div class="modal-body" id="sv_id">
                <div class="form-group col-md-3">
                    <label class="form-control-label">Peso:</label>
                    <input validate="text" type="text" class="form-control "  placeholder="kg - lb" id="sv_Peso">
                </div>
                <div class="form-group col-md-3">
                    <label class="form-control-label">Talla:</label>
                    <input validate="text" type="text" class="form-control "  placeholder="cm" id="sv_Talla">
                </div>
                <div class="form-group col-md-3">
                    <label class="form-control-label">Temp.:</label>
                    <input validate="text" type="text" class="form-control "  placeholder="ºC" id="sv_Temperatura">
                </div>
                <div class="form-group col-md-3">
                    <label class="form-control-label">F. Cardìaca:</label>
                    <input validate="text" type="text" class="form-control "  placeholder="x Min." id="sv_Frecuencia">
                </div>
                <div class="form-group col-md-3">
                    <label class="form-control-label">Presiòn Arterial:</label>
                    <input validate="text" type="text" class="form-control "  placeholder="mmHg" id="sv_Presion">
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
            <div class="modal-footer">
                <button class="btn btn-primary" data-dismiss="modal">Guardar</button>
            </div>
        </div>
    </div>
</div>
<script src="consulta/js/styleViewConsulta.js" type="text/javascript"></script>