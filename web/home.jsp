<%@page import="mvc.controlador.entidades.sm.Medico"%>
<%@page import="java.util.List"%>
<%@page import="mvc.modelo.smDaoImp.IngresosDaoImp"%>
<%@page import="mvc.controlador.entidades.sm.HistorialClinico"%>
<%@page import="mvc.modelo.smDao.IngresosDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Jessika 3.0</title>

        <!-- Bootstrap Core CSS -->
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="resources/bootstrap/css/sb-admin.css" rel="stylesheet">
        <link href="resources/css/style_home.css" rel="stylesheet" type="text/css"/>
        <!-- Custom Fonts -->
        <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="resources/font-awesome/css/style.css" rel="stylesheet" type="text/css">

        <link href="resources/bootstrap/css/bootstrap-datetimepicker.css"  rel="stylesheet" type="text/css"/>

        <link href="resources/js-ui/jquery-ui.css" rel="stylesheet"/>
        <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="resources/bootstrap/table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>



        <script src="resources/js/jquery.min.js" type="text/javascript" ></script>
        <script src="resources/bootstrap/mask/jquery.inputmask.bundle.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"  ></script>
        <script src="resources/bootstrap/table/bootstrap-table.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/table/bootstrap-table-contextmenu.js" type="text/javascript"></script>
        <script src="resources/bootstrap/table/locale/bootstrap-table-es-ES.min.js" type="text/javascript"></script>

        <!--    desde aqui mis links -->

        <script type="text/javascript" src="resources/js/moment.js" async="async"></script>
        <script type="text/javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.js" ></script>        
        <script src="resources/bootstrap/js/bootstrap-datetimepicker.es.js" type="text/javascript" ></script>


        <script type="text/javascript" src="resources/js/alertify.min.js" async="async"></script>
        <!--<script type="text/javascript" src="resources/js/alertify.js" async="async"></script>-->
        <link rel="stylesheet" href="resources/css/alertify.core.css" />
        <link rel="stylesheet" href="resources/css/alertify.default.css" > />
        <script src="resources/js/jquery.twbsPagination.js" type="text/javascript" ></script>
        <script src="resources/js/validate.js" type="text/javascript"></script>
        <script src="resources/js/style.js" type="text/javascript"></script>
        <script src="resources/js/home.js" type="text/javascript"></script>


        <link rel="stylesheet" href="resources/bootstrap/css/bootstrap-select.min.css" >
        <link href="resources/bootstrap/css/ajax-bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
        <script src="resources/bootstrap/js/bootstrap-select.min.js"></script>
        <script src="resources/bootstrap/js/ajax-bootstrap-select.min.js" type="text/javascript"></script>

    </head>


    <%
        HttpSession sesion = request.getSession();
        String usuario = (String) sesion.getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("login.jsp");
        }
    %>
    <body>
        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="home.jsp">Jessika 3.0</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">


                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%=sesion.getAttribute("usuario") == null ? "" : sesion.getAttribute("usuario")%><b class="caret"></b></a>
                        <ul class="dropdown-menu">                            
                            <li>
                                <a id="aSalida" href="#"><i class="fa fa-fw fa-power-off"></i> Salir</a>
                            </li>
                        </ul>
                    </li>


                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse" id="TabAdm">
                    <%
                        if (sesion.getAttribute("rol") != null) {

                            int rol = Integer.parseInt(sesion.getAttribute("rol").toString());
                            switch (rol) {
                                case 1:
                    %>
                    <%@include file="WEB-INF/jspf/menu_admin.jspf" %>
                    <%
                            break;
                        case 2:
                    %>
                    <%@include file="WEB-INF/jspf/menu_medico.jspf" %>
                    <%
                                    break;
                            }
                        }
                    %>
                </div>
                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="container-fluid">

                    <!-- Page Heading -->

                    <div class="row">
                        <div class="col-sm-12">                        
                            <div class="panel panel-default" id="panel">
                                <!--<div class="panel-heading">
                                    <h3 class="panel-title" id="titulo"> </h3>
                                </div>-->
                                <div id="contenido" class="panel-body">
                                    <!-- /. ROW  -->
                                    <div class="row">
                                        <div class="col-md-3 col-sm-6 col-xs-6">           
                                            <div class="panel panel-back noti-box">
                                                <span class="icon-box bg-color-red set-icon">
                                                    <i class="fa fa-envelope-o"></i>
                                                </span>
                                                <div class="text-box" >
                                                    <p class="main-text">120</p>
                                                    <p class="text-muted">Pacientes</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-6">           
                                            <div class="panel panel-back noti-box">
                                                <span class="icon-box bg-color-green set-icon">
                                                    <i class="fa fa-bars"></i>
                                                </span>
                                                <div class="text-box" >
                                                    <p class="main-text">30 </p>
                                                    <p class="text-muted">Consultas</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-6">           
                                            <div class="panel panel-back noti-box">
                                                <span class="icon-box bg-color-blue set-icon">
                                                    <i class="fa fa-bell-o"></i>
                                                </span>
                                                <div class="text-box" >
                                                    <p class="main-text">240 </p>
                                                    <p class="text-muted">Hospitaliaciones</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-6">           
                                            <div class="panel panel-back noti-box">
                                                <span class="icon-box bg-color-brown set-icon">
                                                    <i class="glyphicon glyphicon-user"></i>
                                                </span>
                                                <div class="text-box" >
                                                    <p class="main-text">3</p>
                                                    <p class="text-muted">Usuarios</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                                <!-- /.col-sm-4 -->

                            </div>
                            <!-- /.row -->


                            <!-- / contenido -->            
                        </div>
                        <!-- /.container-fluid -->

                    </div>
                </div>
            </div>

        </div>
        <!-- /.col-sm-4 -->
    </body>
    <script type="text/javascript">
        $("#contenido").load("consulta/ListHistorialC.jsp");
    </script>
    <script type="text/javascript" src="usuario/login.js"></script>
</html>
