/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.ejb.servicioLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @EJB
    private servicioLocal servicio;
    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         request.setAttribute("LectorList", servicio.LectorList());
         request.getRequestDispatcher("login.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String usuario = request.getParameter("x1");
        String clave = request.getParameter("x2");
        
        String error = "";
        
        if (usuario.isEmpty() || clave.isEmpty()) {
            error += "Complete los campos por favor";
        }
        
        if (clave.equalsIgnoreCase("123") && usuario.equalsIgnoreCase("admin")) {
            
        } request.setAttribute("ok", "Haz iniciado sesión como Administrador");
        
    }

  

}
