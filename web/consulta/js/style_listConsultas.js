paginacion_Consultas = ("#pag_tbConsultas");
/*function loadPaginacionConsultas(total) {
    $(paginacion_Consultas + " li a").not("[aria-label]").closest("li").remove();
    li = '';
    total = (total > 10) ? 20 : total;
    for (var c = 0; c < total; c++) {
        li += ('<li ' + ((c === 0) ? 'class="active"' : '') + ' ><a href="#">' + (c + 1) + '</a></li>');
    }
    $(paginacion_Consultas + " li").first().after(li);
}*/
defaultOpts = {
    totalPages: 1,
    visiblePages: 4,
    first: "|<",
    prev: "<<",
    next: ">>",
    last: ">|"
};
$(paginacion_Consultas).twbsPagination(defaultOpts);

var optionDate = {
    //format: "yyyy-mm",
    language: 'es',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    //startView: "year",
    pickerPosition: "bottom-left",
    //minView: 3,
    forceParse: 0
            //initialDate: new Date()
};
$(function () {

    $("#tbConsultas").bootstrapTable();
    $(".selectpicker").selectpicker('refresh');


    modalListPaciente();
    $('#ListPaciente').on('shown.bs.modal', function () {
        $("#ListPaciente table").bootstrapTable('resetView');
    });

    $(".fecha").datetimepicker($.extend(optionDate, {
        format: "yyyy-mm-dd",
        minView: 2,
        startView: 2
    }));

    $('.fecha').datetimepicker('setEndDate', new Date());

    $(".mes").datetimepicker($.extend(optionDate, {
        format: "yyyy-mm",
        minView: 3,
        startView: "year"
    }));
    $(".año").datetimepicker($.extend(optionDate, {
        format: "yyyy",
        minView: 4,
        startView: "decade"
    }));
    $('.form_date').datetimepicker('update', new Date());


    $(".hidden-event").hide();

    $("#run_consulta").on("click", function (e) {
        $("#tbConsultas").bootstrapTable("removeAll");
        adminGet(true, 1);
    });

    cbo_loadTipoConsulta();
    cbo_loadEspecialidad();

    $("#opListConsultas").on("changed.bs.select", function () {
        opTiempo = $(this).val();
        $(".hidden-event").hide();
        limpiarListConsultas();
        switch (opTiempo) {
            case "1":
                $("#fechas").show();
                break;
            case "2":
                $("#con_Mes").closest(".form-group").show();
                break;
            case "3":
                $("#meses").show();
                break;
            case "4":
                $("#con_Año").closest(".form-group").show();
                break;
        }
    });



    /*$("#contenido").on("click", paginacion_Consultas + " li a[aria-label]", function (e) {
        e.preventDefault();
        li_old = $(paginacion_Consultas + " li[class='active']");
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
            adminGet(false, $(li).find("a").html());
        }
    });
    $("#contenido").on("click", paginacion_Consultas + " li:not([class='active']) a:not([aria-label])", function (e) {
        e.preventDefault();
        li = $(this).closest("li");
        $(paginacion_Consultas + " li[class='active']").toggleClass("active");
        $(li).toggleClass("active");
        adminGet(false, $(this).html());
    });*/

    $("#cantListConsultas").on("changed.bs.select", function () {
        adminGet(true, 1);
    });

    $("#pac_Delete").on("click", function (e) {
        limpiarInfoPaciente();
    });

});

function limpiarListConsultas() {
    $(paginacion_Consultas).twbsPagination(defaultOpts);
    $("#tbConsultas").bootstrapTable("removeAll");
    $('.form_date').datetimepicker('update', new Date());
}

function limpiarInfoPaciente() {
    $("#pac_HC").val("");
    $("#pac_Nombres").val("");
    $("#tbConsultas").bootstrapTable("removeAll");
    $(paginacion_Consultas).twbsPagination(defaultOpts);
}

function adminGet(bandera, pag) {
    tiempo = $("#opListConsultas").val();
    switch (tiempo) {
        case "0":
            getConsultas_Pac(bandera, pag);
            break;
        case "1":
        case "3":
            //Validando entre fechas y entre mes
            //Asignando valores dependiendo el case
            fechaI = (tiempo === "1") ? $("#con_FechaI").val() : $("#con_MesI").val();
            fechaF = (tiempo === "1") ? $("#con_FechaF").val() : $("#con_MesF").val();
            getConsultas_Fechas(bandera, pag, fechaI, fechaF, tiempo);
            break;
        case "2":
        case "4":
            // Mes - Año
            getConsultasTiempo(bandera, pag, (tiempo === "2") ? $("#con_Mes").val() : $("#con_Año").val(), tiempo);
            break;
    }
}

