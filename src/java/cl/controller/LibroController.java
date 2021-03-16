package cl.controller;

import cl.ejb.servicioLocal;
import cl.model.Libro;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LibroController", urlPatterns = {"/LibroController"})
public class LibroController extends HttpServlet {

//definir servicio para poder llamarlo:
    @EJB
    private servicioLocal servicio;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("LibroList", servicio.LibroList()); //"nombre", traigo de servicio la lista.
        request.getRequestDispatcher("libros.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("x1");
        String autor = request.getParameter("x2");
        String tipo = request.getParameter("x3");
        String x4 = request.getParameter("x4");
        String x5 = request.getParameter("x5");
        String error = "";
        int estado = 0, stock = 0;

        if (titulo.isEmpty() || autor.isEmpty() || tipo.isEmpty() || x4.isEmpty() || x5.isEmpty()) {
            error += "Complete los campos por favor";
        }
        try {
            estado = Integer.parseInt(x4);
            stock = Integer.parseInt(x5);
        } catch (Exception e) {
            error += "Ingrese solo numeros en estado y stock";
        }

        if (error.isEmpty()) { //si no hay error
            Libro l = new Libro();
            l.setTitulo(titulo);
            l.setAutor(autor);
            l.setTipo(tipo);
            l.setEstado(estado);
            l.setStock(stock);
            servicio.persist(l); //guardo paciente en la BD
            servicio.flush();
            request.setAttribute("ok", "El libro fue registrado en la bd");
        } else {
            request.setAttribute("error", "id del libro ya registrado en bd");
        }

        request.setAttribute("LibroList", servicio.LibroList());
        request.getRequestDispatcher("libros.jsp").forward(request, response);

    }

}
