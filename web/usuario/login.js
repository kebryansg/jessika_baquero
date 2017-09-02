$(function () {
    //load_rol();
    login();
    /*$('#btnLogin').click(function (e) {
        if (validar())
        {
            login();
        }
    });*/
});

function login() {
    $.ajax({
        url: 'sUsuario',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: {
            op: 'login',
            //usuario: $("#txtUsuario").val(),
            //clave: $("#txtClave").val(),
            usuario: 1234,
            clave: 1234,
            rol : 1
            //rol: $("#cboTipo").selectpicker("val")
        },
        success: function (data) {
            if (data.login === true)
                location.href = "home.jsp";
            else
            {
                alertify.success("Usuario o Clave Incorecta");
                $(":input:first").focus();
            }
        }
    });
}
function load_rol() {
    $.ajax({
        url: "sUsuario",
        type: 'POST',
        async: false,
        data: {
            op: "rol_list"
        },
        success: function (response) {
            $("#cboTipo").html(response);
            $("#cboTipo").selectpicker("refresh");
        }
    });
}

$('#aSalida').click(function (e) {
    location.href = "sUsuario?op=close";
});

$("#txtClave").keyup(function (event) {
    var teclaPulsada = event.keyCode;
    if (teclaPulsada === 13)
    {
        $('#btnLogin').click();
    }
});

function validar() {
    $.each($("input[validate='text']"), function (index, value) {
        $(value).blur(function () {
            validarLogin(value);
        });
        validarLogin(value);
    });
    console.log($(".help-block").length === 0);
    return $(".has-error").length === 0;
}

