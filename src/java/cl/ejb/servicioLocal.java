
package cl.ejb;

import cl.model.Lector;
import cl.model.Libro;
import cl.model.Multa;
import cl.model.Prestamo;
import java.util.List;
import javax.ejb.Local;


@Local
public interface servicioLocal {
    void persist (Object o); //insert
    void merge (Object o); //update
    void flush(); //sincronizar la data
    Lector findLector (String rut);
    Libro findLibro (int id);
    Multa findMulta (int id);
    Prestamo findPrestamo (int id);
    List<Lector>LectorList();
    List<Libro>LibroList();
    List<Multa>MultaList();
    List<Prestamo>PrestamoList();
    
}
