var $table = $('#table');
start_page = true;
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
                load_newcaso(row.nombres, row.hc, row.sexo);
                break;
        }
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

function loadPaginacion(total) {
    $("#pagPacientes li a").not("[aria-label]").closest("li").remove();
    li = '';
    total = (total <= 10) ? total : 10;
    for (var c = 0; c < total; c++) {
        li += ('<li ' + ((c === 0) ? 'class="active"' : '') + ' ><a href="#">' + (c + 1) + '</a></li>');
    }
    $("#pagPacientes li").first().after(li);
}
defaultOpts = {
    totalPages: 1,
    visiblePages: 4,
    first: "|<",
    prev: "<<",
    next: ">>",
    last: ">|",
    onPageClick: function (event, page) {
        loadList(false, page);
    }
};
$('#pagination-demo').twbsPagination(defaultOpts);

function loadList(bandera, pag) {
    console.log("entro");
    txt_filter = $("#txt_filterPaciente").val();
    op_filter = $("#cboFilter").selectpicker("val");
    cantList = $("#cantList").val();
    $.ajax({
        url: 'sPaciente',
        type: 'POST',
        async: false,
        data: {
            filter: txt_filter,
            top: cantList,
            pag: ((pag - 1) * cantList),
            op_filter: op_filter,
            op: 'list_filter'
        },
        success: function (response) {
            var obj = $.parseJSON(response);
            var tablePaciente = $("#tablePaciente tbody");
            $totalPages = obj.count / cantList;
            $totalPages = Math.ceil($totalPages);
            if (bandera) {
                $totalPages = ($totalPages === 0) ? 1 : ($totalPages);
                //currentPage = (bandera) ? 1 : $('#pagination-demo').twbsPagination('getCurrentPage');
                $('#pagination-demo').twbsPagination('destroy');
                $('#pagination-demo').twbsPagination($.extend({}, defaultOpts, {
                    totalPages: $totalPages
                            /*, onPageClick: function (event, page) {
                             loadList(false, page);
                             }*/
                }));
            }
            $("#tablPaciente").bootstrapTable('load', obj.list);
            $('#tablPaciente').bootstrapTable('resetView');
        }
    });
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

    //loadList(true, 1);
    $("#tablPaciente").bootstrapTable('hideColumn', 'sexo');
    $("#tablPaciente").bootstrapTable('hideColumn', 'id');
    
    $("#contenido").on("change", "#cantList", function () {
        loadList(true, 1);
    });
    $("#contenido").on("keyup", "#txt_filterPaciente", function (e) {
        if (e.keyCode === 8 && $(this).val() === "") {
            loadList(true, 1);
        } else if (e.keyCode === 13) {
            loadList(true, 1);
        }
    });
});

