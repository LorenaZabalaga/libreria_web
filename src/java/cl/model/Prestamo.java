/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 18225637-4
 */
@Entity
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
    , @NamedQuery(name = "Prestamo.findByIdprestamo", query = "SELECT p FROM Prestamo p WHERE p.idprestamo = :idprestamo")
    , @NamedQuery(name = "Prestamo.findByFechaprestamo", query = "SELECT p FROM Prestamo p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "Prestamo.findByFechadevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "Prestamo.findByEstado", query = "SELECT p FROM Prestamo p WHERE p.estado = :estado")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprestamo")
    private Integer idprestamo;
    @Column(name = "fechaprestamo")
    @Temporal(TemporalType.DATE)
    private Date fechaprestamo;
    @Column(name = "fechadevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechadevolucion;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "lectorFK", referencedColumnName = "rut")
    @ManyToOne
    private Lector lectorFK;
    @JoinColumn(name = "libroFK", referencedColumnName = "idlibro")
    @ManyToOne
    private Libro libroFK;

    public Prestamo() {
    }

    public Prestamo(Integer idprestamo) {
        this.idprestamo = idprestamo;
    }

    public Integer getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(Integer idprestamo) {
        this.idprestamo = idprestamo;
    }

    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Lector getLectorFK() {
        return lectorFK;
    }

    public void setLectorFK(Lector lectorFK) {
        this.lectorFK = lectorFK;
    }

    public Libro getLibroFK() {
        return libroFK;
    }

    public void setLibroFK(Libro libroFK) {
        this.libroFK = libroFK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprestamo != null ? idprestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.idprestamo == null && other.idprestamo != null) || (this.idprestamo != null && !this.idprestamo.equals(other.idprestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.model.Prestamo[ idprestamo=" + idprestamo + " ]";
    }
    
}
