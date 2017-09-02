var currentTab;
var composeCount = 0;
//initilize tabs
$(function () {

    //when ever any tab is clicked this method will be call
    
    /*$("#TabAdm").on("click", "a", function (e) {
        e.preventDefault();

        //$(this).tab('show');
        //$currentTab = $(this);
    });*/
    registerComposeButtonEvent();
    //registerCloseEvent();

});

/*function pestañaValida(a) {
    return $('a[name="' + $(a).attr("data-title") + '"]').length === 0;
}*/

//this method will demonstrate how to add tab dynamically
function registerComposeButtonEvent() {
    /* just for this demo */
    $("a[data-url]").click(function (e) {
        e.preventDefault(); 
        alert();
        $("#contenido").remove();
        $("#contenido").load($(this).attr("data-url"), function()
        {
            ini();
            $("#titulo").text($(this).attr("data-title"));
        });
        
        /*if (pestañaValida($(this))) {
            var tabId = "compose" + composeCount; //this is id on tab content div where the 
            composeCount = composeCount + 1; //increment compose count

            $('#TabAdm').append('<li><a name="' + $(this).attr("data-title") + '" href="#' + tabId + '"><button class="close closeTab" type="button" ><i class="fa fa-close"></i></button>' + $(this).attr("data-title") + '</a></li>');
            $('#ContentAdm').append('<div class="tab-pane fade" id="' + tabId + '"></div>');
            $("#" + tabId).load($(this).attr("data-url"), function () {
                ini();
            });
            $currentTab = $('#TabAdm a[href="#' + tabId + '"]');

            $(this).tab('show');
            showTab(tabId);
            registerCloseEvent();
        } else {
            var tab_href = $("a[name='" + $(this).attr("data-title") + "']").attr("href");
            showTab_a(tab_href);
            $currentTab = $('#TabAdm a[href="#' + tab_href + '"]');
        }*/

    });

}

//this method will register event on close icon on the tab..
function registerCloseEvent() {

    $(".closeTab").click(function () {

        //there are multiple elements which has .closeTab icon so close the tab whose close icon is clicked
        var tabContentId = $(this).parent().attr("href");
        $(this).parent().parent().remove(); //remove li of tab
        $(tabContentId).remove(); //remove respective tab content
        $('#TabAdm a:last').tab('show'); // Select first tab
        $currentTab = $('#TabAdm a:last');
    });
}

//shows the tab with passed content div id..paramter tabid indicates the div where the content resides
function showTab(tabId) {
    $('#TabAdm a[href="#' + tabId + '"]').tab('show');

}
function showTab_a(tabId) {
    $('#TabAdm a[href="' + tabId + '"]').tab('show');

}
//return current active tab
function getCurrentTab() {
    return $currentTab;
}

//This function will create a new tab here and it will load the url content in tab content div.
function craeteNewTabAndLoadUrl(parms, url, loadDivSelector) {

    $("" + loadDivSelector).load(url, function (response, status, xhr) {
        if (status == "error") {
            var msg = "Sorry but there was an error getting details ! ";
            $("" + loadDivSelector).html(msg + xhr.status + " " + xhr.statusText);
            $("" + loadDivSelector).html("Load Ajax Content Here...");
        }
    });

}

//this will return element from current tab
//example : if there are two tabs having  textarea with same id or same class name then when $("#someId") whill return both the text area from both tabs
//to take care this situation we need get the element from current tab.
function getElement(selector) {
    var tabContentId = $currentTab.attr("href");
    return $("" + tabContentId).find("" + selector);

}
function ini()
{
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
}


function removeCurrentTab() {
    var tabContentId = $currentTab.attr("href");
    $currentTab.parent().remove(); //remove li of tab
    $('#TabAdm a:last').tab('show'); // Select first tab
    $(tabContentId).remove(); //remove respective tab content
}
