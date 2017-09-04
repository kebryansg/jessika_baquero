$('.form_date').datetimepicker({
    format: "yyyy-mm-dd",
    language: 'es',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    pickerPosition: "bottom-left",
    minView: 2,
    forceParse: 0
});
$('.form_time').datetimepicker({
    language: 'es',
    weekStart: 1,
    //todayBtn: 1,
    autoclose: 1,
    //todayHighlight: 1,
    startView: 1,
    minView: 0,
    maxView: 1,
    forceParse: 0
});

$(".selectpicker").selectpicker().selectpicker("render");
