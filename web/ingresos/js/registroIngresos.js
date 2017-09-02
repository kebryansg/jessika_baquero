var xhrRequest = [];
var idHistoria = 0;
var totalRegistros = 0;
var totalPaginas = 0;
var paginasVisibles = 5;
var paginaActual = 1;
var buscar = 0;
var pagina = 0;
var ultimo = -1;
$("#tabIngresos #cboMostrar").val(5);

$("#myModal .modal-body").load("paciente/listPacientes.jsp", function (e) {
    $("#tablPaciente").on('dbl-click-row.bs.table', function (e, row, $element) {
        //$("#con_historiaPaciente").val(row.hc);
        //$("#con_cedulaPaciente").val(row.cedula);
        $("#txtPaciente").val(row.nombres);
        //$("#con_sexoPaciente").val(row.sexo);
        //obtList();
        
        idHistoria = row.hc;
        $("#tabIngresos #txtCedula").val(row.cedula);       
        $('#tabIngresos #txtCedula').closest("div").removeClass("has-error");
        $("#tabIngresos #txtCedulahelp").remove();
        $("#myModal").modal("toggle");
    });
});

limpiar();
$("#tabIngresos #txtBuscar").keyup(function (event) {
    if ($("#txtBuscar").val().length < 1)
        buscar = 0;
    else
        buscar = 1;
    pagina = 0;
    cargarPacientes(pagina, buscar);
});
function validarIngreso()
{
    $("#tabIngresos .help-block").remove();
    $.each($("#tabIngresos input[validate='date']"), function (index, value) {
        $(value).change(function () {
            validarDate(value);
        });
        validarDate(value);

    });
    var codigo = $("#tabIngresos #txtCodigoCie");
    $('#tabIngresos #txtCodigoCie').blur(function () {
        validarText(codigo);
    });
    validarText(codigo);
    var definitivo = $("#tabIngresos #txtDefinitivoEgreso");
    validarText(definitivo);
    $('#tabIngresos #txtDefinitivoEgreso').blur(function () {
        validarText(definitivo);
    });
    if (idHistoria == 0)
    {
        $('#tabIngresos #txtCedula').closest("div").addClass("has-error");
        $('#tabIngresos #txtCedula').after('<span id="' + $('#tabIngresos #txtCedula').attr("id") + 'help" class="help-block">Cargar un paciente</span');
    } else
    {
        $('#tabIngresos #txtCedula').closest("div").removeClass("has-error");
    }
    return $("#tabIngresos .help-block").length === 0;
}


$('#btnBuscar').click(function (event) {
    //cargarPacientes(pagina,buscar);
    $("#myModal").modal('show');
});
$('#tabIngresos #cboMostrar').on('change', function () {
    pagina = 0;
    cargarPacientes(pagina, buscar);
});
function limpiar()
{
    $(':text').val('');
    $('textarea').val('');
    $.each($("#tabIngresos input[validate='date']"), function (index, value) {
        $(value).off("blur");
    });
    $("#tabIngresos #txtDefinitivoEgreso").off("blur");
    remover($("#tabIngresos #txtCodigoCie"));
    remover($("#tabIngresos #txtDefinitivoEgreso"));
    $("#tabIngresos .help-block").remove();
}


$('#tabIngresos #btnCargar').click(function (event) {
    var cedula = $("#tabIngresos #txtCedula").val();
    $.ajax({
        type: 'Post',
        url: 'sIngresosHospital',
        data: {
            opcion: '1',
            cedula: cedula
        },

        async: false,
        success: function (data) {
            //shows the relevant data of your login result object in json format                
            var resultado = JSON && JSON.parse(data) || $.parseJSON(data);
            //alert(loginResult.id);
            if (resultado.id == 0)
            {
                $("#tabIngresos #txtCedula").focus();
                alertify.success("Paciente no encontrado");
            } else
            {
                $("#tabIngresos #txtPaciente").val(resultado.paciente);
                idHistoria = resultado.id;
                $('#tabIngresos #txtCedula').closest("div").removeClass("has-error");
            }
        }
    });
});



