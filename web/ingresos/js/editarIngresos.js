/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
ocultarModal();

 $(function () {
  $('[data-toggle="tooltip"]').tooltip();
})
/*-------------------------------Variables-------------------------------------------------------------------------*/
var res;
var pagina=0;
var diagnosticos=[];    
var datos=[];
var medicinas=[];
var indice=0;
var idMedicamento=0;
var idIngreso=0;
var ultimo=0;
var filas=5;
var indiceMedicamentos=0;
 var bandera=0;
 var m;
 var search=false;
    $('.form_date').datetimepicker('update', new Date());
    //dtpFechaMedicamento
    
 
/*-------------------------------Ingresos-------------------------------------------------------------------------*/
//Buscar Ingresos
$('#tabMantenimientoIngresos #btnBuscar').click(function(event) {    
     $('#tablaIngresos tr').remove();
     $('#paginacionIngresosEditar').find('li').remove();
     search=true;
    //if(!fechaMayorQue($("#dtpFechaIngresoIngresos"),$("#dtpFechaEgresoIngresos")))
    //{
        cargarIngresos();
    //}
    
});    
//Ingresos cargar
function cargarIngresos()
    {
        if(search)
        $.ajax({
            type: 'Post',
            url: 'sIngresosHospital',
            data: {            
                opcion: '5',
                fechaEgreso: $("#dtpFechaEgresoIngresos").val(),
                fechaIngreso:$("#dtpFechaIngresoIngresos").val(),
                totalMostrar:5,
                pagina:pagina
            },
            async: false,
            success:function(data){  
                console.log(data);
                var resultado = JSON && JSON.parse(data) || $.parseJSON(data);   
                if(resultado.length==0)
                {
                   
                    alertify.success("No existen registros disponibles");
                }
                else
                {
                console.log(resultado[0].registros);
                $('#paginacionIngresosEditar').find('li').remove();
                var totalPaginas=resultado[0].registros/5;
                totalPaginas=Math.ceil(totalPaginas);                
                $("#paginacionIngresosEditar ul").append('<li id="atras"><a href="#">&laquo;</a></li>');
                filas=resultado.length;
                var indice=0;
                for(i=0;i <totalPaginas; i++)                
                {
                    indice=parseInt(i)+1;
                    if(i==pagina)
                        $("#paginacionIngresosEditar ul").append('<li id='+i+' class="active"><a href="#">'+indice+'</a></li>');
                    else 
                        $("#paginacionIngresosEditar ul ").append('<li id='+i+'><a href="#">'+indice+'</a></li>');
                }
                ultimo=parseInt(totalPaginas)-1;
                $("#paginacionIngresosEditar ul").append('<li id="adelante"> <a href="#">&raquo;</a></li>');
                $('#tablaIngresos tr').remove();
                $('#tablaIngresos thead').append("<tr>\n\                                                        <th style='display:none;'>No.</th>\n\
                                                        <th >Cédula</th>\n\
                                                        <th>Apellidos y Nombres</th>\n\
\n\                                                     <th style='display:none;' >idTipoIngregos</th>\n\
                                                        <th style='display:none;'>idCaso</th>\n\
                                                        \n\<th style='display:none;'>Id. Espegreso</th>\n\
                                                        <th style='display:none;'>E. Egreso</th>\n\
                                                        <th>F. Ingreso</th>\n\
                                                        <th>F. Egreso</th>\n\
                                                        <th style='display:none;'>Hora</th>\n\
                                                        <th  style='display:none;'>Cond. Egreso</th>\n\
                                                        <th>D. Egreso</th>\n\
                                                        <th style='display:none;' class='col-lg-1'>S. Egreso</th>\n\
                                                        <th style='display:none;' class='col-lg-1'>S. Egreso 2</th>\n\
                                                        <th style='display:none;' class='col-lg-1'>C. Externa</th>\n\
                                                        <th >Cód.</th>\n\
                                                        \n\<th style='display:none;' class='col-lg-1'></th>\n\
                                                        <th class='col-lg-3'>Acción.</th></tr>");
                var valor="btn-group";
                
                    for(i=0;i <resultado.length; i++)
                    {
                        diagnosticos[i]=resultado[i].definitivoEgreso;
                        if(diagnosticos[i].length>=15)
                            res = diagnosticos[i].substring(0, 15)+'...';
                        else
                            res = diagnosticos[i];
                        if(i==4)
                            valor="btn-group dropup";
                        $('#tablaIngresos').append("<tr >\n\
                                                        <td style='display:none;'>"+resultado[i].id+"</td>\n\
                                                        <td>"+resultado[i].unPaciente.cedula+"</td>\n\
                                                        <td>"+resultado[i].unPaciente.apellido1+" "+resultado[i].unPaciente.apellido2+" "+resultado[i].unPaciente.nombre1+ ' '+resultado[i].unPaciente.nombre2+"</td>\n\
\n\                                                     <td style='display:none;'>"+resultado[i].idTipoIngreso.id+"</td>\n\
                                                        <td style='display:none;'>"+resultado[i].idCaso.id+"</td>\n\
                                                        <td style='display:none;'>"+resultado[i].idEspecialidadEgreso.id+"</td>\n\
                                                        <td style='display:none;'>"+resultado[i].idEspecialidadEgreso.descripcion+"</td>\n\
                                                        <td>"+resultado[i].fechaEntrada+"</td>\n\
                                                        <td>"+resultado[i].fechaSalida+"</td>\n\
                                                        <td style='display:none;'>"+resultado[i].hora+"</td>\n\
                                                        <td style='display:none;'>"+resultado[i].condicionEgreso+"</td>\n\
                                                        <td class='tooltips' id='tooltipDefinitivoEgreso' data-toggle='tooltip' data-container='body' data-placement='bottom' title='"+resultado[i].definitivoEgreso+"'>"+res+"</td>\n\
                                                        <td style='display:none;'>"+resultado[i].secundarioEgreso+"</td>\n\
                                                        <td style='display:none;'>"+resultado[i].secundarioEgreso2+"</td>\n\
                                                        <td style='display:none;'>"+resultado[i].causaExterna+"</td>\n\
                                                        <td>"+resultado[i].codigoDiagnosticoDefinitivo+"</td>\n\
                                                        \n\<td style='display:none;'>"+resultado[i].definitivoEgreso+"</td>\n\
                                                        <td >\n\
                                                            <button id='botonEditar' class='btn btn-primary'><span class='glyphicon glyphicon-pencil'></span> </button> \n\
                                                            \n\
                                                            <button id='btnEliminar' class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span></a></button>\n\
\n\
                                                            <div class='"+valor+"'>\n\
                                                            \n\<button id='btnMedicinas' type='button' class='btn btn-warning dropdown-toggle' data-toggle='dropdown'>Medicinas <span class='caret'></span></button>\
                                                               <ul class='dropdown-menu' role='menu'>\n\
                                                                    <li id='opAgregarMedicina'><a href='#'>Agregar</a></li>\n\
                                                                    <li id='opMantenimientoMedicina'><a href='#'>Editar</a></li>     \n\
                                                                    \n\
                                                               </ul>\n\
                                                            </div>\n\
</td>\n\
                                                    </tr>");
                }
            }
        }
        });
    $('[data-toggle="tooltip"]').tooltip();
    
     } 
