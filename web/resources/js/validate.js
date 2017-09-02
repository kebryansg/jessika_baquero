function isNull(val){
    return jQuery.type(val) === "undefined";
}
function validarText(value) {
    var bandera = true;
    var valor = "#" + $(value).attr("id") + 'help';
    $(valor).remove();
    if ($(value).val() === null || $(value).val() === "") {

        $(value).closest("div").addClass("has-error");
        $(value).after('<span id="' + $(value).attr("id") + 'help" class="help-block">Campo Vacio</span');
        bandera = false;
    } else
    {
        $(value).closest("div").removeClass("has-error");
    }
    return bandera;
}

function validarSelect(value) {
    var bandera = true;
    var valor = "#" + $(value).attr("id") + 'help';
    $(valor).remove();
    if ($(value).val() === "0" || $(value).val() === null) {
        $(value).closest(".form-group").addClass("has-error");
        $(value).after('<span id="' + $(value).attr("id") + 'help" class="help-block">Sin seleccionar</span');
        $(value).closest(".form-group").addClass("error_input");
        bandera = false;
    } else
    {
        $(value).closest(".form-group").removeClass("has-error");
        $(value).closest(".form-group").removeClass("error_input");
    }
    return bandera;
}
function validarDate(value) {
    var bandera = true;
    var valor = "#" + $(value).attr("id") + 'help';
    $(valor).remove();
    if ($(value).val() === null || $(value).val() === "") {
        $(value).closest("div").addClass("has-error");
        $(value).parent("div").after('<span id="' + $(value).attr("id") + 'help" style="color:#a94442;" class="help-block">Sin Fecha</span');
        bandera = false;
    } else
    {
        $(value).closest("div").removeClass("has-error");
    }
    return bandera;
}
function validarEmail(value) {
    var bandera = true;
    var validacion_email = /^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/;
    //var email = $("#tabPacientes input[validate='email']");
    if ($(value).val() !== "") {
        if (!validacion_email.test($(value).val())) {
            $(value).closest("div").addClass("has-error");
            $(value).after('<span id="' + $(value).attr("id") + 'help" class="help-block">Email no valido.</span');
            bandera = false;
        } else {
            $(value).closest("div").removeClass("has-error");
        }
    }
    return bandera;
}

function validarCedula(value) {
    var bandera = true;
    var valor = "#" + $(value).attr("id") + 'help';
    $(valor).remove();
    if ($(value).val() === null || $(value).val() === "" || $(value).val().length < 10) {

        $(value).closest("div").addClass("has-error");
        $(value).after('<span id="' + $(value).attr("id") + 'help" class="help-block">Campo Vacio</span');
        bandera = false;
    } else
    {
        $(value).closest("div").removeClass("has-error");
    }
    return bandera;
}
function validarLogin(value) {
    var bandera = true;
    var valor = "#" + $(value).attr("id") + 'help';
    $(valor).remove();
    if ($(value).val() === null || $(value).val() === "") {

        $(value).closest("div").addClass("has-error");
        //$(value).after('<span id="' + $(value).attr("id") + 'help" class="help-block">Campo Vacio</span');
        bandera = false;
    } else
    {
        $(value).closest("div").removeClass("has-error");
    }
    return bandera;
}
function remover(value)
{
    $(value).closest("div").removeClass("has-error");
}
function fechaMayorQue(fechaInicial, fechaFinal)
{
    value = fechaInicial;
    var valor = "#" + $(value).attr("id") + 'help';
    $(valor).remove();
    fechaInicial = moment(fechaInicial.val()).format('DD/MM/YYYY');
    fechaFinal = moment(fechaFinal.val()).format('DD/MM/YYYY');

    valuesStart = fechaInicial.split("/");
    valuesEnd = fechaFinal.split("/");
    var dateStart = new Date(valuesStart[0], (valuesStart[1] - 1), valuesStart[2]);
    var dateEnd = new Date(valuesEnd[0], (valuesEnd[1] - 1), valuesEnd[0]);
    console.log(dateStart, dateEnd);
    if (dateStart >= dateEnd)
    {
        $(value).closest("div").removeClass("has-error");
        return 0;
    }

    $(value).closest("div").addClass("has-error");
    $(value).after('<span id="' + $(value).attr("id") + 'help" class="help-block">Rango No Válido</span');
    return 1;
}