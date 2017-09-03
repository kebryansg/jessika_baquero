function validarPaciente(id) {
    $("#tabPacientes div").removeClass("has-error");
    $("#tabPacientes div").removeClass("error_input");
    $("#tabPacientes span").remove(".help-block");
    $.each($("#tabPacientes input[validate='text']"), function (index, value) {
        if (!validarText(value)) {
            $(value).blur(function () {
                validarText(value);
            });
        }
    });
    $.each($("#tabPacientes select[validate='select']"), function (index, value) {
        if (!validarSelect(value)) {
            $(value).on("change", function () {
                validarSelect(value);
            });
        }
    });
    $.each($("#tabPacientes input[validate='fecha_mask']"), function (index, value) {
        if (!$(value).inputmask("isComplete")) {
            $(value).closest("div").addClass("has-error");
            $(value).after('<span id="' + $(value).attr("id") + 'help" class="help-block">Campo Vacio</span');
        }
    });
    var email = $("#tabPacientes input[validate='email']");
    if (!validarEmail(email)) {
        $(email).blur(function () {
            validarEmail(email);
        });
    }
    var cedula = $("#tabPacientes input[validate='cedula']");
    if (!validarCedula(cedula)) {
        $(cedula).blur(function () {
            validarCedula(cedula);
        });
    }

    if ($("#pac_Genero").val() === "2") {
        var date = $("#pac_FPP");
        if (!validarDate(date)) {
            $(date).change(function () {
                validarDate(date);
            });
        }
    }
    return $(".help-block").length === 0;
}

function deletPaciente(id) {
    $.ajax({
        url: 'sPaciente',
        data: {
            op: 'delete',
            id: id
        },
        async: false,
        type: 'POST',
        success: function (data) {
            alertify.success("Registros Eliminado");
        }
    });
}

function list() {
    $.ajax({
        url: 'sPaciente',
        type: 'POST',
        async: false,
        data: {
            op: 'list'
        },
        success: function (response) {
            $("#tablePaciente").html(response);
        }
    });
}

function td_tr_seleccionar(tbody) {
    $.each($(tbody).find("tr"), function (index, tr) {
        var id = $(tr).attr("data-id");
        $(tr).find("td:last").html("");
        $(tr).find("td:last").html('<button name="SeleccionarPaciente" data-dismiss="modal" class="btn btn-info">Seleccionar</button>');
    });
}

function indexPag(pag, totalList, txt_filter) {
    var cantList = totalList;
    $.ajax({
        url: 'sPaciente',
        type: 'POST',
        //async: false,
        data: {
            filter: txt_filter,
            top: cantList,
            pag: ((pag - 1) * cantList),
            op: 'list_filter'
        },
        success: function (response) {
            var obj = $.parseJSON(response);
            var tablePaciente = $("#tablePaciente");
            $(tablePaciente).html(obj.list);
            if ($(tablePaciente).attr("modal") === "1") {

                td_tr_seleccionar(tablePaciente);
            }
            $('#tablPaciente').bootstrapTable('resetView');
        }
    });
}

var defaultOpts = {
//totalPages: 2,
    visiblePages: 10,
    first: "Primero",
    next: "Siguiente",
    last: "Ultimo",
    prev: "Anterior"
};

function list_filter() {
    var tablePaciente = $("#tablePaciente");
    $(tablePaciente).html("");
    var $pagination = $('#pagPacientes');
    var txt_filter = $("#txt_filterPaciente").val();
    var cantList = $("#cantList").val();
    var $totalPages = 2;
    if (txt_filter !== "") {

        $.ajax({
            url: 'sPaciente',
            type: 'POST',
            async: false,
            data: {
                op: 'list',
                filter: txt_filter
            },
            success: function (response) {
                $totalPages = response / cantList;
                $totalPages = Math.ceil($totalPages);
                //Paginacion

            }
        });
    }
    $pagination.twbsPagination('destroy');
    $pagination.twbsPagination($.extend({}, defaultOpts, {
        onPageClick: function (event, page) {
            indexPag(page, cantList, txt_filter);
        },
        totalPages: $totalPages
    }));
}

