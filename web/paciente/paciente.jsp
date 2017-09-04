<%@page import="mvc.controlador.entidades.ip.Parientes"%>
<%@page import="mvc.modelo.ipDaoImp.ParientesDaoImp"%>
<%@page import="mvc.modelo.ipDaoImp.EnfermedadDaoImp"%>
<%@page import="mvc.controlador.entidades.ip.Enfermedad"%>
<%@page import="mvc.modelo.ipDao.EnfermedadDao"%>
<%@page import="mvc.controlador.entidades.ip.Provincia"%>
<%@page import="java.util.List"%>
<%@page import="mvc.modelo.ipDao.ProvinciaDao"%>
<%@page import="mvc.modelo.ipDaoImp.ProvinciaDaoImp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<div class="contenedor-tabs">
    <div class="row ">
        <div class="col-md-12">
            <div class="pull-right">
                <button class="btn btn-info" id="cancelPaciente">Cancelar</button>
                <button class="btn btn-info" id="savePaciente" >Guardar</button>
                <!-- data-id="0" -->
            </div>       

        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-tabs" id="tabPacientes_a">
                <li class="active"><a data-toggle="tab" href="#ip">Informacion personal</a></li>
                <li><a data-toggle="tab" href="#antecedentes">Antecedentes</a></li>
                <li id="tabObstetricia" data-id="0" class="disabledTab"><a data-toggle="tab" href="#obstetricia">Obstetricia</a></li>
            </ul>
            <div class="tab-content" id="tabPacientes">
                <div class="tab-pane fade in active" id="ip" style="padding-top: 10px;">
                    <div class="form-horizontal">
                        <div class="row panel panel-group">
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Cedula </label>
                                    <div class="col-md-8">
                                        <input validate="cedula" type="text" class="form-control solo-numero" id="pac_Cedula" maxlength="10" placeholder="Cedula">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-sm-3">Nombres </label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" validate="text" id="pac_primerNombre" placeholder="Primer nombre">    
                                    </div>

                                    <div class="col-md-4">
                                        <input type="text" class="form-control" validate="text" id="pac_segundoNombre" placeholder="Segundo nombre">
                                    </div>      


                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-sm-3">Apellidos </label>
                                    <div class="col-md-4">
                                        <input type="text" validate="text" class="form-control" id="pac_primerApellido" placeholder="Primer apellido">
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" validate="text" class="form-control" id="pac_segundoApellido" placeholder="Segundo apellido">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Fecha Nac. </label>
                                    <div class="col-md-8">
                                        <input class="date-mask form-control" validate="fecha_mask" placeholder="Fecha nacimiento" data-inputmask="'alias': 'yyyy-mm-dd'" id="pac_FechaNac"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Tef. Casa </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control solo-numero" maxlength="10" id="pac_TelCasa" placeholder="Tef. Casa">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Movil </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control solo-numero"  maxlength="10" id="pac_TelOficina" placeholder="Movil">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">E-mail </label>
                                    <div class="col-md-8">
                                        <input type="email" validate="email" class="form-control"  id="pac_Email" placeholder="E-mail">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Domicilio </label>
                                    <div class="col-md-8">
                                        <textarea class="form-control" validate="text" rows="3" id="pac_Domicilio"></textarea>
                                    </div>
                                </div>


                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Estado civil </label>
                                    <div class="col-md-8">
                                        <select class="form-control selectpicker"  id="pac_EstadoCivil">
                                            <!--<option value="0">Seleccione</option>-->
                                            <option value="1">Soltero</option>
                                            <option value="2">Casado</option>
                                            <option value="3">Divorciado</option>
                                            <option value="4">Viudo</option>
                                            <option value="5">Union libre</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Etnia </label>
                                    <div class="col-md-8">
                                        <select class="form-control selectpicker" id="pac_Etnia">
                                            <!--<option value="0">Seleccione</option>-->
                                            <option value="1">Indigena</option>
                                            <option value="2">Afroecuatoriano</option>
                                            <option value="3">Negro(a)</option>
                                            <option value="4">Mulato(a)</option>
                                            <option value="5">Montubio(a)</option>
                                            <option value="6">Mestizo(a)</option>
                                            <option value="7">Blanco(a)</option>
                                            <option value="8">Otro(a)</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Género </label>
                                    <div class="col-md-8">
                                        <select class="form-control selectpicker"  id="pac_Genero">
                                            <!--<option value="0">Seleccione</option>-->
                                            <option value="1">Masculino</option>
                                            <option value="2">Femenino</option>
                                            <option value="3">Indeterminado</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Nacionalidad </label>
                                    <div class="col-md-8">
                                        <select class="form-control selectpicker"  id="pac_nacionalidad">
                                            <!--<option value="0">Seleccione</option>-->
                                            <option value="1">Ecuatoriano</option>
                                            <option value="2">Extranjero</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Pais Nac. </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" validate="text"  id="pac_PaisNac" value="Ecuador" placeholder="Pais Nacimiento">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Discapacidad </label>
                                    <div class="col-md-8">
                                        <!--<label><input type="checkbox" value="" id="pac_Discapacidad"></label>-->
                                        <select class="form-control selectpicker"  id="pac_Discapacidad">
                                            <option value="1">Ninguna</option>
                                            <option value="2">Discapacidad fìsica</option>
                                            <option value="3">Discapacidad intelectual</option>
                                            <option value="4">Discapacidad mental</option>
                                            <option value="5">Discapacidad auditiva</option>
                                            <option value="6">Discapacidad visual</option>
                                            <option value="7">Otra</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Observaciones </label>
                                    <div class="col-md-8">
                                        <textarea class="form-control" validate="text" rows="5" id="pac_Observacion"></textarea>
                                    </div>
                                </div>
                            </div>
                            <!--<div id="contenedorImg" >
                                <div>
                                    <img src="resources/img/iconperson.png" id="pac_imagen" edit="resources/img/iconperson.png" class="img-thumbnail" height="150" width="150"/>  
                                </div>

                                <div class="btn-foto" >
                                    <input type="file" id="file_imagen" style="display: none;" >      
                                    <button class="btn btn-info" id="btnAddPhoto"><i class="fa fa-camera"></i></button>
                                    <button class="btn btn-danger" id="btnRemovePhoto"><i class="fa fa-remove"></i></button>
                                </div>       
                            </div>-->
                        </div>
                        <div class="row panel panel-group">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-md-4">Provincia </label>
                                    <div class="col-md-7">
                                        <select class="selectpicker form-control" validate="select"  data-live-search="true" data-size="8" id="cboProvincia">
                                            <option value="0">Seleccione</option>
                                            <%
                                                ProvinciaDao p = new ProvinciaDaoImp();
                                                List<Provincia> ps = p.list();
                                                for (Provincia provincia : ps) {%>
                                            <option value="<%= provincia.getId()%>"><%= provincia.getDescripcion()%></option>  
                                            <% }%>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-md-4">Canton </label>
                                    <div class="col-md-7">
                                        <select class="selectpicker form-control remove-example" validate="select" data-live-search="true" data-size="8" id="cboCanton">
                                            <option value="0">Seleccione</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">

                                <div class="form-group">
                                    <label class="control-label col-md-4">Parroquia </label>
                                    <div class="col-md-7">
                                        <select class="selectpicker form-control remove-example" validate="select" data-live-search="true" data-size="8" id="cboParroquia">
                                            <option value="0">Seleccione</option>
                                        </select>
                                    </div>
                                </div>

                            </div>


                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Nombre Contacto</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_nombreContacto"  type="text" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Movil contacto</label>
                                    <div class="col-md-5">
                                        <input class="form-control solo-numero" id="pac_movilContacto"  type="text" maxlength="10">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Parentezco</label>
                                    <div class="col-md-8">
                                        <select class="selectpicker form-control"  id="cboParentezco">
                                            <option value="0">Seleccione</option>
                                            <option value="1">Padre</option>
                                            <option value="2">Madre</option>
                                            <option value="3">Hermano</option>
                                            <option value="4">Otro</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="tab-pane fade " id="antecedentes" style="padding-top: 10px;">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label">APP (Antecedentes Patologicos Personales): </label>
                            <textarea id="pac_APP" validate="text" class="form-control" rows="4"></textarea>
                        </div>
                        <div class="form-group">
                            <label class="control-label">APF (Antecedentes Patologicos Familiares): </label>
                            <textarea id="pac_APF" validate="text" class="form-control" rows="4"></textarea>
                        </div>
                    </div>

                </div>
                <div class="tab-pane fade" id="obstetricia" style="padding-top: 10px;">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">F.P.P </label>
                                    <div class="col-md-5">
                                        <input class="date-mask form-control" validate="fecha_mask" placeholder="FPP" data-inputmask="'alias': 'yyyy-mm-dd'" id="pac_FPP"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Gestacion</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_Gestacion"  type="number" value="0" min="0" max="10">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Abortos</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_Abortos"  type="number" value="0" min="0" max="10">
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-horizontal">


                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Partos</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_Partos" type="number" value="0" min="0" max="10">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Cesareas</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_Cesareas" type="number" value="0" min="0" max="10">
                                    </div>
                                </div>
                            </div>       

                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Nacido vivos</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_NacidoVivo"  type="number" value="0" min="0" max="10">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Nacidos muertos</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_NacidoMuerto"  type="number" value="0" min="0" max="10">
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-horizontal">


                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Hijos vivos</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_HijosVivos" type="number" value="0" min="0" max="10">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputUserName" class="control-label col-md-3">Hijos Muertos</label>
                                    <div class="col-md-5">
                                        <input class="form-control" id="pac_HijosMuertos"  type="number" value="0" min="0" max="10">
                                    </div>
                                </div>
                            </div>       
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="paciente/js/paciente.js" type="text/javascript"></script>
<script src="paciente/js/stylePaciente.js" type="text/javascript"></script>