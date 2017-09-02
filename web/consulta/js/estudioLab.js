table = $("#tableEstudiosLab");
paginacion = ("#pagEstudiosLab");
var idLabs = [];


$("#tableEstudiosLabSelec").bootstrapTable();
$(table).bootstrapTable();

$(table).bootstrapTable("hideColumn","ID");
$("#tableEstudiosLabSelec").bootstrapTable("hideColumn","id");

$("#cantListEstudiosLab").selectpicker('refresh');

cboCategoria_load($("#cboCategoria"));
loadListEstudiosLab(true, 1);

function cboCategoria_load(cbo) {
    $.ajax({
        url: 'sEstudioLab',
        type: 'POST',
        async: false,
        data: {
            op: 'list_cbo'
        },
        success: function (response) {
            $(cbo).html(response);
            $(cbo).selectpicker('refresh');
        }
    });
}

function loadPaginacionEstudiosLab(total) {
    $(paginacion +" li a").not("[aria-label]").closest("li").remove();
    li = '';
    for (var c = 0; c < total; c++) {
        li += ('<li ' + ((c === 0) ? 'class="active"' : '') + ' ><a href="#">' + (c + 1) + '</a></li>');
    }
    $(paginacion + " li").first().after(li);
}

function loadListEstudiosLab(bandera, pag) {
    var categoria = $('#cboCategoria').val();
    var txt_filter = $("#txt_filterEstudiosLab").val();
    var cantList = $("#cantListEstudiosLab").val();
    $.ajax({
        url: 'sEstudioLab',
        type: 'POST',
        data: {
            op: 'detalle',
            filter: (txt_filter === null ? "" : txt_filter),
            pag: ((pag - 1) * cantList),
            top: cantList,
            categoria: categoria
        },
        success: function (response) {
            var obj = $.parseJSON(response);
            $totalPages = obj.count / cantList;
            $totalPages = Math.ceil($totalPages);
            if (bandera) {
                loadPaginacionEstudiosLab($totalPages);
            }
            $(table).bootstrapTable('load', obj.list);
            $(table).bootstrapTable('resetView');
        }
    });
}

$(function () {
    $("#contenido").on("click", paginacion + " li a[aria-label]", function (e) {
        e.preventDefault();
        li_old = $(paginacion + " li[class='active']");
        li = undefined;
        switch ($(this).attr("aria-label")) {
            case "Anterior":
                li = $(li_old).prev();
                break;
            case "Siguiente":
                li = $(li_old).next();
                break;
        }
        if ($(li).find("a[aria-label]").length === 0) {
            $(li).toggleClass("active");
            $(li_old).toggleClass("active");
            loadListEstudiosLab(false, $(li).find("a").html());
        }
    });
    $("#contenido").on("click", paginacion + " li:not([class='active']) a:not([aria-label])", function (e) {
        e.preventDefault();
        li = $(this).closest("li");
        $(paginacion + " li[class='active']").toggleClass("active");
        $(li).toggleClass("active");
        loadListEstudiosLab(false, $(this).html());
    });
});

$('#btnRemover').click(function () {
    var ids = $.map($("#tableEstudiosLabSelec").bootstrapTable('getSelections'), function (row) {
        idLabs.splice($.inArray(row.id, idLabs), 1);
        return row.id;
    });
    $("#tableEstudiosLabSelec").bootstrapTable('remove', {
        field: 'id',
        values: ids
    });

});


$("#cboCategoria").on("changed.bs.select", function () {
    loadListEstudiosLab(true, 1);
});

$("#cantListEstudiosLab").on("changed.bs.select", function () {
    loadListEstudiosLab(true, 1);
});
$("#txt_filterEstudiosLab").keyup(function (e) {
    if (e.keyCode === 8 && $(this).val() === "") {
        loadListEstudiosLab(true, 1);
    } else if (e.keyCode === 13) {
        loadListEstudiosLab(true, 1);
    }
});
$("#btnSelecc").click(function () {
    $.each($("#tableEstudiosLab").bootstrapTable('getSelections'), function (i, item) {
        if ($.inArray(item.ID, idLabs) === -1) {
            $("#tableEstudiosLabSelec").bootstrapTable("append", {
                id: item.ID,
                estudio: item.estudio
            });
            idLabs.push(item.ID);
        }
    });
    $("#tableEstudiosLab").bootstrapTable('uncheckAll');
});

