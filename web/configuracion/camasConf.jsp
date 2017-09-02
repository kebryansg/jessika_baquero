<%-- 
    Document   : camasConf
    Created on : 05-jun-2017, 19:12:52
    Author     : Deivi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
   // $(':number').val(1);
</script>
<script src="configuracion/js/camasConf.js" type="text/javascript" ></script>    
<div class="container-fluid" id="tbEstablecimiento">
    <div class="form-horizontal">
        <div class="row ">                            
            <div class="col-md-12">
                <div class="pull-right">                                    
                    <button class="btn btn-info" id="btnCancelar">Cancelar</button>
                    <button class="btn btn-info" id="btnGuardar"  data-id="0">Guardar</button>
                </div>       
            </div>
        </div>
        <hr id="separador" />
        <div class="alert alert-info" style="text-align: center">Por especialidad registre el total de camas en una habitación individual (campo 1) y el total de camas en una habitación doble o más (campo 2)</div>
        <div class="row">
           <div class="form-group col-md-6">
                <label for="txtMedicinaInterna" class="control-label col-lg-6">Medicina interna</label>
                <div class="col-md-3">
                    <input validate="text" name="MedicinaInterna" value="0" min="0"  type="number" class="form-control" id="txtMedicinaInternaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                     <input validate="text" value="0" min="0" type="number" class="form-control" id="txtMedicinaInternaDoble" placeholder="D Y M">
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="txtCirugia"  class="control-label col-lg-6">Cirugía</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtCirugiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtCirugiaDoble" placeholder="D Y M">
                </div>
            </div>

        </div>
        <div class="row">
           <div class="form-group col-md-6">
                <label for="txtGinecologia" class="control-label col-lg-6">Ginecología y Obstetricia</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtGinecologiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtGinecologiaDoble" placeholder="D Y M">
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="txtPediatría" class="control-label col-lg-6">Pediatría (cunas e incubadoras)</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtPediatriaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtPediatriaDoble" placeholder="D Y M">
                </div>
            </div>

        </div>
        <div class="row">
           <div class="form-group col-md-6">
                <label for="txtCardiologia" class="control-label col-lg-6">Cardiología</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtCardiologiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtCardiologiaDoble" placeholder="D Y M">
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="txtNeumologia" class="control-label col-lg-6">Neumología</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtNeumologiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtNeumologiaDoble" placeholder="D Y M">
                </div>
            </div>

        </div>
        <div class="row">
           <div class="form-group col-md-6">
                <label for="txtPsiquiatria" class="control-label col-lg-6">Psiquiatría</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtPsiquiatriaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtPsiquiatriaDoble" placeholder="D Y M">
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="txtTrtaumotologia"  class="control-label col-lg-6">Traumatología</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtTraumotologiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtTraumotologiaDoble" placeholder="D Y M">
                </div>
            </div>

        </div>
        <div class="row">
           <div class="form-group col-md-6">
                <label for="txtInfectologia" class="control-label col-lg-6">Infectología</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtInfectologiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtInfectologiaDoble" placeholder="D Y M">
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="txtOftalmologia"  class="control-label col-lg-6">Oftalmología y Otorrinolaringo.</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtOftalmologiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtOftalmologiaDoble" placeholder="D Y M">
                </div>
            </div>

        </div>
        
        <div class="row">
           <div class="form-group col-md-6">
                <label for="txtUrologia" class="control-label col-lg-6">Urología</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtUrologiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtUrologiaDoble" placeholder="D Y M">
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="txtGastroenterologia"  class="control-label col-lg-6">Gastroenterología</label>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtGastroenterologiaIndividual" placeholder="I">
                </div>
                <div class="col-md-3">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtGastroenterologiaDoble" placeholder="D Y M">
                </div>
            </div>

        </div>
        <div class="row">
           <div class="form-group col-md-6">
                <label for="txtEmergencia" class="control-label col-lg-6">Emergencia</label>
                <div class="col-md-6">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtEmergencia">
                </div>                                 
            </div>
            <div class="form-group col-md-6">
                <label for="txtCuidadosIntensivos" class="control-label col-lg-6">Cuidados Intensivos</label>
                <div class="col-md-6">
                    <input validate="text" value="0" min="0" type="number" class="form-control" id="txtCuidadosIntensivos" placeholder="I">
                </div>                
                
            </div>

        </div>
    </div>
</div>
        

