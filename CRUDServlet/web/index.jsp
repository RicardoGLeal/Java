<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CRUD Direcciones</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightseagreen">
                <div>
                    <a href="<%=request.getContextPath()%>/Controller" class="navbar-brand"> CRUD de Direcciones</a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Controller"
                           class="nav-link">Direcciones</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <div class="container">
                <h3 class="text-center">Lista de Direcciones</h3>
                <hr>
                <div class="container text-left">
                    <form action="Controller" method="post">
                        <input name="link" type="submit" value="Recargar" class="btn btn-success">
                        <input name="link" type="submit" value="Agregar" class="btn btn-success">
                    </form>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Calle</th>
                            <th>numExt</th>
                            <th>Colonia</th>
                            <th>CP</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="Controller" method="post">
                        <c:forEach var="direction" items="${listDirections}">          
                            <tr>
                                <td><c:out value="${direction.id}" /></td>
                                <td><c:out value="${direction.calle}" /></td>
                                <td><c:out value="${direction.numExt}" /></td>
                                <td><c:out value="${direction.colonia}" /></td>
                                <td><c:out value="${direction.cp}" /></td>
                                <td><button type="submit" name="link" id class="btn btn-success" value="Edit/<c:out value='${direction.id}' />" id="<c:out value='${direction.id}' />">Editar</button>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                <td><button type="submit" name="link" id class="btn btn-success" value="Delete/<c:out value='${direction.id}' />" id="<c:out value='${direction.id}' />">Eliminar</button>
                            </tr>
                        </c:forEach>
                        </form>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>