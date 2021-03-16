<%-- 
    Document   : multas
    Created on : 22-06-2019, 23:27:05
    Author     : lorraine
 <select name="x4">
                <c:forEach items="${MultaList}" var="m">
                    <option value="${m.lectorFK}">
       
                    </option>
                </c:forEach>
            </select>
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
                 <h1>Multas:</h1>

                <form action="MultaController" method="post">
                    
                    <input type="text" placeholder="Monto" name="x1">
                    <br>
                    <input type="text" placeholder="Descripción" name="x2">
                    <br>
                    <input type="text" placeholder="Estado" name="x3">
                    <br>
                    <input type="text" placeholder="Rut Lector" name="x4">
                    <br>
                     
                    <button class=" btn blue-grey">
                        Crear
                        <i class="material-icons" left></i>
                    </button>
                </form>
                <p>
           ${ok}
           ${error} 
        </p>
        <br>
            </div>
            <div class="card-panel z-depth-3">
                <form action="MultaController" method="get">
                    <table class="bordered">
                        <tr>
                            <th>Id</th>
                            <th>Monto</th>
                            <th>Descripción</th>
                            <th>Lector</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${MultaList}" var="m">
                            <tr>
                                <td>${m.idmulta}</td>
                                <td>${m.monto}</td>
                                <td>${m.descripcion}</td>
                                <td>${m.lectorFK.rut}</td>
                            </tr>
                        </c:forEach>

                    </table>
                </form>            
            </div>
                <script src="js/materialize.min.js" type="text/javascript"></script>   

    </body>
</html>
