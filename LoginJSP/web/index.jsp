<%-- 
    Document   : index
    Created on : 17/06/2020, 09:55:18 PM
    Author     : ricar

Este jsp tiene un formulario, el cual se utiliza para el ingreso del usuario y 
contraseña para el inicio de sesión.
Este jsp realiza tres distintas comprobaciones para saber si el usuario ya está 
logueado o no o si los datos proporcionados 
fueron incorrectos. Para cada caso se imprime un bloque de html distinto.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <title>Inicio</title>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightseagreen">

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/" class="nav-link">Inicio</a></li>
                </ul>
            </nav>
        </header>
        <div class="container col-md-5">
            <c:if test="${failedLogging == true}">
                <h4 style="text-align: center">Inicio de Sesión Incorrecto, verifica los datos.</h4>
            </c:if>
            
            
            <c:if test="${logged != null}">
                <h3>Bienvenido! <c:out value='${logged}'/>  Has iniciado sesion correctamente</h3>
            </c:if>

            <c:if test="${logged == null}">
                <div class="card">
                    <div class="card-body">
                        <h2 style="text-align: center">Iniciar Sesion</h2>
                        <p>Introduce tu usuario y contraseña</p>
                        <br>
                        <form action="Controller" method="post">
                            <fieldset class="form-group">
                                <label>Usuario</label> <input type="text" class="form-control" name="User" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Contraseña</label> <input type="password" class="form-control" name="Password" required="required">
                            </fieldset>
                            <button name="link" type="submit" class="btn btn-success" value="Login">Sign in</button>
                        </form>
                    </div>
                </div>
            </c:if>

        </div>
    </body>
</html>
