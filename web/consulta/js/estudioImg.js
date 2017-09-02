tableEstudiosImg = $("#tableEstudiosImg");
paginacionEstudiosImg = ("#pagEstudiosImg");
var idImgs = [];

$(tableEstudiosImg).bootstrapTable();
$("#tableEstudiosImgSelec").bootstrapTable();
$("#cantListEstudiosImg").selectpicker('refresh');

$(tableEstudiosImg).bootstrapTable("hideColumn", "extremidad");
//
$(tableEstudiosImg).bootstrapTable("hideColumn", "ID");
$("#tableEstudiosImgSelec").bootstrapTable("hideColumn", "id");
$("#tableEstudiosImgSelec").bootstrapTable("hideColumn", "tipoEstudio");



//

TipoEstudiosImg_load($("#cboTipoEstudiosImg"));
EstudiosImg_load($("#cboEstudiosImg"));
loadListEstudiosImg(true, 1);

function loadPaginacionEstudiosImg(total) {
    /*$.each($(paginacionEstudiosImg + " li"), function (i, li) {
     if ($(li).find("a[aria-label]").length === 0) {
     $(li).remove();
     }
     });*/
    $(paginacionEstudiosImg + " li a").not("[aria-label]").closest("li").remove();
    li = '';
    for (var c = 0; c < total; c++) {
        li += ('<li ' + ((c === 0) ? 'class="active"' : '') + ' ><a href="#">' + (c + 1) + '</a></li>');
    }
    $(paginacionEstudiosImg + " li").first().after(li);
}

function TipoEstudiosImg_load(cbo) {
    $.ajax({
        url: 'sEstudioImg',
        type: 'POST',
        async: false,
        data: {
            op: 'list_tipoEstudioImg'
        },
        success: function (response) {
            $(cbo).html(response);
            $(cbo).selectpicker('refresh');
        }
    });
}

function EstudiosImg_load(cbo) {
    $.ajax({
        url: 'sEstudioImg',
        type: 'POST',
        async: false,
        data: {
            op: 'list_EstudioImg',
            idTipo: $("#cboTipoEstudiosImg").val()
        },
        success: function (response) {
            $(cbo).html(response);
            $(cbo).selectpicker('refresh');
        }
    });
}

function loadListEstudiosImg(bandera, pag) {
    var txt_filter = $("#txt_filterEstudiosImg").val();
    var cantList = $("#cantListEstudiosImg").val();
    idTipo = $("#cboTipoEstudiosImg").val();
    idEstudio = $("#cboEstudiosImg").val();
    $.ajax({
        url: 'sEstudioImg',
        type: 'POST',
        data: {
            op: 'list_DetEstudioImg',
            filter: (txt_filter === null ? "" : txt_filter),
            pag: ((pag - 1) * cantList),
            top: cantList,
            idTipo: idTipo,
            idEstudio: idEstudio
        },
        success: function (response) {
            var obj = $.parseJSON(response);
            $totalPages = obj.count / cantList;
            $totalPages = Math.ceil($totalPages);
            if (bandera) {
                loadPaginacionEstudiosImg($totalPages);
            }
            $(tableEstudiosImg).bootstrapTable('load', obj.list);
            $(tableEstudiosImg).bootstrapTable('resetView');
        }
    });
}

$("#btnpush").click(function () {
    imgArreglo = [];
    est = $("#tableEstudiosImgSelec").bootstrapTable('getData');
    $.each(est, function (i, item) {
        if (item.extremidad === "0") {
            delete item["der"];
            delete item["izq"];
        }
        der = izq = 0;
        if (item.extremidad === "1") {
            tr = $("#tableEstudiosImgSelec tbody tr").eq(i);
            der = $(tr).find("td").eq(2).find("input[type='checkbox']").is(':checked') ? 1 : 0;
            izq = $(tr).find("td").eq(3).find("input[type='checkbox']").is(':checked') ? 2 : 0;
        }
        imgArreglo.push({
            id: item.id,
            detExtre: (der + izq)
        });

    });
    console.log(imgArreglo);
});

$("#cboTipoEstudiosImg").on("changed.bs.select", function () {
    EstudiosImg_load($("#cboEstudiosImg"));
    loadListEstudiosImg(true, 1);
});

$("#cboEstudiosImg").on("changed.bs.select", function () {
    loadListEstudiosImg(true, 1);
});
$("#cantListEstudiosImg").on("changed.bs.select", function () {
    loadListEstudiosImg(true, 1);
});

$("#txt_filterEstudiosImg").keyup(function (e) {
    if (e.keyCode === 8 && $(this).val() === "") {
        loadListEstudiosImg(true, 1);
    } else if (e.keyCode === 13) {
        loadListEstudiosImg(true, 1);
    }
});
function check(value) {
    if (value === null) {
        checked = 'disabled';
    } else {
        checked = (value) ? 'checked' : '';
    }
    return '<input type="checkbox"  ' + checked + ' />';
}

$("#btnSeleccEstudiosImg").click(function () {
    $.each($(tableEstudiosImg).bootstrapTable('getSelections'), function (i, item) {
        if ($.inArray(item.ID, idImgs) === -1) {
            //defect_val = (item.extremidad === "1") ? false : null;
            tr_push = {
                id: item.ID,
                tipoEstudio: item.tipoEstudio,
                estudio: item.estudio,
                extremidad: item.extremidad
                        /*,der: defect_val,
                         izq: defect_val*/
            };
            if (item.extremidad !== "1") {
                $.extend(true, tr_push, {
                    der: null,
                    izq: null
                });
            }
            /*if (item.extremidad === "1") {
             $.extend(true, tr_push, {
             der: '<input type="checkbox" name="ext_estI" data-dir="der" >',
             izq: '<input type="checkbox" name="ext_estI" data-dir="izq">'
             });
             }*/
            $("#tableEstudiosImgSelec").bootstrapTable("append", tr_push);
            idImgs.push(item.ID);
        }
    });
    $(tableEstudiosImg).bootstrapTable('uncheckAll');
});

$('#btnRemoverEstImg').click(function () {
    var ids = $.map($("#tableEstudiosImgSelec").bootstrapTable('getSelections'), function (row) {
        idImgs.splice($.inArray(row.id, idImgs), 1);
        return row.id;
    });
    $("#tableEstudiosImgSelec").bootstrapTable('remove', {
        field: 'id',
        values: ids
    });
});

$(function () {
    $("#contenido").on("click", paginacionEstudiosImg + " li a[aria-label]", function (e) {
        e.preventDefault();
        li_old = $(paginacionEstudiosImg + " li[class='active']");
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
            loadListEstudiosImg(false, $(li).find("a").html());
        }
    });
    $("#contenido").on("click", paginacionEstudiosImg + " li:not([class='active']) a:not([aria-label])", function (e) {
        e.preventDefault();
        li = $(this).closest("li");
        $(paginacionEstudiosImg + " li[class='active']").toggleClass("active");
        $(li).toggleClass("active");
        loadListEstudiosImg(false, $(this).html());
    });
});
