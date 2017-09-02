/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  
     //obtengo el total de registros
     //arreglo con las peticiones
var xhrRequest=[];
var totalRegistros=0;
var totalPaginas=0;
var paginasVisibles=5;
var paginaActual=0;
var buscar=0;
var pagina=0;
var datos = [];
var idTablaSeleccionada=-1;     
var indice=-1;
var ultimo=-1;
var filas=5;

$("#tabMedicoEditar #txtBuscar").text("");
$("#tabMedicoEditar #cboMostrar").val(5);     
//cargarMedicos(0);

function validar(id) {
    var email = $("#tabMedicoEditar input[validate='email']");
    $(email).blur(function(){
        validarText(email);
    });
    validarEmail(email);
    /* Validacion de email */
    $.each($("#tabMedicoEditar input[validate='text']"), function (index, value) {  
   // $.each($("#optionEditarMedico[data-id='" + id + "'] #tabMedicoEditar input[validate='text']"), function (index, value) {    
        $(value).blur(function(){
            validarText(value);
        });
        validarText(value);
    });
    $.each($("#tabMedicoEditar select[validate='select']"), function (index, value) {
    //$.each($("#optionEditarMedico[data-id='" + id + "'] #tabMedicoEditar select[validate='select']"), function (index, value) {        
        $(value).on('change', function() { 
            validarSelect(value);
        });
        validarSelect(value); 
    });
    $.each($("#tabMedicoEditar input[validate='date']"), function (index, value) {
    //$.each($("#optionEditarMedico[data-id='" + id + "'] #tabMedicoEditar select[validate='date']"), function (index, value) {
        if ($(value).val() === null || $(value).val() === "") {
            $(value).closest("div").addClass("has-error");
            $(value).parent("div").after('<span id="' + $(value).attr("id") + 'help" style="color:#a94442;" class="help-block">Sin Fecha</span');
        } else
        {
            $(value).closest("div").removeClass("has-error");
        }
    });
    return $("#tabMedicoEditar .help-block").length === 0;
}

