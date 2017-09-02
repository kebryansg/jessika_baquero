<%@page import="java.util.Date"%>
<%@page import="mvc.modelo.smDaoImp.ExcelDaoImp"%>
<%@page import="mvc.modelo.smDao.ExcelDao"%>
<%@page import="mvc.modelo.smDao.ExcelDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") != null) {
        response.sendRedirect("home.jsp");
    }
%>
<!DOCTYPE html>
<html >
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Jessika 3.0</title>
        <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
        <link href="resources/font-awesome/css/font-awesome.css" rel="stylesheet"/>
        <link href="resources/font-awesome/css/custom.css" rel="stylesheet"/>   
        <link rel="stylesheet" href="resources/css/alertify.core.css" />
        <link rel="stylesheet" href="resources/css/alertify.default.css" />
        <link rel="stylesheet" href="resources/bootstrap/css/bootstrap-select.min.css" />
        <link href="resources/css/style_home.css" rel="stylesheet"/>
        
        <script src="resources/js/jquery.min.js" type="text/javascript" ></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap-select.min.js"></script>
        <script type="text/javascript" src="resources/js/alertify.min.js" async="async"></script>
        <script src="resources/js/validate.js" type="text/javascript"></script>
        <script type="text/javascript" src="usuario/login.js"></script>
    </head>
    <body>

        <div class="full-page">
            <div class="container">
                <div class="row text-center ">
                    <div class="col-md-12">
                        <br>
                        <h2> Jessika 3.0 : Login</h2>

                        <h5>( Inicia sesión para acceder al sistema)</h5>
                        <br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <strong>   Ingrese Los Datos </strong>  
                            </div>
                            <div class="panel-body">
                                <form role="form">
                                    <br />
                                    <div class="form-group input-group">
                                        <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                        <input  id="txtUsuario" validate="text" type="text" class="form-control" placeholder="Usuario " />
                                    </div>
                                    <div class="form-group input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                        <input id="txtClave" validate="text" type="password" class="form-control"  placeholder="Contraseña" />
                                    </div>
                                    <div class="form-group">
                                        <select class="selectpicker form-control" id="cboTipo">
                                            <!--<option value="1" >Administrador</option>
                                            <option value="2" >Médico</option>-->
                                        </select>
                                    </div>


                                    <div class="form-group">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" /> Recordarme
                                        </label>
                                        <span class="pull-right">
                                            <a href="#" >Olvidó su contraseña? </a> 
                                        </span>
                                    </div>

                                    <a href="#"  id="btnLogin" class="btn btn-primary ">Iniciar sesión</a>

                                </form>
                            </div>

                        </div>
                    </div>


                </div>
            </div>
            <div class="logo-emp">
                <a class="btn" data-toggle="modal" data-target="#myModal"><img src="resources/img/Logo-Ui-Life-10-compressor.png" class="img-responsive img-logo"></a> 
            </div>
        </div>
        <%@include file="carp_uilife/about_.jsp" %>
    </body>

</html>