function cargarPacientes(pagina, esBuscar)
{
    $.each(xhrRequest, function (idx, jqXHR)
    {
        jqXHR.abort();
    });
    var totalRegistro = $("#tabIngresos #cboMostrar").val();
    var xhr = null;
    xhr = $.post('sIngresosHospital', {
        totalMostrar: totalRegistro,
        pagina: pagina,
        opcion: '4',
        bandera: esBuscar,
        buscar: $("#tabIngresos #txtBuscar").val()
    }, function (data) {
        $('#tabIngresos #paginacionBuscarIngresos').find('li').remove();
        $('#tabIngresos #tablaPacientes tr').remove();
        $('#tabIngresos #tablaPacientes thead').append("<tr>\n\
                                                <th style='display: none'></th>\n\
                                                <th class='col-lg-1'>No.</th>\n\
                                                <th class='col-lg-3'>Cédula</th>\n\
                                                <th class='col-lg-8'>Apellidos y Nombres</th>\n\
\n\                                             <th class='col-lg-4'>Opción</th>\n\
                                             </tr>");
        var resultado = JSON && JSON.parse(data) || $.parseJSON(data);
        for (i = 0; i < resultado.length; i++)
        {
            $('#tabIngresos #tablaPacientes ').append("<tr>\n\
                                                <td style='display: none'>" + resultado[i].idPaciente + "</td>\n\
                                                <td>" + resultado[i].id + "</td>\n\
                                                <td>" + resultado[i].cedula + "</td>\n\
                                                <td>" + resultado[i].apellido1 + " " + resultado[i].apellido2 + " " + resultado[i].nombre1 + " " + resultado[i].nombre2 + "</td>\n\
\n\                                         <td style='width: 20%' ><button id='btnSeleccionar' type=\"button\" class='btn btn-primary'><span>Seleccionar</span> </button></td>\n\
                                             </tr>");
        }
    });
    xhrRequest.push(xhr);

}

$('#paginacionBuscarIngresos ul').click(function (e) {
    var a = e.target.parentNode;
    if (a.id !== "adelante" && a.id !== "atras")
        pagina = a.id;
    if (a.id === "adelante" && pagina < ultimo)
        pagina = parseInt(pagina) + 1;
    if (a.id === "atras" && pagina > 0)
        pagina = parseInt(pagina) - 1;
    cargarPacientes(pagina, buscar)
});
$("#tabIngresos .table-responsive").on("click", "#btnSeleccionar", function () {
    var cont = 0;
    var datos = [];
    $(this).parents("tr").find("td").each(function () {
        datos[cont] = $(this).html();
        cont++;
    });
    idHistoria = datos[1];
    $("#tabIngresos #txtCedula").val(datos[2]);
    $("#tabIngresos #txtPaciente").val(datos[3]);
    $('#tabIngresos #txtCedula').closest("div").removeClass("has-error");
    $("#tabIngresos #txtCedulahelp").remove();
    closeModal("myModal");
});

$('#tabIngresos #btnGuardar').click(function (event) {
    if (validarIngreso()) {
        $.post('sIngresosHospital', {
            idHistoria: idHistoria,
            fechaIngreso: $("#tabIngresos #dtpFechaIngreso").val(),
            idTipoIngreso: 2,
            idEspecialidadEgreso: $('#tabIngresos #cboEspecialidadEgreso').val(),
            fechaEgreso: $("#tabIngresos #dtpFechaEgreso").val(),
            horaIngreso: $("#tabIngresos #dtpHoraIngreso").val(),
            sos: 0,
            condicionEgreso: $("#tabIngresos #cboCondicionEgreso").val(),
            definitivoEgreso: $("#tabIngresos #txtDefinitivoEgreso").val(),
            secundarioEgreso: $("#tabIngresos #txtSecundarioEgreso").val(),
            secundarioEgreso2: $("#tabIngresos #txtSecundarioEgreso2").val(),
            causaExterna: $("#tabIngresos #txtCausaExterna").val(),
            codigoDiagnosticoDefinitivo: $("#tabIngresos #txtCodigoCie").val(),
            opcion: '2'
        }, function (data) {
            limpiar();
            var resultado = JSON && JSON.parse(data) || $.parseJSON(data);
            if (resultado.estado === true)
            {
                alertify.success("Datos Registrados correctamente");
            } else
                alertify.success("Problemas al intentar guardar\n" + resultado.excepcion);

        });
    }
});
