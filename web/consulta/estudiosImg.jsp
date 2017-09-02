<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-fluid">
    <div class="row">
        <div class="pull-right ">
            <div class="col-md-12">

                <div class="form-inline">
                    <input class="form-control" style="width: 120px;" id="txt_filterEstudiosImg" placeholder="Buscar">
                    <select class="selectpicker" data-width="150px" id="cboTipoEstudiosImg"></select>
                    <select class="selectpicker" data-width="150px" id="cboEstudiosImg"></select>
                </div>
            </div>
        </div>
        <div class="pull-left">
            <div class="col-md-12">
                <div class="form-inline">
                    <label for="txt_filterPaciente" class="control-label">Mostrar</label>

                </div>
            </div>

        </div>
    </div>    
    <br>
    <div id="toolbarEstudiosImg">
        <select class="selectpicker" data-width="80px" id="cantListEstudiosImg">
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
        </select>
        <button id="btnSeleccEstudiosImg" class="btn btn-default">Agregar</button>
    </div>
    <div class="row">
        <div class="col-md-6">
            <table data-toggle="table" 
                   data-toolbar="#toolbarEstudiosImg" 
                   data-click-to-select="true"
                   data-height="300" id="tableEstudiosImg">
                <thead style="font-weight: bold;">
                    <tr>
                        <th data-field="state" data-checkbox="true"></th>
                        <th data-field="ID" data-align="center">Codigo</th>
                        <th data-field="tipoEstudio">Tipo Estudio</th>
                        <th data-field="estudio">Estudio de Imagenes</th>
                        <th data-field="extremidad">Extremidad</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <div class="pull-right">
                <ul id="pagEstudiosImg" class="pagination">
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
                </ul>
            </div>
        </div>
        <div class="col-md-6">
            <table id="tableEstudiosImgSelec" 
                   data-toolbar="#toolbarEstudiosImgSelec"  
                   data-toggle="table" data-height="300">
                <thead style="font-weight: bold;">
                    <tr>
                        <th rowspan="2" data-valign="middle" data-field="state" data-checkbox="true"></th>
                        <th rowspan="2" data-valign="middle" data-field="id" data-align="center" >ID</th>
                        <th rowspan="2" data-valign="middle" data-field="tipoEstudio">Tipo estudio</th>
                        <th rowspan="2" data-valign="middle" data-field="estudio">Estudio de imagenes</th>
                        <th colspan="2" data-halign="center"  >Accion</th>
                    </tr>
                    <tr>
                        <th data-field="der" data-formatter="check" data-align="center">Der.</th>
                        <th data-field="izq" data-formatter="check" data-align="center">Izq.</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <div id="toolbarEstudiosImgSelec">
        <button id="btnRemoverEstImg" class="btn btn-default">Remover</button>    
        <button id="btnpush" class="btn btn-default">Push</button>
    </div>

</div>
<script src="consulta/js/estudioImg.js" type="text/javascript"></script>
