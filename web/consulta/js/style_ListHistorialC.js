pag_tbHC = ("#pag_tbHC");
cont_vacio = 0;
opts_pag_tbHC = {
    totalPages: 1,
    visiblePages: 5,
    initiateStartPageClick: false,
    first: "&larrb;",
    prev: "&laquo;",
    next: "&raquo;",
    last: "&rarrb;",
    onPageClick: function (event, page) {
        obtList(false, page);
    }
};
$("#tbHC").on("click", "button[name='btnHCaso']", function () {
    viewHistorialCaso($(this).attr("data-id"));
});
$("#tbHC").on("click", "button[name='btnNewConsulta']", function () {
    addHistorialCaso($(this).attr("data-id"));
});

$("#btn_paciente_reg").click(function () {
    $("#contenido").load("paciente/paciente.jsp", function () {
        setReturn(3);
    });
});

$("#tb_ViewHC").on('dbl-click-row.bs.table', function (e, row, $element) {
    viewConsulta(row.id);
});

$("#tb_ViewHC").bootstrapTable();
$("#tbHC").bootstrapTable(/*{
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
 }*/);

$("#cboH_Caso").selectpicker();

$.ajaxSetup({
    cache: false
});

$("#txt_filterHistorialC").keyup(function (e) {
    switch (e.keyCode) {
        case 8:
            if ($.isEmptyObject($(this).val()) && cont_vacio === 0) {
                cont_vacio = 1;
                obtList(true, 1);
            }
            break;
        case 13:
            cont_vacio = 0;
            obtList(true, 1);
            break;
        default :
            cont_vacio = 0;
            break;
    }



});

function obtList(bandera, pag) {
    cantList = $("#cboH_Caso").selectpicker("val");
    $.ajax({
        url: "sConsulta",
        data: {
            op: "list",
            idHc: $("#con_historiaPaciente").val(),
            filter: $("#txt_filterHistorialC").val(),
            top: cantList,
            pag: ((pag - 1) * cantList)
        },
        dataType: 'json',
        type: 'POST',
        async: false,
        success: function (data) {
            if (bandera) {
                $totalPages = data.total / cantList;
                $totalPages = Math.ceil($totalPages);
                $totalPages = ($totalPages === 0) ? 1 : ($totalPages);
                $(pag_tbHC).twbsPagination('destroy');
                $(pag_tbHC).twbsPagination($.extend({}, opts_pag_tbHC, {
                    totalPages: $totalPages
                }));
            }

            $("#tbHC").bootstrapTable("load", data.list);
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

function viewConsulta(id) {
    $('#viewHistorialCaso').modal('toggle');
    $('#viewHistorialCaso').on({
        'hidden.bs.modal': function () {
            $("#contenido").load("consulta/viewConsulta.jsp", function () {
                editConsulta(id);
            });
        }
    });
}

function addHistorialCaso(idCaso) {
    nomPaciente = $("#con_nombrePaciente").val();
    hc = $("#con_historiaPaciente").val();
    sexo = $("#con_sexoPaciente").val();
    load_newcaso(nomPaciente, hc, sexo, idCaso);
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
            load_newcaso(nomPaciente, hc, sexo, 0);
        } else {
            alertify.success("Paciente no seleccionado...!");
        }
    });

    function load_newcaso(nombre, hc, sexo, idCaso) {
        $("#contenido").load("consulta/newConsulta.jsp", function () {
            $("#PacienteId").val(nombre);
            $("#PacienteId").attr("data-hc", hc);
            $("#casoId").val(idCaso);
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
    $(pag_tbHC).twbsPagination(opts_pag_tbHC);
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
                $("#con_sexoPaciente").val(ob.paciente.sexo);
                obtList(true, 1);
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
            obtList(true, 1);
            $("#ListPaciente").modal("toggle");
        });
    });
}

function limpiarDivPaciente() {
    $("#con_historiaPaciente").val("");
    $("#con_cedulaPaciente").val("");
    $("#con_nombrePaciente").val("");
    $("#con_ciudadPaciente").val("");
    $("#txt_filterHistorialC").val("");
    $("#tbHC").bootstrapTable("removeAll");
    $(pag_tbHC).twbsPagination(opts_pag_tbHC);
}