//Editar Ingreso     
 $("#tabMantenimientoIngresos .table-responsive").on("click", "#botonEditar", function(){  
          var cont=0;    
          $(this).parents("tr").find("td").each(function(){
              datos[cont]=$(this).html();   
              cont++;
          });
          $.each($("#modalEditarIngresos input[validate='date']"), function (index, value) {
              $(value).off("blur");
          });
          $("#modalEditarIngresos #txtDefinitivoEgreso").off("blur"); 
          remover($("#modalEditarIngresos #txtCodigoCie"));
          remover($("#modalEditarIngresos #txtDefinitivoEgreso"));
          $("#modalEditarIngresos .help-block").remove();
          console.log(datos);
          $('#txtPaciente').val(datos[2]);
          $('select[id=cboCondicionEgreso]').val(datos[10]);
          $('select[id=cboEspecialidadEgreso]').val(datos[5]);
          $('.selectpicker').selectpicker('refresh');
          $('#dtpFechaIngresoIngresosModal').val(datos[7]);
          $('#dtpFechaEgresoIngresosModal').val(datos[8]);             
          $('#dtpHoraIngreso').val(datos[9]);
          $('#txtCodigoCie').val(datos[15]);
          $('#txtDefinitivoEgreso').val(datos[16]);  
          //alert($('#tooltipDefinitivoEgreso').tooltip('fixTitle').text());
          //$('#txtDefinitivoEgreso').val($('#tooltipDefinitivoEgreso').tooltip('fixTitle').text());
          $('#txtSecundarioEgreso').val(datos[12]);   
          $('#txtSecundarioEgreso2').val(datos[13]);   
          $('#txtCausaExterna').val(datos[14]);   
           $('#btnActualizar').prop('disabled', false);
          var id='modalEditarIngresos';
          $("#"+id).modal('show');
      });
