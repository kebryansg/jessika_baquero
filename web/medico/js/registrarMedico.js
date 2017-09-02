/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var bandera=0;
    function validar() {
    /* Validacion de email */
   var email = $("#tabMedicoRegistro input[validate='email']");
    if (!validarEmail(email)) {
        $(email).blur(function () {
            validarEmail(email);
        });
    }
    $.each($("#tabMedicoRegistro input[validate='text']"), function (index, value) {  
       $(value).blur(function(){    		               
                validarText(value);
	});
        validarText(value);
    });    
    $.each($("#tabMedicoRegistro select[validate='select']"), function (index, value) {
        $(value).on('change', function() { 
             validarSelect(value);
        });        
       validarSelect(value); 
    });
    $.each($("#tabMedicoRegistro input[validate='date']"), function (index, value) {
        if ($(value).val() === null || $(value).val() === "") {
            $(value).closest("div").addClass("has-error");
            $(value).parent("div").after('<span id="' + $(value).attr("id") + 'help" style="color:#a94442;" class="help-block">Sin Fecha</span');
        } else
        {
            $(value).closest("div").removeClass("has-error");
        }
    });
 
    return $("#tabMedicoRegistro .help-block").length === 0;
}
function validarCedula()
    {
       
       if($("#txtCedula").val()!=="" )
         $.ajax({
            type: 'Post',
            url: 'sMedico',
            data: {
                cedula : $('#tabMedicoRegistro #txtCedula').val(),                
                opcion:'6'
            },
            async: false,
            success:function(response){
                $("#tabMedicoRegistro #txtCedulahelp").remove();  
                if(response>0)
                {   
                    
                    $("#tabMedicoRegistro #txtCedula").closest("div").addClass("has-error");                    
                    $("#tabMedicoRegistro #txtCedula").after('<span id="' + $("#txtCedula").attr("id") + 'help" class="help-block">Cédula ya registrada</span');
                    
                }
                else 
                {
                    
                    $("#tabMedicoRegistro #txtCedula").closest("div").removeClass("has-error");
                                       
                    
                }
            }
        }); 
    }
    function limpiar()
    {
       
        $('#tabMedicoRegistro :text').val('');
       // $(':email').val('');
        $('#tabMedicoRegistro textarea').val('');
        $("#tabMedicoRegistro #txtEmail").val('');
        $('#tabMedicoRegistro .selectpicker').selectpicker('deselectAll');
        $.each($("#tabMedicoRegistro select[validate='select']"), function (index, value) {
            $(value).closest("div").removeClass("has-error");
        });
        $.each($("#tabMedicoRegistro :text"), function (index, value) {             
              $(value).off("blur");
               
           });
    $("#tabMedicoRegistro .help-block").remove();

    }
    limpiar();
    $('#tabMedicoRegistro #btnGuardar').click(function(event) {
        if (validar()) {
        $.ajax({
            type: 'Post',
            url: 'sMedico',
            data: {
                cedula : $('#tabMedicoRegistro #txtCedula').val(),
                primerNombre: $('#tabMedicoRegistro #txtPrimerNombre').val(),
                segundoNombre: $('#tabMedicoRegistro #txtSegundoNombre').val(),
                primerApellido: $('#tabMedicoRegistro #txtPrimerApellido').val(),
                segundoApellido: $('#tabMedicoRegistro #txtSegundoApellido').val(),
                domicilio: $('#tabMedicoRegistro #txtDomicilio').val(),
                ciudad: $('#tabMedicoRegistro #txtCiudad').val(),
                telefonoOficina: $('#tabMedicoRegistro #txtTelefonoOficina').val(),
                email: $('#tabMedicoRegistro #txtEmail').val(),
                telefonoDomicilio: $('#tabMedicoRegistro #txtTelefonoDomicilio').val(),
                telefonoMovil: $('#tabMedicoRegistro #txtTelefonoMovil').val(),
                idEspecialidad: $("#tabMedicoRegistro #cboEspecialidad").val(),
                visible:'1',
                opcion:'0'
            },
            async: false,
            success:function(data){ 
                var resultado = JSON && JSON.parse(data) || $.parseJSON(data);
                console.log(data);
                if(resultado.estado===true)
                {
                    alertify.success("Médico registrado");
                }  
                else
                    alertify.success("Problemas al guardar\n"+resultado.excepcion);
                limpiar();
            }
        }); 
    }
    });