function edit(id) {
    $.ajax({
        url: 'sPaciente',
        type: 'POST',
        async: false,
        cache: false,
        data: {
            id: id,
            op: 'edit'
        },
        success: function (response) {
            var ob = $.parseJSON(response);
            asignarPaciente(ob.paciente);
            if (!ob.paciente.sexo) {
                asignarObstetrico(ob.obs, ob.paciente.id);
            }
        }
    });
}

function editSave(id) {
    if (validarPaciente(id)) {
        var paciente = obtenerDatos(id);
        $.ajax({
            url: 'sPaciente',
            type: 'POST',
            async: false,
            data: {
                id: id,
                paciente: paciente,
                op: 'save'
            },
            success: function (response) {
                alertify.success("Paciente Modificado");
                limpiarPaciente();
            }
        });
    } else {
        alertify.success("Inconvenientes..!");
    }
}

function save() {
    if (validarPaciente(0)) {
        $.ajax({
            url: 'sPaciente',
            type: 'POST',
            async: false,
            cache: false,
            dataType: 'json',
            data: {
                id: 0,
                paciente: obtenerDatos(),
                op: 'save'
            },
            success: function (response) {
                switch (response.status) {
                    case "ok":
                        alertify.success("Paciente Registrado");
                        limpiarPaciente();
                        $("#savePaciente").data("id", response.id);
                        break;
                    case "cedula":
                        alertify.success("Cedula repetida...!");
                        break;
                }
            }
        });
    } else {
        alertify.success("Inconvenientes..!");
    }
}

function obtenerDatos() {
    var paciente = {
        cedula: $("#pac_Cedula").val(),
        primerNombre: $("#pac_primerNombre").val(),
        segundoNombre: $("#pac_segundoNombre").val(),
        primerApellido: $("#pac_primerApellido").val(),
        segundoApellido: $("#pac_segundoApellido").val(),
        fechaNac: $("#pac_FechaNac").val(),
        nacionalidad: $("#pac_nacionalidad").val(),
        telCasa: $("#pac_TelCasa").val(),
        email: $("#pac_Email").val(),
        etnia: $("#pac_Etnia").val(),
        domicilio: $("#pac_Domicilio").val(),
        discapacidad: $("#pac_Discapacidad").val(),
        //ciudad: $("#pac_Ciudad").val(),
        estadoCivil: $("#pac_EstadoCivil").val(),
        telOficina: $("#pac_TelOficina").val(),
        genero: $("#pac_Genero").val(),
        paisNac: $("#pac_PaisNac").val(),
        //lugarNac: $("#pac_LugarNac").val(),
        observacion: $("#pac_Observacion").val(),
        provincia: $("#cboProvincia").val(),
        parroquia: $("#cboParroquia").val(),
        canton: $("#cboCanton").val(),
        nombreContacto: $("#pac_nombreContacto").val(),
        movilContacto: $("#pac_movilContacto").val(),
        parentezco: $("#cboParentezco").val(),
        app: $("#pac_APP").val(),
        apf: $("#pac_APF").val(),
        //Obstetricia
        idObs: $("#tabObstetricia").data("id"),
        fpp: $("#pac_FPP").val(),
        gestacion: $("#pac_Gestacion").val(),
        abortos: $("#pac_Abortos").val(),
        partos: $("#pac_Partos").val(),
        cesareas: $("#pac_Cesareas").val(),
        nacidoVivo: $("#pac_NacidoVivo").val(),
        nacidoMuerto: $("#pac_NacidoMuerto").val(),
        hijosVivos: $("#pac_HijosVivos").val(),
        hijosMuertos: $("#pac_HijosMuertos").val()

    };
    return paciente;
}

function asignarObstetrico(obs, id) {
    $("#tabObstetricia").attr("data-id", obs.id);
    $("#pac_FPP").val(obs.fpp);
    $("#pac_Gestacion").val(obs.gestas);
    $("#pac_Abortos").val(obs.abortos);
    $("#pac_Partos").val(obs.partos);
    $("#pac_Cesareas").val(obs.cesareas);
    $("#pac_NacidoVivo").val(obs.nacidosVivos);
    $("#pac_NacidoMuerto").val(obs.nacidosMuertos);
    $("#pac_HijosVivos").val(obs.hijosVivos);
    $("#pac_HijosMuertos").val(obs.muertos);
}