//Actualizar Ingresos
 $('#tabMantenimientoIngresos #btnActualizar').click(function(event) {
     if (validarIngresos()) {
          $('#btnActualizar').prop('disabled', true);
        $.post('sIngresosHospital', {
               idIngreso : datos[0],
               fechaIngreso: $('#dtpFechaIngresoIngresosModal').val(),
               idTipoIngreso: 2,
               idEspecialidadEgreso: $('#cboEspecialidadEgreso').val(),
               fechaEgreso: $('#dtpFechaEgresoIngresosModal').val(),
               horaIngreso: $("#dtpHoraIngreso").val(),
               sos: 0,
               condicionEgreso: $("#cboCondicionEgreso").val(),
               definitivoEgreso: $("#txtDefinitivoEgreso").val(),
               secundarioEgreso: $("#txtSecundarioEgreso").val(),
               secundarioEgreso2: $("#txtSecundarioEgreso2").val(),
               causaExterna: $("#txtCausaExterna").val(),
               idCaso:datos[4],
               codigoDiagnosticoDefinitivo:$("#txtCodigoCie").val(),
               opcion:'7'                                
           }, function(data) {   
               console.log(data);
               var resultado = JSON && JSON.parse(data) || $.parseJSON(data);   
               if(resultado.estado===true)
               {
                    alertify.success("Datos Actualizados correctamente");
                                      
               //$('#tooltipDefinitivoEgreso').attr('title', $("#txtDefinitivoEgreso").val()).tooltip('fixTitle').text();
               $('#tooltipDefinitivoEgreso').attr('title','the new text you want');
             
                if($("#txtDefinitivoEgreso").val().length>=15)
                    res = $("#txtDefinitivoEgreso").val().substring(0, 15)+'...';
                else
                    res = $("#txtDefinitivoEgreso").val();
                //$($('.table-responsive').find('tbody > tr')[indice]).children('td')[5].innerHTML = datos[5];
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[5].innerHTML = $('#cboEspecialidadEgreso').val();            
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[7].innerHTML = $('#dtpFechaIngresoIngresosModal').val();
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[8].innerHTML = $('#dtpFechaEgresoIngresosModal').val();
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[9].innerHTML = $("#dtpHoraIngreso").val();
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[10].innerHTML = $("#cboCondicionEgreso").val();
               
               $($('.table-responsive').find('tbody > tr')[indice]).children('td')[11].innerHTML = res;
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[12].innerHTML = $("#txtSecundarioEgreso").val();
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[13].innerHTML = $("#txtSecundarioEgreso2").val();
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[14].innerHTML = $("#txtCausaExterna").val();
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[15].innerHTML = $("#txtCodigoCie").val();
                $($('.table-responsive').find('tbody > tr')[indice]).children('td')[16].innerHTML = $("#txtDefinitivoEgreso").val();
                //cambio el valor del tooltip
                //$($('.table-responsive').find('tbody > tr')[indice]).children('td')[11].attr('data-original-title','the new text you want');
                $($('.table-responsive').find('tbody > tr')[indice]).children('td').eq(11).attr('title',  $("#txtDefinitivoEgreso").val()).tooltip('fixTitle').text();
            }
            else
                alertify.success("Problemas al intentar guardar\n"+resultado.excepcion); 
            closeModal('modalEditarIngresos');
               
           });
       }
    });
