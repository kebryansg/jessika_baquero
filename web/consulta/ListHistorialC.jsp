<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<i class="glyphicon glyphicon-plus"></i>-->
<div class="container-fluid">
    <div class="row">
        <div class="form-group col-md-2">
            <input class="form-control solo-numero" maxlength="10" validate="date" id="txt_cargarPaciente" placeholder="Buscar (cedula)" type="text" >
        </div>
        <div class="form-group col-md-4">
            <button class="btn btn-info" id="pac_Cargar" >Cargar</button>    
            <button class="btn btn-info" id="pac_Buscar" data-toggle="modal" data-target="#ListPaciente" ><i class="glyphicon glyphicon-search"></i> Buscar Paciente</button>    
            <button class="btn btn-danger " id="pac_Delete" ><i class="glyphicon glyphicon-trash"></i></button>    
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-2">
            <label class="control-label" for="con_nombrePaciente">Nº Historia C.:</label>
            <input class="form-control solo-numero" readonly  id="con_historiaPaciente" placeholder="Nº Historia C." type="text" >
        </div>
        <div class="form-group col-md-2">
            <label class="control-label" for="con_nombrePaciente">Cedula:</label>
            <input class="form-control solo-numero" readonly  id="con_cedulaPaciente" placeholder="Cedula Paciente" type="text" >
        </div>
        <div class="form-group col-md-4">
            <label class="control-label" for="con_nombrePaciente">Paciente:</label>
            <input class="form-control solo-numero" readonly  id="con_nombrePaciente" placeholder="Nombre Paciente" type="text" >
        </div>
        <div class="form-group col-md-4 hidden">
            <label class="control-label" for="con_sexoPaciente">Sexo:</label>
            <input class="form-control solo-numero" readonly  id="con_sexoPaciente" placeholder="Nombre Paciente" type="text" >
        </div>
    </div>  
    <hr>
    <div class="row">
        <div class="col-md-12">
            <div class="pull-left">
                <select class="form-control selectpicker" validate="select"  >
                    <option value="5">5</option>
                    <option value="5">10</option>
                    <option value="5">20</option>
                </select>
            </div>
            <div class="pull-right">
                <div class="form-inline">
                    <input class="form-control" style="width: 150px;" placeholder="Buscar" id="txt_filterHistorialC">
                    <button class="btn btn-info" id="btnNewConsulta"> Nueva Consulta </button>
                </div>
            </div>
        </div>

    </div>
    <br>
    <!-- context menu -->
    <ul id="tbHc-context-menu" class="dropdown-menu" >
        <li data-item="new"><a><i class="fa fa-plus fa-fw" aria-hidden="true"></i>&nbsp; Nuevo Consulta</a></li>
        <li data-item="view"><a><i class="fa fa-table fa-fw" aria-hidden="true"></i>&nbsp; Detalle del caso</a></li>
    </ul>
    <div class="row">
        <div class="col-md-12">
            <div class="table-responsive">
                <table data-toggle="table" data-height="300" id="tbHC" >
                    <thead>
                        <tr>
                            <th data-field="caso">ID</th>
                            <th data-field="fecha">Fecha</th>
                            <th data-field="motivo">Motivo</th>
                        </tr>
                    </thead>

                </table>
            </div>
        </div>
    </div>
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
<div class="modal fade" id="viewHistorialCaso" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Historial del Caso</h4>
            </div>
            <div class="modal-body">
                <ul id="tb_ViewHC-context-menu" class="dropdown-menu" >
                    <li data-item="view"><a><i class="fa fa-table fa-fw" aria-hidden="true"></i>&nbsp; Detalle Consulta</a></li>
                </ul>

                <table data-toggle="table" data-height="300" id="tb_ViewHC">
                    <thead>
                        <tr>
                            <th data-field="id" data-align="center">ID</th>
                            <th data-field="caso">Caso</th>
                            <th data-field="fecha">Fecha</th>
                            <th data-field="tipo">Tipo Consulta</th>
                            <th data-field="motivo">Causa - Motivo</th>
                            <th data-field="especialidad">Especialidad</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<!--<script src="resources/js/configuracionInicial.js" type="text/javascript"></script>-->
<script src="consulta/js/style_ListHistorialC.js" type="text/javascript"></script>

<script type="text/javascript">

    /*$('#grid').bootstrapTable({
     contextMenu: '#context-menu',
     onContextMenuItem: function(row, $el){
     if($el.data("item") == "edit"){
     alert(row.itemid);
     }
     }
     });*/


</script>