/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var s=0;

// alert($('label[for='+  elemento.id  +']').text());
                //alert("elemento.name="+ elemento.name + ", elemento.value=" + elemento.value); 
function validar() {
        $(".help-block").remove();         
        $.each($("input[validate='text']"), function (index, value) {  
            $(value).blur(function(){    		               
                validarText(value);
            });
            validarText(value);
        });    
        return $(".help-block").length === 0;
    }                
$('#btnGuardar').click(function(event) {
    
    if(validar())
    {
        
        $.ajax({
            type: 'Post',
            url: 'sCamas',
            data: {
                opcion:'2',
                s: s,
                medicinaInternaIndividual: $('#txtMedicinaInternaIndividual').val(),
                medicinaInternaDoble:$('#txtMedicinaInternaDoble').val(),
                cirugiaIndividual:$('#txtCirugiaIndividual').val(),
                cirugiaDoble:$('#txtCirugiaDoble').val(),
                ginecologiaIndividual:$('#txtGinecologiaIndividual').val(),
                ginecologiaDoble:$('#txtGinecologiaDoble').val(),
                pediatriaIndividual:$('#txtPediatriaIndividual').val(),
                pediatriaDoble:$('#txtPediatriaDoble').val(),
                cardiologiaIndividual:$('#txtCardiologiaIndividual').val(),
                cardiologiaDoble:$('#txtCardiologiaDoble').val(),
                neumologiaIndividual:$('#txtNeumologiaIndividual').val(),
                neumologiaDoble:$('#txtNeumologiaDoble').val(),
                psiquiatriaIndividual:$('#txtPsiquiatriaIndividual').val(),
                psiquiatriaDoble:$('#txtPsiquiatriaDoble').val(),
                traumatologiaIndividual:$('#txtTraumotologiaIndividual').val(),
                traumatologiaDoble:$('#txtTraumotologiaDoble').val(),
                infectologiaIndividual:$('#txtInfectologiaIndividual').val(),
                infectologiaDoble:$('#txtInfectologiaDoble').val(),
                oftalmologiaIndividual:$('#txtOftalmologiaIndividual').val(),
                oftalmologiaDoble:$('#txtOftalmologiaDoble').val(),
                urologiaIndividual:$('#txtUrologiaIndividual').val(),
                urologiaDoble:$('#txtUrologiaDoble').val(),
                gastroenterologiaIndividual:$('#txtGastroenterologiaIndividual').val(),
                gastroenterologiaDoble:$('#txtGastroenterologiaDoble').val(),
                emergencia:$('#txtEmergencia').val(),
                cuidadosIntensivos:$('#txtCuidadosIntensivos').val(),                
            },        
        success:function(data){ 
            
             var resultado = JSON && JSON.parse(data) || $.parseJSON(data);
             console.log(resultado);
             if(resultado.estado===true)
             {                 
                 if(s==0)
                     alertify.success("Datos registrados correctamente");
                 else
                     alertify.success("Datos actualizados correctamente");
             }        
            else
                 alertify.success("Problemas al intentar guardar\n"+resultado.excepcion);
            s=1;
            
        }
    });
    }
});
$(function () {    
    $.ajax({
        type: 'Post',
        url: 'sCamas',
        data: {
            opcion:'1'
        },
        async: false,
        success:function(data){ 
            var resultado = JSON && JSON.parse(data) || $.parseJSON(data);
            for(i=0;i <resultado.length; i++)
            {
                s=1;
                $("#txtMedicinaInternaIndividual").val(resultado[i].medicinaInternaIndividual);
                $("#txtMedicinaInternaDoble").val(resultado[i].medicinaInternaDoble);
                $("#txtCirugiaIndividual").val(resultado[i].cirugiaIndividual);
                $("#txtCirugiaDoble").val(resultado[i].cirugiaDoble);
                $("#txtGinecologiaIndividual").val(resultado[i].ginecologiaYobstetriciaIndividual);
                $("#txtGinecologiaDoble").val(resultado[i].ginecologiaYobstetriciaDoble);
                $("#txtPediatriaIndividual").val(resultado[i].pediatriaIndividual);
                $("#txtPediatriaDoble").val(resultado[i].pediatriaDoble);
                $("#txtCardiologiaIndividual").val(resultado[i].cardiologiaIndividual);
                $("#txtCardiologiaDoble").val(resultado[i].cardiologiaDoble);
                $("#txtNeumologiaIndividual").val(resultado[i].neumologiaIndividual);
                $("#txtNeumologiaDoble").val(resultado[i].neumologiaDoble);
                $("#txtPsiquiatriaIndividual").val(resultado[i].psiquiatriaIndividual);
                $("#txtPsiquiatriaDoble").val(resultado[i].psiquiatriaDoble);
                $("#txtTraumotologiaIndividual").val(resultado[i].traumatologiaIndividual);
                $("#txtTraumotologiaDoble").val(resultado[i].traumotologiaDoble);
                $("#txtInfectologiaIndividual").val(resultado[i].infectologiaIndividual);
                $("#txtInfectologiaDoble").val(resultado[i].infectologiaDoble);
                $("#txtOftalmologiaIndividual").val(resultado[i].oftalmologiaIndividual);
                $("#txtOftalmologiaDoble").val(resultado[i].oftalmologiaDoble);
                $("#txtUrologiaIndividual").val(resultado[i].urologiaIndividual);
                $("#txtUrologiaDoble").val(resultado[i].urologiaDoble);
                $("#txtGastroenterologiaIndividual").val(resultado[i].gastroenterologiaIndividual);
                $("#txtGastroenterologiaDoble").val(resultado[i].gastroenterologiaDoble);
                $("#txtEmergencia").val(resultado[i].emergencia);
                $("#txtCuidadosIntensivos").val(resultado[i].cuidadosIntensivos);
                
            }
        }
    }); 
});