//Eliminar Ingresos
$('#tabMantenimientoIngresos .table-responsive').on("click", "#btnEliminar", function(event){ 
        var cont=0;
        $(this).parents("tr").find("td").each(function(){
            datos[cont]=$(this).html();   
            cont++;
        });
        $.post('sIngresosHospital', {
            opcion : 8,
            idIngreso: datos[0]                                
        }, function(data) {
            var resultado = JSON && JSON.parse(data) || $.parseJSON(data);   
               if(resultado.estado===true)
               {
                   filas--;
                   if(filas===0)
                    {
                        if(pagina>0)
                            pagina--;
                        filas=5;
                    }
                     cargarIngresos();
                    alertify.success("Registro eliminado");
               }
               else
                alertify.success("Problemas al intentar guardar\n"+resultado.excepcion); 
            
        });
        event.preventDefault();
        $(this).closest('tr').remove();
    });    
//Paginacion Ingreso
 $('#paginacionIngresosEditar ul').click(function (e) {        
        var a = e.target.parentNode;
        if(a.id!=="adelante" && a.id!=="atras")
            pagina=a.id;
        if(a.id==="adelante"  && pagina<ultimo)    
            pagina=parseInt(pagina)+1;            
        if(a.id==="atras" && pagina>0)
            pagina=parseInt(pagina)-1;
        cargarIngresos();
        
        
    });  
//Validar Ingresos
function validarIngresos()
{
    $("#modalEditarIngresos .help-block").remove();
    $.each($("#modalEditarIngresos input[validate='date']"), function (index, value) {
        $(value).change(function(){    		               
                validarDate(value);
	});
        validarDate(value);
        
    });
    var codigo = $("#modalEditarIngresos #txtCodigoCie");
    $('#modalEditarIngresos #txtCodigoCie').blur(function(){    		               
        validarText(codigo);
    });
    validarText(codigo);
    var definitivo = $("#txtDefinitivoEgreso");
    validarText(definitivo);
    $('#txtDefinitivoEgreso').blur(function(){    		               
        validarText(definitivo);
    });
    
    return $("#modalEditarIngresos .help-block").length === 0;
}
//Indice Seleccionado
$("#tabMantenimientoIngresos .table-responsive").on("click", "tr", function(){  
         indice = $(this).index();
      });
    
/*-------------------------------Medicamentos-------------------------------------------------------------------------*/    
//
function limpiarMedicamentos()
{
    remover($('#dtpFechaMedicamentoIngresosModal'));
    remover($('#txtMedicamentos'));
    $("#txtMedicamentoshelp").remove();  
    $("#dtpFechaMedicamentoIngresosModalhelp").remove();  
    $($('#dtpFechaMedicamentoIngresosModal')).off("blur");
    $($('#txtMedicamentos')).off("blur");
    
}


