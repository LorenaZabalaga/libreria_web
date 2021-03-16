<%-- 
    Document   : prestamo
    Created on : 01-07-2019, 18:50:07
    Author     : 18225637-4
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/materialize.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body class="blue-grey">
        <div class="container">
            <div class="card-panel z-depth-3">
                <form action="PrestamoController" method="POST">
                    <input type="text" placeholder="Fecha actual" name="x1" class="calendario" value="<fmt:formatDate pattern='yyyy-MM-dd' value="${fechaActual}"/>" readonly />
                    <br>
                    <input type="text" placeholder="Fecha devolución" name="x2" class="calendario"/>
                    <br>
                    <input type="text" placeholder="Rut Lector" name="x3">
                    <br>
                    <input type="text" placeholder="Id Libro" name="x4">
                    <br>
                    <button class="btn blue">Guardar</button>
                    <p>
                        ${ok}
                        ${error}
                    </p>
                </form>
                <form action="PrestamoController" method="GET">
                    <h3>Libros</h3>
                    <table class="bordered">
                        <tr>               
                            <th>Id</th>
                            <th>Titulo</th>
                            <th>Autor</th>
                            <th>Stock</th>
                        </tr>
                        <c:forEach items="${LibroList}" var="l">
                            <tr>
                                <td>${l.idlibro}</td>
                                <td>${l.titulo}</td>
                                <td>${l.autor}</td>
                                <td>${l.stock}</td> 
                            </tr>
                        </c:forEach>
                    </table>
                    <h3>Lectores</h3>
                    <table class="bordered">
                        <tr>
                            <th>Rut</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                        </tr>
                        <c:forEach items="${LectorList}" var="l">
                            <tr>
                                <td>${l.rut}</td>
                                <td>${l.nombre}</td>
                                <td>${l.apellido}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <h3>Prestamos</h3>
                    <table class="bordered">    
                        <tr>
                            <th>Id</th>
                            <th>Titulo</th> 
                            <th>Lector</th> 
                            <th>Fecha Dev</th> 
                        </tr>
                        <c:forEach items="${PrestamoList}" var="p">
                            <tr>
                                <td>${p.idprestamo}</td>
                                <td>${p.libroFK.titulo}</td>
                                <td>${p.lectorFK.nombre} ${p.lectorFK.apellido}</td>
                                <td><fmt:formatDate pattern='yyyy-MM-dd' value="${p.fechadevolucion}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>  
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var elems = document.querySelectorAll('.calendario');
                var instances = M.Datepicker.init(elems, {
                    format: "yyyy-mm-dd"
                });
            });
        </script>
    </body>
</html>