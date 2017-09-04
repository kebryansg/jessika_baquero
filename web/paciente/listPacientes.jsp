<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="contenedor-tabs">
    <div class="container-fluid">
        <div class="row">
            <div class="pull-right">
                <div class="col-md-12">
                    <div class="form-inline">
                        <select class="selectpicker" data-width="120px" id="cboFilter" >
                            <option value="1">Nombres</option>
                            <option value="2">Cédula</option>
                            <option value="3">H. Clinica</option>
                        </select>
                        <input class="form-control" id="txt_filterPaciente"  placeholder="Buscar">
                    </div>
                    
                </div>
            </div>
            <div class="pull-left">
                <div class="col-md-12">
                    <div class="form-inline">
                        <label for="txt_filterPaciente" class="control-label">Mostrar</label>
                        <select class="selectpicker" data-width="80px" id="cantList">
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="30">30</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>    
        <br>
        <div class="row">
            <ul id="tablPaciente-context-menu" class="dropdown-menu">
                <li data-item="edit"><a><i class="fa fa-pencil-square fa-fw" aria-hidden="true"></i>&nbsp; Editar</a></li>
                <li data-item="delete"><a><i class="fa fa-trash fa-fw" aria-hidden="true"></i>&nbsp; Eliminar</a></li>
                <li data-item="new_consulta"><a><i class="fa fa-plus fa-fw" aria-hidden="true"></i>&nbsp; Nueva Consulta</a></li>
            </ul>
            <div class="col-md-12">
                <div class="table-responsive ">
                    <table  data-toggle="table" data-unique-id="hc" id="tablPaciente" data-height="300">
                        <thead style="font-weight: bold;">
                            <tr>
                                <th data-field="id" >Cod.</th>
                                <th data-field="hc" >H. Clinica</th>
                                <th data-field="cedula" >Cédula</th>
                                <th data-field="nombres" >Apellidos y Nombres</th>
                                <th data-field="domicilio" >Domicilio</th>
                                <th data-field="sexo">Sexo</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>       
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="pull-right">
                    <ul id="pagination-demo" class="pagination-sm"></ul>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="paciente/js/paciente.js" type="text/javascript"></script>
<script src="paciente/js/styleListPaciente.js" type="text/javascript"></script>