function asignarPaciente(paciente) {
    $("#savePaciente").data("id", paciente.id);
    $("#cancelPaciente").data("id", paciente.id);
    $("#pac_Cedula").val(paciente.cedula);
    $("#pac_primerNombre").val(paciente.nombre1);
    $("#pac_segundoNombre").val(paciente.nombre2);
    $("#pac_primerApellido").val(paciente.apellido1);
    $("#pac_segundoApellido").val(paciente.apellido2);
    $("#pac_FechaNac").val(paciente.fechaNacimiento);
    $("#pac_imagen").attr("src", paciente.imagen);
    $("#pac_imagen").attr("edit", paciente.imagen);
    $("#pac_TelCasa").val(paciente.telefonoDomicilio);
    $("#pac_Email").val(paciente.email);
    $("#pac_TelOficina").val(paciente.telefonoOficina);
    $("#pac_PaisNac").val(paciente.paisNacimiento);
    //$("#pac_LugarNac").val(paciente.lugarNacimiento);
    $("#pac_Observacion").val(paciente.observacion);
    $("#pac_Domicilio").val(paciente.domicilio);
    //$("#pac_Ciudad").val(paciente.ciudad);
    $("#pac_nombreContacto").val(paciente.nombreContacto);
    $("#pac_movilContacto").val(paciente.movilContacto);
    $("#pac_APP").val(paciente.app);
    $("#pac_APF").val(paciente.apf);



    $("#cboParentezco").selectpicker("val", paciente.parentezco);
    $('#pac_Discapacidad').selectpicker("val", paciente.discapacidad);
    //Select
    $('#pac_nacionalidad').selectpicker("val", paciente.nacionalidad);
    $('#pac_Etnia').selectpicker("val", paciente.etnia);
    $('#pac_EstadoCivil').selectpicker("val", paciente.estadoCivil);
    $('#pac_Genero').selectpicker("val", paciente.sexo);
    change_Genero($('#pac_Genero'));
    /* Carga y asigna provincia,canton y parroquia */
    $.ajax({
        type: 'POST',
        url: 'sPaciente',
        async: false,
        data: {
            idParroquia: paciente.idParroquia.id,
            op: "det"
        },
        success: function (response) {
            var det = $.parseJSON(response);
            $('#cboProvincia').selectpicker('val', det.provincia);
            //$('#cboProvincia > option[value="' + det.provincia + '"]').attr('selected', true);
            change_cboProvincia($('#cboProvincia'));
            $('#cboCanton').selectpicker('val', det.canton);
            //$('#cboCanton > option[value="' + det.canton + '"]').attr('selected', true);
            change_cboCanton($('#cboCanton'));
            $('#cboParroquia').selectpicker('val', paciente.idParroquia.id);
            //$('#cboParroquia > option[value="' + paciente.idParroquia.id + '"]').attr('selected', true);
        }
    });
}

function limpiarPaciente() {
    var id = $("#savePaciente").data("id");
    moment();
}

function moment() {
    // Informacion basica
    $("#tabPacientes input[validate='text'],input[validate='fecha_mask'],textarea[validate='text'],input[validate='cedula']").val("");

    $("#tabPacientes select[validate='select']").selectpicker("val", 0);
    $('#pac_nacionalidad').selectpicker("val", 1);
    $("#pac_PaisNac").val("Ecuador");

    $("#tabPacientes div").removeClass("has-error");
    $("#tabPacientes div").removeClass("error_input");
    $("#tabPacientes span").remove(".help-block");

    $('.remove-example').find('option').remove();
    $('.remove-example').selectpicker('refresh');

    $("#tabPacientes #pac_Discapacidad").selectpicker("val", 1);

    $("#pac_nombreContacto").val("");
    $("#cboParentezco").selectpicker("val", 0);
    $("#pac_movilContacto").val("");

    change_Genero($('#pac_Genero'));
    // Antecedentes
    // Obstetricia
    $("input[type='number']").val(0);
    $("#tabObstetricia").data("id", 0);
    var utc = new Date().toJSON().slice(0, 10);//.replace(/-/g,'/');
    $("#pac_FPP").val(utc);
}