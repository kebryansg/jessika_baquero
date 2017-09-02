paginacion_Especialidad = ("#pag_tbEspecialidad");
function loadPaginacionEspecialidad(total) {
    $(paginacion_Especialidad + " li a").not("[aria-label]").closest("li").remove();
    li = '';
    for (var c = 0; c < total; c++) {
        li += ('<li ' + ((c === 0) ? 'class="active"' : '') + ' ><a href="#">' + (c + 1) + '</a></li>');
    }
    $(paginacion_Especialidad + " li").first().after(li);
}
$("#tbEspecialidad").bootstrapTable();
$("#tbEspecialidad").on("click", "button[name='editEspecialidad']", function () {
    clear_especialidad();
    tr = $(this).closest("tr");
    $("#modalEspecialidad .modal-title").html("Editar Especialidad");
    $("#recipient-name").val($(tr).find("td").eq(1).html());
    $("#modalEspecialidad").modal("toggle");
    $("#btnActualizar").data("id", $(tr).find("td").eq(0).html());
});
$("#tbEspecialidad").on("click", "button[name='deleteEspecialidad']", function () {
    tr = $(this).closest("tr");
    id = $(tr).find("td").eq(0).html();
    $.ajax({
        url: 'sEspecialidad',
        type: 'POST',
        data: {
            id: id,
            opcion: "delete"
        },
        success: function (data) {
            cargarEspecialidades(1, true);
            alertify.success("Especialidad Eliminada");
        }
    });
});


function cargarEspecialidades(pagina, bandera) {
    var totalRegistro = $("#cboMostrar_tbEspecialidad").val();
    $.ajax({
        type: 'POST',
        dataType: 'json',
        url: "sEspecialidad",
        data: {
            totalMostrar: totalRegistro,
            pagina: ((pagina - 1) * totalRegistro),
            opcion: '2',
            buscar: $("#txtBuscar").val()
        },
        success: function (data) {
            console.log(data);
            if (bandera)
                loadPaginacionEspecialidad(data.count);
            $("#tbEspecialidad").bootstrapTable("load", data.list);
        }
    });
}
cargarEspecialidades(1, true);
$(function () {
    cargarEspecialidades(1, true);

    $("#cboMostrar_tbEspecialidad").on("changed.bs.select", function () {
        cargarEspecialidades(1, true);
    });

    $("#contenido").on("click", paginacion_Especialidad + " li a[aria-label]", function (e) {
        li_old = $(paginacion_Especialidad + " li[class='active']");
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
            cargarEspecialidades($(li).find("a").html(), false);
        }
    });
    $("#contenido").on("click", paginacion_Especialidad + " li:not([class='active']) a:not([aria-label])", function (e) {
        li = $(this).closest("li");
        $(paginacion_Especialidad + " li[class='active']").toggleClass("active");
        $(li).toggleClass("active");
        cargarEspecialidades($(this).html(), false);
    });
});


function validaciones() {
    $(".help-block").remove();
    validarText($("#recipient-name"));
    return $(".help-block").length === 0;
}


$("#tbEspecialidad #txtBuscar").keyup(function (event) {
    if ($("#txtBuscar").val().length < 1)
        buscar = 0;
    else
        buscar = 1;
    pagina = 0;

    cargarEspecialidades(pagina, buscar);
});

$('#btnAgregar').click(function (event) {
    clear_especialidad();
    $("#modalEspecialidad").modal('toggle');
});



function clear_especialidad() {
    $("#modalEspecialidad .help-block").remove();
    $("#recipient-name").closest(".form-group").removeClass("has-error");
    $("#recipient-name").val("");
    $("#btnActualizar").data("id", 0);
}


$('#btnActualizar').click(function (event) {
    if (validaciones()) {
        descripcionEspecialidadVar = $('#recipient-name').val();
        idEspecialidadVar = $(this).data("id");
        $.ajax({
            url: 'sEspecialidad',
            type: 'POST',
            dataType: 'json',
            data: {
                descripcion: descripcionEspecialidadVar,
                id: idEspecialidadVar,
                opcion: "save"
            },
            success: function (data) {
                if (data.status) {
                    if (idEspecialidadVar === 0) {
                        alertify.success("Especialidad Agregada");
                    } else {
                        alertify.success("Especialidad Modificada");
                    }
                    cargarEspecialidades(1, true);
                    $("#modalEspecialidad").modal('toggle');
                } else {
                    alertify.success("Problemas");
                }

            }
        });
    }
});