/*$.ajax({
    type: 'Post',
    url: 'sCamas',
    data: { 
        s: s,
        opcion: 2,
        medicinaInternaIndividual: $('#txtMedicinaInternaIndividual').val(),
        medicinaInternaDoble:$('#txtMedicinaInternaDoble').val(),
        cirugiaIndividual:$('#txtCirugiaIndividual').val(),
        cirugiaDoble:$('#txtCirugiaDoble').val(),
        ginecologiaIndividual:$('#txtGinecologiaIndividual').val(),
        ginecologiaDoble:$('#txtGinecologiaDoble').val(),
        pediatriaIndividual:$('#txtPediatriaIndividual').val(),
        pediatriaDoble:$('#txtPediatriaDoble').val(),
        cardiologiaIndividual:$('#txtCardiologiaIndividual').val(),
        cardiologiaDoble:$('#txtCardiologiaDoble').val(),
        neumologiaIndividual:$('#txtNeumologiaIndividual').val(),
        neumologiaDoble:$('#txtNeumologiaDoble').val(),
        psiquiatriaIndividual:$('#txtPsiquiatriaIndividual').val(),
        psiquiatriaDoble:$('#txtPsiquiatriaDoble').val(),
        traumatologiaIndividual:$('#txtTraumotologiaIndividual').val(),
        traumatologiaDoble:$('#txtTrtaumotologiaDoble').val(),
        infectologiaIndividual:$('#txtInfectologiaIndividual').val(),
        infectologiaDoble:$('#txtInfectologiaDoble').val(),
        oftalmologiaIndividual:$('#txtOftalmologiaIndividual').val(),
        oftalmologiaDoble:$('#txtOftalmologiaDoble').val(),
        urologiaIndividual:$('#txtUrologiaIndividual').val(),
        urologiaDoble:$('#txtUrologiaDoble').val(),
        gastroenterologiaIndividual:$('#txtGastroenterologiaIndividual').val(),
        gastroenterologiaDoble:$('#txtGastroenterologiaDoble').val(),
        emergencia:$('#txtEmergencia').val(),
        cuidadosIntensivos:$('#txtCuidadosIntensivos').val(),                
    },
    async: false,
    success:function(data){ 
        
    }
});
*/
