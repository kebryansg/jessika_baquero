<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="resources/js/jquery.min.js" type="text/javascript"></script>
        <!-- Mask -->
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/jquery.twbsPagination.min.js" type="text/javascript"></script>
        <!-- Mask -->


    </head>
    <body>
        <div id="page-content">

        </div>
        <ul id="pagination-demo" class="pagination-sm"></ul>


        <script type="text/javascript">
            $('#pagination-demo').twbsPagination({
                totalPages: 70,
                visiblePages: 7,
                first: "|<",
                prev: "<<",
                next: ">>",
                last: ">|",
                onPageClick: function (event, page) {
                    $('#page-content').text('Page ' + page);
                }
            });
            //$(".date-mask").inputmask({ alias: "yyyy-mm-dd"});
        </script>
    </body>
</html>