//Medicamentos cargar
 $("#tabMantenimientoIngresos .table-responsive").on("click", "#opMantenimientoMedicina", function(){
          var cont=0;    
          $(this).parents("tr").find("td").each(function(){
              datos[cont]=$(this).html();   
              cont++;
          });
          idIngreso=datos[0];
           $.post('sIngresosHospital', {
               idIngreso : datos[0],
               opcion: 10
           }, function(data) { 
               var resultado = JSON && JSON.parse(data) || $.parseJSON(data); 
               console.log(resultado);
               $('#tablaMedicamentos tr').remove();
               $("#sinRegistro #sinRegistros").remove();
               if(resultado.length>0)
               {
               
               $('#tablaMedicamentos thead').append("<tr>\n\
                                                <th style='display:none;' class='col-lg-1'>id</th>\n\
                                                <th class='col-lg-1'>Fecha</th>\n\
                                                <th class='col-lg-1'>Hora</th>\n\
                                                <th class='col-lg-3'>Notas de Evolución</th>\n\
                                                <th class='col-lg-3'>Prescripción</th>\n\
                                                <th style='display:none;'></th>\n\
                                                <th class='col-lg-1'>Acción</th>\n\
                                              </tr>");
               for(i=0;i <resultado.length; i++)
                {
                    $('#tablaMedicamentos').append("<tr>\n\                                                            \n\
                                                    <td style='display:none;'>"+resultado[i].id+"</td>\n\
                                                    \n\<td>"+resultado[i].fecha+"</td>\n\
                                                    \n\<td>"+resultado[i].hora+"</td>\n\
                                                    \n\ \n\<td>"+resultado[i].notasEvolucion+"</td>\n\
                                                    \n\ \n\<td>"+resultado[i].prescripcionMedica+"</td>\n\n\
                                                    <td style='display:none;'>"+resultado[i].idIngresos.id+"</td>\
                                                    <td style='width: 12%' ><button id='btnEditarMedicamento' class='btn btn-primary' '><span class='glyphicon glyphicon-pencil'></span> </button>\n\
                                                        <button id='btnEliminarMedicamento' class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span></a></button>\n\
                                                    </td>\n\
                                                </tr>");
                }
            }
            else
            {
                
                $('#sinRegistro').append(" <div id='sinRegistros' class='alert alert-success'>\n\
                                                    <strong>Sin medicamentos Registrados</strong>\n\
                                                </div>");
            }
           
       });
           
          var cont=0;    
          $(this).parents("tr").find("td").each(function(){
              datos[cont]=$(this).html();   
              cont++;
          });
          
          var id='mantenimientoMedicina';
          $("#"+id).modal('show');
      });
 //Editar Medicamentos
$("#tabMantenimientoIngresos .table-responsive").on("click", "#btnEditarMedicamento", function(){  
          var cont=0;    
          $(this).parents("tr").find("td").each(function(){
              medicinas[cont]=$(this).html();               
              cont++;
              
          });
          idMedicamento=medicinas[0];
          idIngreso=medicinas[5];
          var id='medicinas';
           $('#dtpFechaMedicamentoIngresosModal').val(medicinas[1]);
           $('#dtpHora').val(medicinas[2]);
           $('#txtNotasEvolucion').val(medicinas[3]);
           $('#txtPrescripcion').val(medicinas[4]);           
           limpiarMedicamentos();
           $('#btnGuardarMedicamento').prop('disabled', false);
           $("#"+id).modal('show');
          
      });
//Agregar Medicamento
 $("#tabMantenimientoIngresos .table-responsive").on("click", "#opAgregarMedicina", function(){
         idMedicamento=0;
          var cont=0;    
          $(this).parents("tr").find("td").each(function(){              
              datos[cont]=$(this).html();   
              cont++;
          });
          idIngreso=datos[0];
          limpiarMedicamentos();
          var id='medicinas';
          $.each($("#"+id+" input"), function (){
            $(this).val("");
            
        }); 
         $('#btnGuardarMedicamento').prop('disabled', false);
        $("#tabMantenimientoIngresos #dtpFechaMedicamentoIngresosModal").val('');
        $('#txtNotasEvolucion').val('');
        $('#txtPrescripcion').val('');
          $("#"+id).modal('show');
      });            
