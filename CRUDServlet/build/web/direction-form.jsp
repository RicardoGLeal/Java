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
                    <a href="<%=request.getContextPath()%>/Controller" class="navbar-brand"> Direcciones </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Controller" class="nav-link">Direcciones</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="Controller" method="post">
                        <caption>
                            <h2>
                                <c:if test="${direction != null}">
                                    Editar Direccion
                                </c:if>
                                <c:if test="${direction == null}">
                                    Agregar Direccion
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${direction != null}">
                            <input type="hidden" name="id" value="<c:out value='${direction.id}' />" />
                        </c:if>
                        <fieldset class="form-group">
                            <label>Calle</label> <input type="text" value="<c:out value='${direction.calle}' />" class="form-control" name="calle" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>NumExt</label> <input type="number" value="<c:out value='${direction.numExt}' />" class="form-control" name="numExt">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Colonia</label> <input type="text" value="<c:out value='${direction.colonia}' />" class="form-control" name="colonia">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>CP</label> <input type="number"value="<c:out value='${direction.cp}' />" class="form-control" name="cp">
                        </fieldset>
                        <c:if test="${direction != null}">
                            <button name="link" type="submit" class="btn btn-success" value="Update/<c:out value='${direction.id}' />">Actualizar</button>
                        </c:if>
                        <c:if test="${direction == null}">
                            <button name="link" type="submit" class="btn btn-success" value="Insert">Guardar</button>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>