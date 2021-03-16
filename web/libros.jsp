<%-- 
    Document   : lector.jsp
    Created on : 21-06-2019, 15:03:27
    Author     : 18225637-4
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.min.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body class="blue-grey">
        <div class="container">
            <div class="card-panel z-depth-3"
                 <h1>Libros:</h1>

                <form action="LibroController" method="post">
                    
                    <input type="text" placeholder="Titulo" name="x1">
                    <br>
                    <input type="text" placeholder="Autor" name="x2">
                    <br>
                    <input type="text" placeholder="Tipo" name="x3">
                    <br>
                    <input type="text" placeholder="Estado" name="x4">
                    <br>
                    <input type="text" placeholder="Stock" name="x5">
                    <br>
                    <button class=" btn blue-grey">
                        Guardar
                        <i class="material-icons" left>Guardar</i>
                    </button>
                </form>
                <p>
           ${ok}
           ${error} 
        </p>
        <br>
            </div>
            <div class="card-panel z-depth-3">
                <form action="LibroController" method="get">
                    <table class="bordered">
                        <tr>
                            <th>Id</th>
                            <th>Título</th>
                            <th>Tipo</th>
                            <th>Autor</th>
                            <th>Estado</th>
                            <th>Stock</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${LibroList}" var="l">
                            <tr>
                                <td>${l.idlibro}</td>
                                <td>${l.titulo}</td>
                                <td>${l.tipo}</td>
                                <td>${l.autor}</td>
                                <td>${l.estado}</td>
                                <td>${l.stock}</td> 
                            </tr>
                        </c:forEach>

                    </table>
                </form>            
            </div>
                <script src="js/materialize.min.js" type="text/javascript"></script>   

    </body>
</html>
