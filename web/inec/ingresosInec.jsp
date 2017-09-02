<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <script>
        $('.form_date').datetimepicker({
        format: "yyyy-mm",
        language: 'es',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: "year",         
        pickerPosition: "bottom-left",
        minView: 3,
        forceParse: 0
        
    });     
    
    </script>       
    <body>
        
<div class="container-fluid">
     <div style="display:none;" id="msgAlert" class="alert alert-danger">            
            <center><strong>¡Error!</strong> Opción no valida, registre los datos del establecimiento en configuración, para poder descargar los formularios</center>
        </div>
	<div class="row">
            <div class="col-md-12">
                <label for="inputName" style="padding-top: 10px" class="control-label col-xs-1">Ingreso</label>
                <div class="col-xs-2">
                    <div class="input-group date form_date"  data-date-format="mm-yyyy">
                        <input validate="date" class="form-control" id="dtpFechaReporte" size="16" type="text"  readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>                     
                </div>
                 <button id="btnGenerar" type="button" class="btn btn-primary">Generar</button> 
                 <a hidden="hidden"  id="descargarEgresos" class="btn btn-primary ">Descargar Egresos</a>                 
                 <a hidden="hidden" id="descargarCamasIndividual" class="btn btn-primary ">Descargar Camas Individual</a>    
                 <a hidden="hidden" id="descargarCamas" class="btn btn-primary ">Descargar Camas</a>                 
                
                 
            </div>
	</div>
    <br/>    
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">Egresos Hospitalarios</h3>
        </div>
        <div class="panel-body">
            Identificación del establecimiento; datos del paciente: sexo, edad, lugar de residencia habitual, fecha de ingreso y egreso, días de estadía, condición al egresar (alta, fallecido), diagnóstico definitivo del egreso y servicio del que egresó.
        </div>
    </div>
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">Camas Hospitalarias</h3>
        </div>
        <div class="panel-body">
            Número de camas de dotación normal, según servicios o especialidades. Número de camas disponibles, según servicio o especialidades; días-paciente; días-cama disponible.
        </div>
    </div>        
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">Camas y Egresos Hospitalarias</h3>
        </div>
        <div class="panel-body">
            Las Estadísticas Hospitalarias presentan información estadística, sobre la morbilidad hospitalaria, que permite conocer el estado de salud de la población; determina la incidencia de las enfermedades tratadas en los hospitales; proporciona indicadores necesarios, para la elaboración y evaluación de los programas de salud, y, suministra datos sobre la utilización de camas hospitalarias de dotación normal y camas disponibles
        </div>
    </div>    
</div>
      
    </body>
    
 <script src="inec/js/ingresosInec.js" type="text/javascript" ></script>
  <script src="resources/bootstrap/js/waitingfor.js" type="text/javascript" ></script>
</html>