function getConsultas_Pac(bandera, pag) {
    idHC = $("#pac_HC").val() !== "" ? $("#pac_HC").val() : 0;
    idTipoConsulta = $("#cboTipoConsulta").val();
    cantList = $("#cantListConsultas").val();
    idsEspecialidad = ($("#cboEspecialidad").val() !== null) ? $("#cboEspecialidad").val().join() : "0";
    $.ajax({
        url: "sConsulta",
        type: 'POST',
        dataType: 'json',
        async: false,
        data: {
            op: 'adminConsultas',
            idTipoConsulta: idTipoConsulta,
            idsEspecialidad: idsEspecialidad,
            opTiempo: 0,
            idHC: idHC,
            filter: "",
            pag: ((pag - 1) * cantList),
            top: cantList
        },
        success: function (data) {
            if (bandera)
                loadPaginacionConsultas(data.count);
            $("#tbConsultas").bootstrapTable("load", data.list);
        }
    });
}

function loadPaginacionConsultas(total) {
    //alert(total);
    currentPage = $(paginacion_Consultas).twbsPagination('getCurrentPage');
    $(paginacion_Consultas).twbsPagination('destroy');
    $(paginacion_Consultas).twbsPagination($.extend({}, defaultOpts, {
        startPage: currentPage,
        totalPages: total,
        onPageClick: function (event, page) {
            adminGet(false, page);
        }
    }));
}

function getConsultasTiempo(bandera, pag, fecha, opTiempo, filter) {
    idHC = $("#pac_HC").val() !== "" ? $("#pac_HC").val() : 0;
    idTipoConsulta = $("#cboTipoConsulta").val();
    cantList = $("#cantListConsultas").val();
    idsEspecialidad = ($("#cboEspecialidad").val() !== null) ? $("#cboEspecialidad").val().join() : "0";

    $.ajax({
        url: "sConsulta",
        type: 'POST',
        dataType: 'json',
        async: false,
        data: {
            op: 'adminConsultas',
            fecha: fecha,
            idTipoConsulta: idTipoConsulta,
            idsEspecialidad: idsEspecialidad,
            opTiempo: opTiempo,
            idHC: idHC,
            filter: "",
            pag: ((pag - 1) * cantList),
            top: cantList
        },
        success: function (data) {
            if (bandera)
                loadPaginacionConsultas(data.count);
            $("#tbConsultas").bootstrapTable("load", data.list);
        }
    });
}

function getConsultas_Fechas(bandera, pag, fechaI, fechaF, opTiempo, filter) {
    idHC = $("#pac_HC").val() !== "" ? $("#pac_HC").val() : 0;
    idTipoConsulta = $("#cboTipoConsulta").val();
    cantList = $("#cantListConsultas").val();
    idsEspecialidad = ($("#cboEspecialidad").val() !== null) ? $("#cboEspecialidad").val().join() : "0";
    $.ajax({
        url: "sConsulta",
        type: 'POST',
        dataType: 'json',
        async: false,
        data: {
            op: 'adminConsultas',
            idTipoConsulta: idTipoConsulta,
            idsEspecialidad: idsEspecialidad,
            fechaI: fechaI,
            fechaF: fechaF,
            opTiempo: opTiempo,
            idHC: idHC,
            filter: "",
            pag: ((pag - 1) * cantList),
            top: cantList
        },
        success: function (data) {
            if (bandera)
                loadPaginacionConsultas(data.count);

            $("#tbConsultas").bootstrapTable("load", data.list);
        }
    });
}

function modalListPaciente() {
    $("#ListPaciente .modal-body").load("paciente/listPacientes.jsp", function () {
        $("#tablPaciente").bootstrapTable('hideColumn', 'accion');
        $("#tablPaciente").bootstrapTable('showColumn', 'seleccionar');

        $("#tablPaciente").on('dbl-click-row.bs.table', function (e, row, $element) {
            $("#pac_HC").val(row.hc);
            //$("#con_cedulaPaciente").val(row.cedula);
            $("#pac_Nombres").val(row.nombres);
            //$("#con_sexoPaciente").val(row.sexo);
            $("#ListPaciente").modal("toggle");
        });


    });
}

function cbo_loadTipoConsulta() {
    $.ajax({
        url: 'sTipoConsulta',
        data: {
            op: 'list'
        },
        type: 'POST',
        success: function (data) {
            $("#cboTipoConsulta").html('<option value="0">Seleccione</option>' + data);
            $("#cboTipoConsulta").selectpicker("refresh");
        }
    });
}

function cbo_loadEspecialidad() {
    $.ajax({
        url: 'sEspecialidad',
        data: {
            opcion: 'list'
        },
        type: 'POST',
        success: function (data) {
            $("#cboEspecialidad").html(data);
            $("#cboEspecialidad").selectpicker("refresh");
        }
    });
}