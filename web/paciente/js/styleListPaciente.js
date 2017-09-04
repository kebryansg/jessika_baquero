var $table = $('#table');
cont_vacio = 0;
$('#cboFilter').on('changed.bs.select', function (e) {
    newValue = $(this).val();
    $("#txt_filterPaciente").val("");
    $("#txt_filterPaciente").removeAttr("maxlength");
    $("#txt_filterPaciente").removeClass("solo-numero");
    switch (newValue) {
        case "2":
            $("#txt_filterPaciente").addClass("solo-numero");
            $("#txt_filterPaciente").attr("maxlength", 10);
            break;
        case "3":
            $("#txt_filterPaciente").addClass("solo-numero");
            break;
    }
    loadList(true, 1);

});
$("#tablPaciente").bootstrapTable({
    contextMenu: '#tablPaciente-context-menu',
    beforeContextMenuRow: function (e) {
        return $("#tablPaciente").closest(".modal").length === 0;
    },
    onContextMenuItem: function (row, $el) {
        switch ($el.data("item")) {
            case "edit":
                editPaciente(row.id);
                break;
            case "delete":
                deletePaciente(row.id);
                break;
            case "new_consulta":
                load_newcaso(row.nombres, row.hc, row.sexo,0);
                break;
        }
    }
});
function load_newcaso(nombre, hc, sexo,idCaso) {
    $("#contenido").load("consulta/newConsulta.jsp", function () {
        $("#PacienteId").val(nombre);
        $("#casoId").val(idCaso);
        $("#PacienteId").attr("data-hc", hc);
        if (sexo === "1") {
            $("#div_femenino").hide();
            $(".sFemenino").closest("li").attr("class", "disabled");
        } else {
            $(".sMasculino").closest("li").attr("class", "disabled");
        }
    });
}

defaultOpts = {
    totalPages: 1,
    visiblePages: 5,
    initiateStartPageClick: false,
    first: "&larrb;",
    prev: "&laquo;",
    next: "&raquo;",
    last: "&rarrb;",
    onPageClick: function (event, page) {
        loadList(false, page);
    }
};


function loadList(bandera, pag) {
    ojb_return = null;
    txt_filter = $("#txt_filterPaciente").val();
    op_filter = $("#cboFilter").selectpicker("val");
    cantList = $("#cantList").val();
    $.ajax({
        url: 'sPaciente',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: {
            filter: txt_filter,
            top: cantList,
            pag: ((pag - 1) * cantList),
            op_filter: op_filter,
            op: 'list_filter'
        },
        success: function (obj) {
            $totalPages = obj.count / cantList;
            $totalPages = Math.ceil($totalPages);
            $totalPages = ($totalPages === 0) ? 1 : ($totalPages);
            if (bandera) {
                modif_Paginacion($totalPages);
            }
            $("#tablPaciente").bootstrapTable('load', obj.list);
            $('#tablPaciente').bootstrapTable('resetView');
        }
    });
}
function modif_Paginacion(total) {
    $('#pagination-demo').twbsPagination('destroy');
    console.log(defaultOpts);
    $('#pagination-demo').twbsPagination($.extend({}, defaultOpts, {
        totalPages: total
    }));
}

function editPaciente(idPaciente) {
    $("#contenido").load("paciente/paciente.jsp", function () {
        edit(idPaciente);
    });
}

function deletePaciente(idPaciente) {
    deletPaciente(idPaciente);
    loadList(true, 1);
}

$(function () {

    $("#cantList").selectpicker();

    $('#pagination-demo').twbsPagination(defaultOpts);
    loadList(true, 1);
    $("#tablPaciente").bootstrapTable('hideColumn', 'sexo');
    $("#tablPaciente").bootstrapTable('hideColumn', 'id');

    $("#contenido").on("change", "#cantList", function () {
        loadList(true, 1);
    });

    $("#contenido").on("keyup", "#txt_filterPaciente", function (e) {
        switch (e.keyCode) {
            case 8:
                if ($.isEmptyObject($(this).val()) && cont_vacio === 0) {
                    cont_vacio = 1;
                    loadList(true, 1);
                }
                break;
            case 13:
                cont_vacio = 0;
                loadList(true, 1);
                break;
            default :
                cont_vacio = 0;
                break;
        }
    });
});

