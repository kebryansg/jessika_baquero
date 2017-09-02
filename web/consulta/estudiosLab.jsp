<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-fluid">

    <div id="toolbarEstudiosLab">

    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="row">
                <div class="pull-left">
                    <select class="selectpicker" data-width="60px" id="cantListEstudiosLab">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="20">20</option>
                        <option value="30">30</option>
                    </select>
                    <button id="btnSelecc" class="btn btn-default">Agregar</button>
                </div>
                <div class="pull-right">
                    <div class="form-inline">
                        <input class="form-control" id="txt_filterEstudiosLab" style="width: 150px;" placeholder="Buscar">
                        <select class="selectpicker" data-width="120px" id="cboCategoria">

                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <table data-toggle="table" 
                       data-toolbar="#toolbarEstudiosLab" 
                       data-click-to-select="true"
                       data-height="270" id="tableEstudiosLab">
                    <thead style="font-weight: bold;">
                        <tr>
                            <th data-field="state" data-checkbox="true"></th>
                            <th data-field="ID" data-align="center">Codigo</th>
                            <th data-field="categoria">Categoria</th>
                            <th data-field="estudio">Estudio de laboratorio</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="row">
                <div class="pull-right">
                    <ul id="pagEstudiosLab" class="pagination">
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

        </div>       
        <div class="col-md-6">
            <div class="pull-left">
                <button id="btnRemover" class="btn btn-default">Remover</button>    
            </div>
            <br>
            <br>
            <table id="tableEstudiosLabSelec" 
                   data-click-to-select="true"
                   data-toggle="table" data-height="250">
                <thead style="font-weight: bold;">
                    <tr>
                        <th data-field="state" data-checkbox="true"></th>
                        <th data-field="id" data-align="center">Codigo</th>
                        <th data-field="estudio">Estudio de laboratorio</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script src="consulta/js/estudioLab.js" type="text/javascript"></script>
