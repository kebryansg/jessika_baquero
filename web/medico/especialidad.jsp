<%@page import="mvc.modelo.smDaoImp.EspecialidadDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.Especialidad"%>
<%@page import="mvc.modelo.smDao.EspecialidadDao"%>
<%@page import="java.util.List"%>
<%@page import="mvc.modelo.smDao.EspecialidadDao"%>
<div class="contenedor-tabs" >
    <div class="container-fluid">
        <div class="row">
            <div class="pull-right">
                <div class="col-md-12">
                    <div class="form-inline">
                        <input class="form-control" id="txtBuscar" placeholder="Buscar">
                        <button class="btn btn-info" id="btnAgregar">Agregar</button>
                    </div>

                </div>
            </div>
            <div class="pull-left">
                <div class="col-md-12">
                    <div class="form-inline">
                        <select class="selectpicker" data-width="80px" id="cboMostrar_tbEspecialidad">
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
            <ul id="tbEspecialidad-context-menu" class="dropdown-menu" >
                <li data-item="edit"><a><i class="fa fa-plus fa-fw" aria-hidden="true"></i>&nbsp; Editar</a></li>
                <li data-item="delete"><a><i class="fa fa-table fa-fw" aria-hidden="true"></i>&nbsp; Eliminar</a></li>
            </ul>
            <div class="table-responsive col-lg-12">
                <table id="tbEspecialidad" data-toogle="table">
                    <thead>
                        <tr>
                            <th data-field="id" >No.</th>
                            <th data-field="descripcion" >Descripción</th>
                            <th data-field="accion" >Descripción</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>   
        <div class="pull-right">
            <ul id="pag_tbEspecialidad" class="pagination">
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
    <div class="modal fade" id="modalEspecialidad" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Agregar Especialidad</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="form-control-label">Descripci&oacute;n:</label>
                        <input validate="text" type="text" class="form-control" id="recipient-name">
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="btnActualizar" type="button"  class="btn btn-primary">Guardar</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="medico/js/especialidades.js"></script>
<script src="resources/js/configuracionInicial.js" type="text/javascript" ></script>