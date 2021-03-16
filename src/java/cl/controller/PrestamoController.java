/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.ejb.servicioLocal;
import cl.model.Lector;
import cl.model.Libro;
import cl.model.Prestamo;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PrestamoController", urlPatterns = {"/PrestamoController"})
public class PrestamoController extends HttpServlet {

    //definir servicio para poder llamarlo:
    @EJB
    private servicioLocal servicio;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("fechaActual", new Date());
        request.setAttribute("LectorList", servicio.LectorList());
        request.setAttribute("LibroList", servicio.LibroList());
        request.setAttribute("PrestamoList", servicio.PrestamoList());
        request.getRequestDispatcher("prestamo.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fechaA = request.getParameter("x1"); //date
        String fechaD = request.getParameter("x2");
        String rutLector = request.getParameter("x3");
        String id = request.getParameter("x4"); //int
        int idLibro = 0;
        String error = "";

        //campos vacios:
        if (fechaA.isEmpty() || fechaD.isEmpty() || rutLector.isEmpty() || id.isEmpty()) {
            error += "Complete los campos por favor";
        } else {

            //solo numeros INT
            try {
                idLibro = Integer.parseInt(id);

            } catch (Exception e) {
                error += "Ingrese id válido";
            }
        }

        Lector lector = servicio.findLector(rutLector);
        Libro libro = servicio.findLibro(idLibro);

        // lector exista
        if (lector == null) {
            error += "Lector no existe, intente nuevamente";
        }

        // libro exista
        if (libro == null) {
            error += "Libro no existe, intente nuevamente";
        } else { // libro tenga stock: 
            if (libro.getStock() <= 0) { //stock libro en cero
                error += "Este libro no tiene stock, intente con otro";
            }
        }
        //lector o libro este activo. ESTADO ==1

        if (lector.getEstado() != 1 || libro.getEstado() != 1) {
            error += "El lector o el libro tienen que estar activos, por favor, intente nuevamente";
        }
        
        request.setAttribute("error", error);

        if (error.isEmpty()) {
            try {
                int stock = libro.getStock();
                Prestamo p = new Prestamo();
                Date fechaActual = new SimpleDateFormat("yyyy-MM-dd").parse(fechaA); //valido formato fecha
                Date fechaDevolucion = new SimpleDateFormat("yyyy-MM-dd").parse(fechaD);

                p.setFechaprestamo(fechaActual);
                p.setFechadevolucion(fechaDevolucion);
                p.setLectorFK(lector);
                p.setLibroFK(libro);
                p.setEstado(0); //el estado del prestamo queda en 0 = prestado
                libro.setStock(stock - 1); //registro prestamo, actualizar stock libro
                if (libro.getStock() == 0) {
                    libro.setEstado(0);
                }
                servicio.persist(p);
                servicio.merge(libro);
                servicio.flush();

            } catch (ParseException ex) {
                Logger.getLogger(PrestamoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        request.setAttribute("LectorList", servicio.LectorList());
        request.setAttribute("LibroList", servicio.LibroList());
        request.setAttribute("PrestamoList", servicio.PrestamoList());
        request.setAttribute("ok", "Préstamo realizado");
        request.getRequestDispatcher("prestamo.jsp").forward(request, response);

    }
}