$("#tb_ViewHC").bootstrapTable({
    contextMenu: '#tb_ViewHC-context-menu',
    onContextMenuItem: function (row, $el) {
        switch ($el.data("item")) {
            case "view":
                $('#viewHistorialCaso').modal('toggle');
                $('#viewHistorialCaso').on({
                    'hidden.bs.modal': function () {

                        $("#contenido").load("consulta/viewConsulta.jsp", function () {
                            editConsulta(row.id);
                        });
                    }
                });
                break;
        }
    }
});
$("#tbHC").bootstrapTable({
    contextMenu: '#tbHc-context-menu',
    onContextMenuItem: function (row, $el) {
        switch ($el.data("item")) {
            case "new":
                addHistorialCaso(row.caso);
                break;
            case "view":
                viewHistorialCaso(row.caso);
                break;
        }
    }
});

$.ajaxSetup({
    cache: false
});
$("#txt_filterHistorialC").keyup(function (e) {
    tecla = e.keyCode;
    if (tecla === 13) {
        obtList();
    }
});


function obtList() {
    $.ajax({
        url: "sConsulta",
        data: {
            op: "list",
            idHc: $("#con_historiaPaciente").val(),
            filter: $("#txt_filterHistorialC").val()
        },
        dataType: 'json',
        type: 'POST',
        async: false,
        success: function (data) {
            $("#tbHC").bootstrapTable("load", data);
            $('#tbHC').bootstrapTable('resetView');
        }
    });
}
function viewHistorialCaso(idCaso) {
    $.ajax({
        url: "sConsulta",
        type: 'POST',
        dataType: 'json',
        async: false,
        data: {
            caso: idCaso,
            op: "detCaso"
        },
        success: function (data) {
            $("#viewHistorialCaso table").bootstrapTable('load', data);
        }
    });
    $('#viewHistorialCaso').modal("show");
}
function addHistorialCaso(idCaso) {
    nomPaciente = $("#con_nombrePaciente").val();
    hc = $("#con_historiaPaciente").val();
    sexo = $("#con_sexoPaciente").val();
    load_newcaso(nomPaciente,hc,sexo);

    /*$("#contenido").load("consulta/newConsulta.jsp", function () {
        $("#PacienteId").val(nomPaciente);
        $("#casoId").val(idCaso);
        $("#PacienteId").attr("data-hc", hc);
        if (sexo === "1") {
            $("#div_femenino").hide();
        }

    });*/
}

$(function () {


    $("#pac_Delete").on("click", function (e) {
        limpiarDivPaciente();

    });


    $("#btnNewConsulta").click(function () {
        nomPaciente = $("#con_nombrePaciente").val();
        hc = $("#con_historiaPaciente").val();
        sexo = $("#con_sexoPaciente").val();
        if (hc !== "") {
            load_newcaso(nomPaciente,hc,sexo);
            /*$("#contenido").load("consulta/newConsulta.jsp", function () {
                $("#PacienteId").val(nomPaciente);
                $("#PacienteId").attr("data-hc", hc);
                if (sexo === "1") {
                    $("#div_femenino").hide();
                    $(".sFemenino").closest("li").attr("class", "disabled");
                } else {
                    $(".sMasculino").closest("li").attr("class", "disabled");
                }
            });*/
        } else {
            alertify.success("Paciente no seleccionado...!");
        }
    });
    function load_newcaso(nombre, hc, sexo) {
        $("#contenido").load("consulta/newConsulta.jsp", function () {
            $("#PacienteId").val(nombre);
            $("#PacienteId").attr("data-hc", hc);
            if (sexo === "1") {
                $("#div_femenino").hide();
                $(".sFemenino").closest("li").attr("class", "disabled");
            } else {
                $(".sMasculino").closest("li").attr("class", "disabled");
            }
        });
    }

    modalListPaciente();
    $('#ListPaciente').on('shown.bs.modal', function () {
        $("#ListPaciente table").bootstrapTable('resetView');
    });

    $('#viewHistorialCaso').on('shown.bs.modal', function () {
        $("#viewHistorialCaso table").bootstrapTable('resetView');
    });

    $("#pac_Cargar").click(function () {
        limpiarDivPaciente();
        var cod = $("#txt_cargarPaciente").val();
        var bandera = validarText($("#txt_cargarPaciente"));
        if (bandera) {
            load_Paciente(cod);
        }
    });
});

function load_Paciente(cod) {
    $.ajax({
        url: 'sConsulta',
        type: 'POST',
        data: {
            op: 'paciente',
            cod: cod
        },
        success: function (response) {
            if (response !== "null") {
                var ob = $.parseJSON(response);
                $("#con_historiaPaciente").val(ob.hc_id);
                $("#con_cedulaPaciente").val(ob.paciente.cedula);
                $("#con_nombrePaciente").val((ob.paciente.apellido1 + " " + ob.paciente.apellido2 + " " + ob.paciente.nombre1 + " " + ob.paciente.nombre2).toUpperCase());
                $("#con_sexoPaciente").val((ob.paciente.sexo) ? "1" : "0");
                obtList();
            } else {
                alertify.success("Paciente no encontrado");
            }
        }
    });
}

function modalListPaciente() {
    $("#ListPaciente .modal-body").load("paciente/listPacientes.jsp", function () {
        $("#tablPaciente").bootstrapTable('hideColumn', 'accion');
        $("#tablPaciente").bootstrapTable('showColumn', 'seleccionar');

        $("#tablPaciente").on('dbl-click-row.bs.table', function (e, row, $element) {
            $("#con_historiaPaciente").val(row.hc);
            $("#con_cedulaPaciente").val(row.cedula);
            $("#con_nombrePaciente").val(row.nombres);
            $("#con_sexoPaciente").val(row.sexo);
            //alert(row.sexo);
            obtList();
            $("#ListPaciente").modal("toggle");
        });


    });
}

function limpiarDivPaciente() {
    $("#con_historiaPaciente").val("");
    $("#con_cedulaPaciente").val("");
    $("#con_nombrePaciente").val("");
    $("#con_ciudadPaciente").val("");
    $("#tbHC").bootstrapTable("removeAll");
}
