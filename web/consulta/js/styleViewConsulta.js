$(function () {
    $("#btnCancelarViewConsulta").click(function () {
        hc = $("#PacienteId").attr("data-hc");
        $("#contenido").load("consulta/ListHistorialC.jsp", function () {
            load_Paciente(hc);
            obtList();
        });
    });

    $("#btnModificarConsulta").click(function () {
        if (saveConsulta_edit($(this).data("id"))) {
            hc = $("#PacienteId").attr("data-hc");
            $("#contenido").load("consulta/ListHistorialC.jsp", function () {
                load_Paciente(hc);
                obtList();
            });
        }
    });
});

function saveConsulta_edit(id) {
    bandera = false;
    $.ajax({
        type: 'POST',
        data: {
            op: 'save_edit',
            descripcion: get_descripcion(),
            sv: get_sv(),
            id: id
        },
        async: false,
        url: 'sConsulta',
        success: function (data) {
            bandera = true;
        }
    });
    return bandera;
}
function get_descripcion() {
    descripcion = {
        motivo: $("#con_Motivo").val(),
        sintomas: $("#con_Sintomas").val(),
        diagnostico: $("#con_Diagnostico").val(),
        prescipcion: $("#con_Prescripcion").val(),
        observacion: $("#con_Observacion").val()
    };
    return descripcion;
}
function get_sv() {
    sv = {
        id: $("#sv_id").data("id"),
        peso: $("#sv_Peso").val(),
        talla: $("#sv_Talla").val(),
        temperatura: $("#sv_Temperatura").val(),
        presion: $("#sv_Presion").val(),
        frecuenciaC: $("#sv_Frecuencia").val(),
        fum: $("#sv_FUM").val(),
        fuc: $("#sv_FUC").val(),
        periodo: $("#sv_Periodo").val()
    };
    return sv;
}

function editConsulta(id) {
    $.ajax({
        type: 'POST',
        data: {
            op: 'edit',
            id: id
        },
        async: false,
        url: 'sConsulta',
        dataType: 'json',
        success: function (data) {
            asignarConsuta(data.consulta);
            asginarSV(data.sv, data.sexoP);

            //listEstudiosLabs(data.estl);
            //listEstudiosImgs(data.esti);

            $("#PacienteId").val(data.paciente);
            $("#PacienteId").attr("data-hc", data.consulta.idCaso.idHistorialClinico.id);
            $("#con_tipoConsulta").val(data.tipoConsulta);
            $("#con_CausaMetodo").val(data.metodo_causa);
        }
    });
}
function listEstudiosLabs(estLab) {
    result = "";
    $.each(estLab, function (i, el) {
        result += "<li class='list-group-item'>" + el.idDetalleEstudiosLabs.idEstudiosLab.descripcion + " - <strong> " + el.idDetalleEstudiosLabs.descripcion + "</strong></li>";
    });
    if (result !== "") {
        $("#listEstudiosLab").append(result);
    } else {
        $("#listEstudiosLab").hide();
    }
}
function listEstudiosImgs(estImgs) {
    result = "";
    $.each(estImgs, function (i, ei) {
        result += "<li class='list-group-item'>" + ei.idDetalleEstudiosImagen.idEstudiosImg.idTipoEstudioImg.descripcion + " - " + ei.idDetalleEstudiosImagen.idEstudiosImg.descripcion + " - <strong>" + ei.idDetalleEstudiosImagen.descripcion + "</strong></li>";
    });
    if (result !== "") {
        $("#listEstudiosImg").append(result);
    } else {
        $("#listEstudiosImg").hide();
    }

}
function asignarConsuta(consulta) {
    $("#btnModificarConsulta").data("id", consulta.id);
    $("#con_Especialidad").val(consulta.idMedicoEspecialidad.idEspecialidad.descripcion);
    $("#con_Fecha").val(consulta.fecha);
    $("#con_Diagnostico").val(consulta.diagnostico);
    $("#con_Prescripcion").val(consulta.prescripcion);
    $("#con_Observacion").val(consulta.observacion);
}
function asginarSV(sv, sexo) {
    $("#sv_id").data("id", sv.id);
    $("#sv_Peso").val(sv.peso);
    $("#sv_Talla").val(sv.talla);
    $("#sv_Temperatura").val(sv.temperatura);
    $("#sv_Frecuencia").val(sv.frecuenciaC);
    $("#sv_Presion").val(sv.presion);
    if (sexo === "H") {
        $("#div_femenino").hide();
    } else {
        $("#sv_FUM").val(sv.fum);
        $("#sv_FUC").val(sv.fuc);
        $("#sv_Periodo").val(sv.periodo);
        $("#sv_Periodo").selectpicker("refresh");
    }
}

