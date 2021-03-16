
package cl.controller;

import cl.ejb.servicioLocal;
import cl.model.Lector;
import cl.model.Multa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MultaController", urlPatterns = {"/MultaController"})
public class MultaController extends HttpServlet {

//definir servicio para poder llamarlo:
    @EJB
    private servicioLocal servicio;
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("LectorList", servicio.LectorList());
        request.setAttribute("MultaList", servicio.MultaList()); //"nombre", traigo de servicio la lista.
        request.getRequestDispatcher("multas.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String x1 = request.getParameter("x1"); //int
        String descripcion = request.getParameter("x2");
        String estado = request.getParameter("x3");
        String rut = request.getParameter("x4");
        String error = "";
        
        int monto = 0;
        
        
        //campos vacios:
         if (x1.isEmpty() || descripcion.isEmpty() || estado.isEmpty() || rut.isEmpty()) {
            error += "Complete los campos por favor";
        }
        
         //solo numeros INT
         
        try {
            monto = Integer.parseInt(x1);

        } catch (Exception e) {
            error += "Ingrese solo numeros en monto";
        }
        
        // rut VARCHAR de lector no se puede repetir:
        
         if (servicio.findLector(rut) != null) { 
             error += "Rut de lector ya existe";
         }
        
         
        
         if (error.isEmpty()) { //si no hay error
            Multa m = new Multa();
            m.setMonto(monto);
            m.setDescripcion(descripcion);
            m.setEstado(estado);
            Lector l = servicio.findLector(rut);
            m.setLectorFK(l);
            servicio.persist(m); //guardo multa en la BD
            servicio.flush();
            request.setAttribute("ok", "La multa fue registrada en la bd");
        } else {
            request.setAttribute("error", "Rut de Lector ya existe");
        }

        request.setAttribute("LectorList", servicio.LectorList());
        request.setAttribute("MultaList", servicio.MultaList()); //"nombre", traigo de servicio la lista.
        request.getRequestDispatcher("multas.jsp").forward(request, response);
        
    }

  

}
