<%-- 
    Document   : login
    Created on : 27-06-2019, 20:47:19
    Author     : 18225637-4
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inicio Sesión</h1>
         <form action="LoginController" method="post">
            <input type="text" name="x1" placeholder="Usuario"/><br>
            <input type="text" name="x2" placeholder="Clave"/><br>
            <br>
            <button>Login</button>
            
        </form>
        <p>
            ${ok}
            ${error}
        </p>
        
    </body>
</html>