function validarCedula()
{
    $.ajax({
        type: 'Post',
        url: 'sMedico',
        data: {
            cedula : $('#txtCedula').val(),
            opcion:'6'
        },
        async: false,
        success:function(response){
            $("#txtCedulahelp").remove();
            if(response>0)
            {   
                $("#txtCedula").closest("div").addClass("has-error");
                $("#txtCedula").after('<span id="' + $("#txtCedula").attr("id") + 'help" class="help-block">Cédula ya registrada</span');                
            }
            else 
            {
                $("#txtCedula").closest("div").removeClass("has-error");
            }
        }
    }); 
}
$("#tabMedicoEditar #txtBuscar").keyup(function(event){
    if($("#tabMedicoEditar #txtBuscar").val().length < 1) 
    {
        buscar=0;
    }
    else
    {
        buscar=1;
    }
    pagina=0;
    cargarMedicos(0);
});         
//Funcion para cargar los medicos de forma paginada
function cargarMedicos(pagina)
{
    $.each(xhrRequest,function(idx, jqXHR)
    {
        jqXHR.abort();
    });
    var totalRegistro=$("#tabMedicoEditar #cboMostrar").val();
    var xhr=null;
    xhr=$.post('sMedico', {
        totalMostrar : totalRegistro,
        pagina: pagina,
        opcion: '2',
        bandera:buscar,
        buscar:$("#tabMedicoEditar #txtBuscar").val()
    }, 
    function(data) {   
         
        $('#tabMedicoEditar #tablaMedico tr').remove();
        $('#tabMedicoEditar #paginacionMedico').find('li').remove();
        var resultado = JSON && JSON.parse(data) || $.parseJSON(data); 
        var totalPaginas=resultado[0].registros/$("#cboMostrar").val();
        totalPaginas=Math.ceil(totalPaginas); 
        console.log(totalPaginas);
        filas=resultado.length;
        $("#tabMedicoEditar #paginacionMedico ul").append('<li id="atras"><a  href="#">&laquo;</a></li>');
        var indice=0;
        for(i=0;i <totalPaginas; i++)                
        {
            indice=parseInt(i)+1;
            if(i==pagina)
                $("#tabMedicoEditar #paginacionMedico ul").append('<li id='+i+' class="active"><a href="#">'+indice+'</a></li>');
            else 
                $("#tabMedicoEditar #paginacionMedico ul ").append('<li id='+i+'><a  href="#">'+indice+'</a></li>');
        }
        ultimo=parseInt(totalPaginas)-1;
        
        $("#tabMedicoEditar #paginacionMedico ul").append('<li id="adelante"><a href="#">&raquo;</a></li>');
        $('#tabMedicoEditar #tablaMedico thead').append("<tr>\n\<th >No.</th>\n\                                                \n\
                                            <th>Cédula</th>\n\
                                               <th >Apellidos y Nombres</th>\n\
\n\                                             <th >Domicilio</th>\n\
                                               <th style='display:none;'>Ciudad</th>\n\
                                               <th style='display:none;'>Teléf. Domicilio</th>\n\
                                               <th style='display:none;'>Teléf. Oficina</th>\n\
                                               <th>Teléf. Movil</th>\n\
                                               <th style='display:none;' >Email</th>\n\
                                               <th style='display:none;'>Estado</th>\n\
                                               <th >Acci&oacute;n</th>");
        for(i=0;i <resultado.length; i++)
        {
            $('#tabMedicoEditar #tablaMedico').append("<tr>\n\
                                               <td>"+resultado[i].id+"</td>\n\
                                               <td>"+resultado[i].cedula+"</td>\n\
                                               <td>"+resultado[i].apellidos1+ ' '+resultado[i].apellidos2+" "+resultado[i].nombre1+" "+resultado[i].nombre2+"</td>\n\
\n\                                             <td>"+resultado[i].domicilio+"</td>\n\
                                               <td style='display:none;'>"+resultado[i].ciudad+"</td>\n\
                                               <td  style='display:none;'>"+resultado[i].telefonoDomicilio+"</td>\n\
                                               <td  style='display:none;'>"+resultado[i].telefonoOficina+"</td>\n\
                                               <td>"+resultado[i].telefonoMovil+"</td>\n\
                                               <td style='display:none;'>"+resultado[i].email+"</td>\n\
                                               <td style='display:none;'>"+resultado[i].visible+"</td>  \n\
                                               <td >     \n\
                                                    <button id='botonEditar' class='btn btn-primary'><span class='glyphicon glyphicon-pencil'></span> </button> \n\
                                                    <button id='btnEliminar' class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span></a></button>\n\
                                                    </td>\n\
                                     </tr>");
        }
    });    
    xhrRequest.push(xhr);
}

$('#tabMedicoEditar #paginacionMedico ul').click(function (e) {
    var a = e.target.parentNode;
    if(a.id!=="adelante" && a.id!=="atras")
        pagina=a.id;
    if(a.id==="adelante"  && pagina<ultimo)    
        pagina=parseInt(pagina)+1;            
    if(a.id==="atras" && pagina>0)
        pagina=parseInt(pagina)-1;
    cargarMedicos(pagina);
    
});

