$(function () {
    $("#cerrarMenu").click(function () {
        $(".menu-lateral").toggleClass("close-menu");
        $(".contenedor").toggleClass("open-contenedor");
        $("#cerrarMenu i").toggleClass("fa-chevron-circle-left");
        $("#cerrarMenu i").toggleClass("fa-chevron-circle-right");
    });

    $("a[data-url]").click(function (e) {
        e.preventDefault();
        if ($(this).attr("data-url") !== "") {
            $("#contenido").load($(this).attr("data-url"));
            $("#tituloPagina").text($(this).attr("data-title"));
        }

    });

    $("#contenido").on("keypress", ".solo-numero", function (e) {
        var key = window.Event ? e.which : e.keyCode;
        return ((key >= 48 && key <= 57) || key === 8);
    });
});

function openModal(id) {
    $("#" + id).modal('show');
    $.each($("#" + id + " input"), function () {
        $(this).val("");
    });
}
function openModal_Clean(id) {
    $("#" + id).modal('show');
}

function closeModal(id) {
    $("#" + id).modal('toggle');

}


/*
 $('#myModal').on('hide',function(e){
 if(!confirm('You want to close me?'))
 e.preventDefault();
 });
 */