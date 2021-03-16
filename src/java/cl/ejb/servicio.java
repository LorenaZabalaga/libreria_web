/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ejb;

import cl.model.Lector;
import cl.model.Libro;
import cl.model.Multa;
import cl.model.Prestamo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 18225637-4
 */
@Stateless
public class servicio implements servicioLocal {
    
    @PersistenceContext(unitName = "BibliosPU")
    private EntityManager em; //este procesa. 

    //Programar metodos:
    @Override
    public void persist(Object o) {
        em.persist(o);
    }

    @Override
    public void merge(Object o) {
        em.merge(o);
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public Lector findLector(String rut) {
        return em.find(Lector.class, rut);
    }

    @Override
    public Libro findLibro(int id) {
        return em.find(Libro.class, id);
    }

    @Override
    public Multa findMulta(int id) {
        return em.find(Multa.class, id);
    }

    @Override
    public Prestamo findPrestamo(int id) {
        return em.find(Prestamo.class, id);
    }

    @Override
    public List<Libro> LibroList() {
        return em.createNamedQuery("Libro.findAll").getResultList();
    }

    @Override
    public List<Multa> MultaList() {
        return em.createNamedQuery("Multa.findAll").getResultList();
    }

    @Override
    public List<Prestamo> PrestamoList() {
        return em.createNamedQuery("Prestamo.findAll").getResultList();
    }


    @Override
    public List<Lector> LectorList() {
        return em.createNamedQuery("Lector.findAll").getResultList();
    }

    


   
}
