return_dir = 1;
function setReturn(val) {
    return_dir = val;
}
$(function () {
    $("#savePaciente").data("id", 0);
    $("#tabObstetricia").data("id", 0);
    $(".selectpicker").selectpicker();
    $(".date-mask").inputmask();

    $("#contenido").on("changed.bs.select", "#cboProvincia", function (e) {
        change_cboProvincia(this);
    });

    $("#contenido").on("changed.bs.select", "#cboCanton", function (e) {
        change_cboCanton(this);
    });

    $("#contenido").on("change", "#pac_Genero", function (e) {
        change_Genero(this);
    });
    $('#pac_nacionalidad').on('changed.bs.select', function (e) {
        newValue = $('#pac_nacionalidad').selectpicker("val");
        switch (newValue) {
            case "1":
                $("#pac_PaisNac").val("Ecuador");
                break;
            default :
                $("#pac_PaisNac").val("");
                break;
        }
    });

    $("#savePaciente").click(function (e) {
        var id = $("#savePaciente").data("id");
        if (save()) {
            dir();
        }
    });
    $("#cancelPaciente").click(function (e) {
        dir();
    });

    var utc = new Date().toJSON().slice(0, 10);
    $("#pac_FPP").val(utc);

});

function dir() {
    switch (return_dir) {
        case 1:
            limpiarPaciente();
            break;
        case 2:
            $("#contenido").load("paciente/listPacientes.jsp");
            break;
        case 3:
            id_cedula = $("#savePaciente").data("cedula");
            $("#contenido").load("consulta/ListHistorialC.jsp", function () {
                load_Paciente(id_cedula);
            });
            break;
    }
}

function change_cboProvincia(cbo) {
    $.ajax({
        type: 'Post',
        url: 'pruebaCombo',
        async: false,
        data: {
            id: $(cbo).val(),
            op: 'cantones'
        },
        success: function (response) {
            var cboCanton = $("#cboCanton");
            $(cboCanton).html(response);
            $(cboCanton).selectpicker('refresh');
        }
    });
}

function change_cboCanton(cbo) {
    $.ajax({
        type: 'Post',
        url: 'pruebaCombo',
        async: false,
        data: {
            id: $(cbo).val(),
            op: 'parroquias'
        },
        success: function (response) {
            var cboParroquia = $("#cboParroquia");
            $(cboParroquia).html(response);
            $(cboParroquia).selectpicker('refresh');
        }

    });
}

function change_Genero(cbo) {
    var tabObstetricia = $("#tabObstetricia");
    if ($(cbo).val() === "1" || $(cbo).val() === "0" || $(cbo).val() === "3") {
        $(tabObstetricia).addClass("disabledTab");
    } else if ($(cbo).val() === "2") {
        $(tabObstetricia).removeClass("disabledTab");
    }
}

