/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 18225637-4
 */
@Entity
@Table(name = "lector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lector.findAll", query = "SELECT l FROM Lector l")
    , @NamedQuery(name = "Lector.findByRut", query = "SELECT l FROM Lector l WHERE l.rut = :rut")
    , @NamedQuery(name = "Lector.findByNombre", query = "SELECT l FROM Lector l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Lector.findByApellido", query = "SELECT l FROM Lector l WHERE l.apellido = :apellido")
    , @NamedQuery(name = "Lector.findByEstado", query = "SELECT l FROM Lector l WHERE l.estado = :estado")
    , @NamedQuery(name = "Lector.findByTipo", query = "SELECT l FROM Lector l WHERE l.tipo = :tipo")
    , @NamedQuery(name = "Lector.findByLibrospedidos", query = "SELECT l FROM Lector l WHERE l.librospedidos = :librospedidos")
    , @NamedQuery(name = "Lector.findByClave", query = "SELECT l FROM Lector l WHERE l.clave = :clave")})
public class Lector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "rut")
    private String rut;
    @Size(max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 20)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "estado")
    private Integer estado;
    @Size(max = 20)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "librospedidos")
    private Integer librospedidos;
    @Size(max = 100)
    @Column(name = "clave")
    private String clave;
    @OneToMany(mappedBy = "lectorFK")
    private List<Prestamo> prestamoList;
    @OneToMany(mappedBy = "lectorFK")
    private List<Multa> multaList;

    public Lector() {
    }

    public Lector(String rut) {
        this.rut = rut;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getLibrospedidos() {
        return librospedidos;
    }

    public void setLibrospedidos(Integer librospedidos) {
        this.librospedidos = librospedidos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @XmlTransient
    public List<Multa> getMultaList() {
        return multaList;
    }

    public void setMultaList(List<Multa> multaList) {
        this.multaList = multaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rut != null ? rut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lector)) {
            return false;
        }
        Lector other = (Lector) object;
        if ((this.rut == null && other.rut != null) || (this.rut != null && !this.rut.equals(other.rut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.model.Lector[ rut=" + rut + " ]";
    }
    
}