$('#tabMedicoEditar #cboMostrar').on('change', function() {   
    pagina=0;
    cargarMedicos(pagina);
    filas=$("#tabMedicoEditar #cboMostrar").val();
});
//porque las creo de forma dinamicas    
$("#tabMedicoEditar #tablaMedico").on("click", "#botonEditar", function(){   
    var cont=0;
    $(this).parents("tr").find("td").each(function(){
        datos[cont]=$(this).html();   
        //alert(datos[cont]);
        cont++;
    });
    //var nombres=datos[3];
    var apellidos=datos[2];    
    var res = apellidos.split(" ");
    $('#txtCedulaModal').val(datos[1]);
    $('#txtPrimerApellidoModal').val(res[0]);
    $('#txtSegundoApellidoModal').val(res[1]);
    $('#txtPrimerNombreModal').val(res[2]);
    $('#txtSegundoNombreModal').val(res[3]);
    $('#txtDomicilioModal').val(datos[3]);
    $('#txtCiudadModal').val(datos[4]);
    $('#txtTelefonoDomicilioModal').val(datos[5]);
    $('#txtTelefonoOficinaModal').val(datos[6]);
    $('#txtTelefonoMovilModal').val(datos[7]);
    $('#txtEmailModal').val(datos[8]);
    $('select[id=cboEstadoModal]').val(datos[9]);
    $.ajax({
        type: 'Post',
        url: 'sMedico',
        data: {
            idMedico: datos[0],
            opcion: '1'
        },
        async: false,
        success:function(data){
            var resultado = JSON && JSON.parse(data) || $.parseJSON(data);
            for(i=0;i <resultado.length; i++)
            {
                $("#tabMedicoEditar #cboEspecialidades option[value="+ resultado[i].id +"]").attr("selected",true);
                $('#tabMedicoEditar .selectpicker').selectpicker('refresh');
            }
        }
    });
    $.each($("#tabMedicoEditar input[validate='text']"), function (index, value) {  
        remover(value);
    });
     var email = $("#tabMedicoEditar input[validate='email']");
     remover(email);
     $.each($("#tabMedicoEditar input[validate='date']"), function (index, value) {
        remover(value);
     });
     $.each($("#tabMedicoEditar select[validate='select']"), function (index, value) {
         remover(value) ;
     });
     $('#btnActualizar').prop('disabled', false);
    $("#tabMedicoEditar .help-block").remove();
    var id='modalEditarMedico';
    $("#"+id).modal('show');
});
$('#tabMedicoEditar #btnActualizar').click(function(event) {
    if (validar(1)) {
         $('#btnActualizar').prop('disabled', true);
        $.post('sMedico', {
            cedula : $('#txtCedulaModal').val(),
            primerNombre: $('#txtPrimerNombreModal').val(),
            segundoNombre: $('#txtSegundoNombreModal').val(),
            primerApellido: $('#txtPrimerApellidoModal').val(),
            segundoApellido: $('#txtSegundoApellidoModal').val(),
            domicilio: $('#txtDomicilioModal').val(),
            ciudad: $('#txtCiudadModal').val(),
            telefonoOficina: $('#txtTelefonoOficinaModal').val(),
            email:  $('#txtEmailModal').val(),
            telefonoDomicilio: $('#txtTelefonoDomicilioModal').val(),
            telefonoMovil:  $('#txtTelefonoMovilModal').val(),
            idEspecialidad: $("#cboEspecialidades").val(),
            visible:$("#cboEstadoModal").val(),
            opcion:'3',
            idMedico: datos[0]
        }, function(responseText) {   
            $($('#tablaMedico').find('tbody > tr')[indice]).children('td')[1].innerHTML = $('#txtCedulaModal').val();
            $($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[2].innerHTML = $('#txtPrimerApellidoModal').val()+' '+$('#txtSegundoApellidoModal').val()+' '+$('#txtPrimerNombreModal').val()+' '+$('#txtSegundoNombreModal').val() ;
            //$($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[3].innerHTML = ;
            $($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[3].innerHTML =$('#txtDomicilioModal').val();
            $($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[4].innerHTML =$('#txtCiudadModal').val();
            $($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[5].innerHTML =$('#txtTelefonoDomicilioModal').val();
            $($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[6].innerHTML =$('#txtTelefonoOficinaModal').val();
            $($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[7].innerHTML =$('#txtTelefonoMovilModal').val();
            $($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[8].innerHTML =$('#txtEmailModal').val();
            $($('#tabMedicoEditar .table-responsive').find('tbody > tr')[indice]).children('td')[9].innerHTML =$("#cboEstadoModal").val();
            alertify.success("Datos Actualizados correctamente");
            var cont=0;     
            $("#tabMedicoEditar #modalEditarMedico").modal('toggle');
        });
    }
});

$("#tabMedicoEditar .table-responsive").on("click", "tr", function(){  
    indice = $(this).index();
});
$('#tabMedicoEditar .table-responsive').on("click", "#btnEliminar", function(event){ 
    var cont=0;
    $(this).parents("tr").find("td").each(function(){
        datos[cont]=$(this).html();   
        cont++;
    });
    $.post('sMedico', {
        idMedico : datos[0],
        opcion: 5
    }, function(data) {         
        var resultado = JSON && JSON.parse(data) || $.parseJSON(data);   
        console.log(resultado);
        if(resultado.estado===true)
        {
            filas--;
            if(filas===0)
            {
                if(pagina>0)
                    pagina--;
                filas=$("#tabMedicoEditar #cboMostrar").val();
            }
            cargarMedicos(pagina);
            alertify.success("Registros Eliminado");
        }        
        else
            alertify.success("Error al intentar eliminar \n"+resultado.excepcion);
    });
    event.preventDefault();
    $(this).closest('tr').remove();
});

    
