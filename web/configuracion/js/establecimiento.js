/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function change_cboProvincia(cbo) {
    $.ajax({
        type: 'Post',
        url: 'pruebaCombo',
        async: false,
        data: {
            id: $(cbo).val(),
            op: 'cantones'
        },
        success: function (response) {
            var cboCanton = $("#cboCanton");
            $(cboCanton).html(response);
            $(cboCanton).selectpicker('refresh');
        }
    });
}

function change_cboCanton(cbo) {
    $.ajax({
        type: 'Post',
        url: 'pruebaCombo',
        async: false,
        data: {
            id: $(cbo).val(),
            op: 'parroquias'
        },
        success: function (response) {
            var cboParroquia = $("#cboParroquia");
            $(cboParroquia).html(response);
            $(cboParroquia).selectpicker('refresh');
        }

    });
}

var id=0;
    function validar() {
        $(".help-block").remove(); 
        var email = $("input[validate='email']");
        if (!validarEmail(email)) {
            $(email).blur(function () {
                validarEmail(email);
            });
        }
        $.each($("input[validate='text']"), function (index, value) {  
            $(value).blur(function(){    		               
                validarText(value);
            });
            validarText(value);
        });    
        $.each($("select[validate='select']"), function (index, value) {
            $(value).on('change', function() { 
                validarSelect(value);
            });        
            validarSelect(value); 
        });    
        console.log($("#tbEstablecimiento .help-block").length);
        return $("#tbEstablecimiento .help-block").length === 0;
    }
$(function () {
     
    $("#establecimiento").on("changed.bs.select", "#cboProvincia", function (e) {
        change_cboProvincia(this);
    });

    $("#establecimiento").on("changed.bs.select", "#cboCanton", function (e) {
        change_cboCanton(this);
    });
    $("#msgAlert").fadeOut();
      
    $.ajax({
        type: 'Post',
        url: 'sEstablecimiento',
        data: {            
            opcion:'1'
        },
        async: false,
        success:function(data){
            var resultado = JSON && JSON.parse(data) || $.parseJSON(data);
            var total=resultado.length;
            //console.log(if(data==="[]"));
            if(data==="[]")
            {
                $("#input-es").fileinput({
                language: "es",
                uploadUrl: "sEstablecimiento?opcion=subirLogo",
                allowedFileExtensions: ["jpg", "png", "gif"],
                uploadAsync: true,
                 showUpload: false
                });
            }
            else
            {
                
                if(resultado[0].logo==="sinLogo")
                {
                    $("#input-es").fileinput({
                        showUpload:false,
                        language: "es",
                        uploadUrl: "sEstablecimiento?opcion=subirLogo",
                        allowedFileExtensions: ["jpg", "png", "gif"],
                        uploadAsync: true,
                       
                        });
                    
                    
                }
                else
                {
                    $("#input-es").fileinput({
                    showUpload:false,
                    language: "es",
                    uploadUrl: "sEstablecimiento?opcion=subirLogo",
                    allowedFileExtensions: ["jpg", "png", "gif"],
                    uploadAsync: true,
                    minFileCount: 1,
                    maxFileCount: 5,
                    overwriteInitial: false,                
                    initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
                    initialPreview: [
                                "/Jessika/configuracion/img/"+resultado[0].logo
                            ],
                    initialPreviewFileType: 'image', // image is the default and can be overridden in config below
                    initialPreviewConfig: [
                        {caption: resultado[0].logo, size: 576237, width: "120px", url: "sEstablecimiento?opcion=eliminar", key: 1}            
                    ],
                    uploadExtraData: {
                        img_key: "1000",
                        img_keywords: "happy, places",
                    }
                });
            }
        }
        for(i=0;i <resultado.length; i++)
        {
            id=1;
            $("#txtEstablecimiento").val(resultado[i].nombre);
            $("#txtEncargado").val(resultado[i].encargado);                
            $("#txtDireccion").val(resultado[i].direccion);
            $("#txtTelefono").val(resultado[i].telefono);
            $("#txtEmail").val(resultado[i].email);
            /* Carga y asigna provincia,canton y parroquia */
             $.ajax({
                type: 'POST',
                url: 'sPaciente',
                async: false,
                data: {
                    idParroquia: resultado[i].parroquia,
                    op: "det"
                },
                success: function (response) {
                    var det = $.parseJSON(response);
                    $('#cboProvincia').selectpicker('val', det.provincia);
                    //$('#cboProvincia > option[value="' + det.provincia + '"]').attr('selected', true);
                    change_cboProvincia($('#cboProvincia'));
                    $('#cboCanton').selectpicker('val', det.canton);
                    //$('#cboCanton > option[value="' + det.canton + '"]').attr('selected', true);
                    change_cboCanton($('#cboCanton'));
                    $('#cboParroquia').selectpicker('val', resultado[i].parroquia);
                    //$('#cboParroquia > option[value="' + paciente.idParroquia.id + '"]').attr('selected', true);
                }
            });
        }
    }
});

   

});
$('#btnGuardar').click(function(event) { 
    if(validar())
    {
        var formData = new FormData();
        if($('#input-es').val()=='')
        {
             formData.append("logo","sinLogo");             
        }
        else
        {
            formData.append("logo",$('input[type=file]')[0].files[0]);            
        }
    
    formData.append("establecimiento", $("#txtEstablecimiento").val());
    formData.append("encargado", $("#txtEncargado").val()); // number 123456 is immediately converted to string "123456"
    formData.append("parroquia",$("#cboParroquia").val());
    formData.append("direccion", $("#txtDireccion").val());
    formData.append("telefono", $("#txtTelefono").val());
    formData.append("correo", $("#txtEmail").val()); 
    formData.append("id", id); 
    //// HTML file input user's choice...
    
    //// JavaScript file-like object...   
    var request = new XMLHttpRequest();
    $.ajax({
            type: 'Post',
            url: 'sEstablecimiento',
            data: formData,
            processData: false,
            contentType: false,
            success:function(data){ 
                 //var resultado = JSON && JSON.parse(data) || $.parseJSON(data); 
                 //console.log(resultado);
                $("#msgAlert").fadeOut();            
                if(id==1)
                {
                    $("#msgAlert").empty();
                    $("#msgAlert").append("\n\
                                            <center><strong class='test'>¡Información!</strong> Datos Actualizados correctamente</center>");
                }
                id=1;
                 
                $("#msgAlert").fadeIn();
                
                //alertify.success("Datos registrados");
                
            }
        });
    }
    else
    {
        $("#msgAlert").fadeOut();
    }
    
});
function isEmptyJSON(obj) {
  for(var i in obj) { return false; }
  return true;
}