//Actualizar - Guardar medicamentos      
 $('#tabMantenimientoIngresos #btnGuardarMedicamento').click(function(event) {          
          if(validaciones())
          {
              $('#btnGuardarMedicamento').prop('disabled', true);
          $.post('sIngresosHospital', {
              
            idIngreso : idIngreso,
            fechaMedicamento: $('#dtpFechaMedicamentoIngresosModal').val(),
            hora: $('#dtpHora').val(),
            notasEvolucion: $('#txtNotasEvolucion').val(),
            prescripcionMedica: $('#txtPrescripcion').val(),              
            opcion:'9',
            idMedicamento:idMedicamento
        }, function(data) { 
            var resultado = JSON && JSON.parse(data) || $.parseJSON(data);   
            console.log(resultado);
            if(resultado.estado===true)
            {
                if(idMedicamento===0)
                alertify.success("Datos registrados correctamente");
                else
                {
                    //indiceMedicamentos
                    $($('#tablaMedicamentos').find('tbody > tr')[indiceMedicamentos]).children('td')[1].innerHTML = $('#dtpFechaMedicamentoIngresosModal').val();
                    $($('#tablaMedicamentos').find('tbody > tr')[indiceMedicamentos]).children('td')[2].innerHTML = $('#dtpHora').val();
                    $($('#tablaMedicamentos').find('tbody > tr')[indiceMedicamentos]).children('td')[3].innerHTML = $('#txtNotasEvolucion').val();
                    $($('#tablaMedicamentos').find('tbody > tr')[indiceMedicamentos]).children('td')[4].innerHTML = $('#txtPrescripcion').val();                
                    alertify.success("Datos actualizados correctamente");
                }
                $("#medicinas").modal('toggle');
            }        
            else
                 alertify.success("Problemas al intentar guardar\n"+resultado.excepcion); 
            
            
        });
        }
      });
//Eliminar Medicamento  
$('#tabMantenimientoIngresos .table-responsive').on("click", "#btnEliminarMedicamento", function(event){ 
        var cont=0;
        $(this).parents("tr").find("td").each(function(){
            datos[cont]=$(this).html();   
            cont++;
        });
        $.post('sIngresosHospital', {
            opcion : 11,
            idMedicamento: datos[0]                                
        }, function(responseText) {              
            alertify.success("Registro eliminado");
        });
        event.preventDefault();
        $(this).closest('tr').remove();
    });    
 
//Indice Seleccionado Medicamentos
 $("#tablaMedicamentos").on("click", "tr", function(){  
         indiceMedicamentos = $(this).index();
         
      });
//Validaciones Medicamentos
function validarMedicamentos()
{
    $.each($("#medicinas textarea[validate='text']"), function (index, value) {  
       $(value).blur(function(){
           validarText(value);
	});
        validarText(value);
    });
    $.each($("#medicinas input[validate='date']"), function (index, value) {
        $(value).change(function(){    		               
                validarDate(value);
	});
        validarDate(value);
        
    }); 
}
function validarDateIngresos()
{
    var value = $("#dtpFechaMedicamentoIngresosModal");
    $("#dtpFechaMedicamentoIngresosModalhelp").remove();     
    if ($(value).val() === null || $(value).val() === "") {
        $(value).closest("div").addClass("has-error");
        $(value).parent("div").after('<span id="' + $(value).attr("id") + 'help" style="color:#a94442;" class="help-block">Sin Fecha</span');
    } else
    {
        $(value).closest("div").removeClass("has-error");
        $("#"+$(value).attr("id") + 'help').remove();
    }
}
$('#txtMedicamentos').blur(function(){    		               
    validarMedicamentos();
});
$('#dtpFechaMedicamentoIngresosModal').blur(function(){    		               
    validarDateIngresos();
});
function validaciones()
{
    $("#tabMantenimientoIngresos .help-block").remove();    
    //validarDateIngresos();
    validarMedicamentos();
    return $("#tabMantenimientoIngresos .help-block").length === 0;    
}
/*-------------------------------Otras-------------------------------------------------------------------------*/           
function ocultarModal()
     {
         $('.modal').on({ 
	            'show.bs.modal': function() {
	                var idx = $('.modal:visible').length;
	                $(this).css('z-index', 1040 + (10 * idx));
	            },
	            'shown.bs.modal': function() {
	                var idx = ($('.modal:visible').length) - 1; // raise backdrop after animation.
	                $('.modal-backdrop').not('.stacked')
	                .css('z-index', 1039 + (10 * idx))
	                .addClass('stacked');
	            },
	            'hidden.bs.modal': function() {
	                if ($('.modal:visible').length > 0) {
	                    // restore the modal-open class to the body element, so that scrolling works
	                    // properly after de-stacking a modal.
	                    setTimeout(function() {
	                        $(document.body).addClass('modal-open');
	                    }, 0);
	                }
	            }
	        });
         
     }

    
     
     
    
     

