/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.ejb.servicioLocal;
import cl.model.Lector;
import cl.model.Libro;
import cl.model.Multa;
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

@WebServlet(name = "DevController", urlPatterns = {"/DevController"})
public class DevController extends HttpServlet {

    //definir servicio para poder llamarlo:
    @EJB
    private servicioLocal servicio;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("fechaActual", new Date()); //la fecha de la devolucion
        request.setAttribute("LectorList", servicio.LectorList());
        request.setAttribute("LibroList", servicio.LibroList());
        request.setAttribute("PrestamoList", servicio.PrestamoList());
        request.getRequestDispatcher("devolucion.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fechaA = request.getParameter("x1"); //date
        String id = request.getParameter("x2"); //int
        int idPrestamo = 0;
        int idLibro = 0;
        int idmulta = 0;
        String rut = "";
        //String rutLector;
        String error = "";

        //campos vacios:
        if (fechaA.isEmpty() || id.isEmpty()) {
            error += "Complete los campos por favor";
        } else {

            //solo numeros INT
            try {
                idPrestamo = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                error += "Ingrese id válido";
            }
        }

        Prestamo p = servicio.findPrestamo(idPrestamo); //RODO: DEBES BUSCAR PRÉSTAMO FUERA, PARA EVITAR QUE SEA NULO

        if (p == null) { //TE REGALO ÉSTE HERMOSO IF
            error += "Préstamo no existe";
        } else {
            if (p.getEstado() == 1) { //devolucion al dia: estado prestamo = 1 devuelto
                error += "Préstamo ya fue devuelto";
            }
        }
        //Libro libro = servicio.findLibro(idLibro); LÍNEA QUE MOVÍ HACIA ABAJO-

//        rutLector = servicio.findPrestamo(idLibro).getLectorFK().getRut();
        //Lector lector = servicio.findPrestamo(idLibro).getLectorFK();
        Lector lector = servicio.findLector(rut);
        request.setAttribute("error", error);

        if (error.isEmpty()) {

            try {
                //int stock = libro.getStock();
                Multa m = new Multa();

                //p = servicio.findPrestamo(idPrestamo); LÍNEA QUE PUSE MÁS ARRIBA CON EL IF REGALADO
                idLibro = p.getLibroFK().getIdlibro();  //ADEMÁS NUNCA BUSCASTE EL LIBRO, POR ESO TE DA EL STOCK NULO DEL MISMO, DEBÍAS BUSCARLO ASÍ:

                //ALLÍ TIENES LA ID, PARA BUSCAR EL LIBRO COMO LO HACES HACIA ABAJO, Y EL RESTO YA FUNCIONA COMO LO TIENES.
                Libro libro = servicio.findLibro(idLibro);
                int stock = libro.getStock(); //UNA VEZ QUE TENGO EL LIBRO, PUEDO OBTENER SU STOCK, ÉSTE ERA EL NULLPOINTEREXCEPTION, ERA NULO PORQUE NUNCA HUBO LIBRO

                Date fechaActual = new SimpleDateFormat("yyyy-MM-dd").parse(fechaA); //valido formato fecha acutal de la dev
                Date fechaDevolucion;
                fechaDevolucion = p.getFechadevolucion();

                //devolucion al dia:
                if (fechaActual.before(fechaDevolucion)) {
                    p.setEstado(1);//actualizar prestamo: devuelto
                    libro.setStock(stock + 1);//actualizar stock libro stock +1
                } else {

                    //devolucion atrasada:
                    //if (fechaActual.after(fechaDevolucion)) { //si fecha ingresada en devolucion es despues de la fecha dev de prestamo
                    p.setEstado(1); //actualizo prestamo: devuelto
                    libro.setStock(stock + 1); //actualizo libro stock +1
                    lector.setEstado(1); //actualizar estado lector: 1               

                    //ingresar multa
                    m.setDescripcion("devolucion atrasada");
                    m.setEstado("1");
                    m.setIdmulta(idmulta); //automatico
                    m.setLectorFK(lector);
                    m.setMonto(2000);
                    servicio.persist(m); //ingreso multa
                }

                servicio.merge(libro);
                servicio.merge(p);
                servicio.flush();

            } catch (ParseException ex) {
                Logger.getLogger(DevController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        request.setAttribute("LectorList", servicio.LectorList());
        request.setAttribute("LibroList", servicio.LibroList());
        request.setAttribute("PrestamoList", servicio.PrestamoList());
        request.setAttribute("ok", "Devolución realizada");
        request.getRequestDispatcher("devolucion.jsp").forward(request, response);

    }
}
