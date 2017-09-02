<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel panel-group">
    <div class="row ">
        <div class="col-md-12">
            <div class="pull-left">
                <div class="form-inline">
                    <input class="form-control" style="width: 80px;" readonly id="pac_HC" placeholder="Cod. HC" >
                    <input class="form-control " style="width: 260px;" readonly id="pac_Nombres" placeholder="Apellidos y Nombres del Paciente" >  
                    <button class="btn btn-info " id="pac_Buscar" data-toggle="modal" data-target="#ListPaciente" ><i class="glyphicon glyphicon-search"></i> Buscar Paciente</button>    
                    <button class="btn btn-danger " id="pac_Delete" ><i class="glyphicon glyphicon-trash"></i></button>    
                </div>

            </div>
            <div class="pull-right">
                <button class="btn btn-success" id="run_consulta">Consultar</button>
            </div>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-2">
            <div class="form-group ">
                <label class="control-label" for="con_Fecha">Tipo Consulta:</label>
                <select class="selectpicker form-control" id="cboTipoConsulta">
                    
                </select>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group ">
                <label class="control-label" for="con_Fecha">Especialidad:</label>
                <select multiple data-selected-text-format="count" class="selectpicker form-control" multiple data-live-search="true" data-size="8" id="cboEspecialidad">
                    
                </select>
            </div>
        </div>
        <div class="col-md-2">
            <div class="form-group ">
                <label class="control-label" for="con_Fecha">Buscar por:</label>
                <select class="selectpicker form-control" id="opListConsultas">
                    <option value="0">Seleccione</option>
                    <option value="1">Fechas</option>
                    <optgroup label="Mes">
                        <option value="2">Mes</option>
                        <option value="3">Entre Meses</option>
                    </optgroup>
                    <optgroup label="Año">
                        <option value="4">Año</option>
                    </optgroup>
                </select>
            </div>
        </div>
        <div>
            <div id="fechas" class="hidden-event">
                <div class="form-group col-md-2">
                    <label class="control-label" for="con_Fecha">Fecha Inicio</label>
                    <div class="input-group date form_date fecha" data-date="" data-date-format="yyyy-mm-dd">
                        <input class="form-control" validate="date" id="con_FechaI" size="16" type="text" value="" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>    
                </div>
                <div class="form-group col-md-2">
                    <label class="control-label" for="con_Fecha">Fecha Final</label>
                    <div class="input-group date form_date fecha" data-date="" data-date-format="yyyy-mm-dd">
                        <input class="form-control" validate="date" id="con_FechaF" size="16" type="text" value="" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>    
                </div>
            </div>
            <div class="form-group col-md-2 hidden-event">
                <label class="control-label" for="con_Fecha">Mes</label>
                <div class="input-group date form_date mes" data-date="" data-date-format="yyyy-mm-dd">
                    <input class="form-control" validate="date" id="con_Mes" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>    
            </div>
            <div id="meses" class="hidden-event">
                <div class="form-group col-md-2">
                    <label class="control-label" for="con_Fecha">Mes Inicio</label>
                    <div class="input-group date form_date mes" data-date="" data-date-format="yyyy-mm-dd">
                        <input class="form-control" validate="date" id="con_MesI" size="16" type="text" value="" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>    
                </div>
                <div class="form-group col-md-2">
                    <label class="control-label" for="con_Fecha">Mes Final</label>
                    <div class="input-group date form_date mes" data-date="" data-date-format="yyyy-mm-dd">
                        <input class="form-control" validate="date" id="con_MesF" size="16" type="text" value="" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>    
                </div>
            </div>
            <div class="form-group col-md-2 hidden-event">
                <label class="control-label" for="con_Fecha">Año</label>
                <div class="input-group date form_date año" data-date="" data-date-format="yyyy-mm-dd">
                    <input class="form-control" validate="date" id="con_Año" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>    
            </div>
        </div>

    </div>

    <div class="clearfix"></div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="pull-left">
            <select class="selectpicker " data-width="80px" id="cantListConsultas">
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="30">30</option>
            </select>
        </div>
        <div class="pull-right">
            <input class="form-control" style="width: 150px;" placeholder="Buscar" id="txt_filterHistorialC">
        </div>
    </div>
</div>
<br>
<table id="tbConsultas" 
       data-toogle="table" data-height="300">
    <thead>
        <tr>
            <th data-field="id" >ID</th>
            <th data-field="fecha" >Fecha</th>
            <th data-field="paciente" >Paciente</th>
            <th data-field="tipoConsulta" >Tipo Consulta</th>
            <th data-field="especialidad" >Especialidad</th>
            <th data-field="causa_motivo" >Causa - Motivo</th>
        </tr>
    </thead>
</table>
<div class="pull-right">
    <ul id="pag_tbConsultas" class="pagination-sm"></ul>
    <!--<ul id="pag_tbConsultas" class="pagination">
        <li>
            <a href="#" aria-label="Anterior">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li>
            <a href="#" aria-label="Siguiente">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>-->
</div>
<div class="modal fade" id="ListPaciente" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Elegir pacientes</h4>
            </div>
            <div class="modal-body">

            </div>
        </div>
    </div>
</div>
<script src="consulta/js/style_listConsultas.js" type="text/javascript"></script>
