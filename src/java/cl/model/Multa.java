/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 18225637-4
 */
@Entity
@Table(name = "multa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Multa.findAll", query = "SELECT m FROM Multa m")
    , @NamedQuery(name = "Multa.findByIdmulta", query = "SELECT m FROM Multa m WHERE m.idmulta = :idmulta")
    , @NamedQuery(name = "Multa.findByMonto", query = "SELECT m FROM Multa m WHERE m.monto = :monto")
    , @NamedQuery(name = "Multa.findByDescripcion", query = "SELECT m FROM Multa m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Multa.findByEstado", query = "SELECT m FROM Multa m WHERE m.estado = :estado")})
public class Multa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmulta")
    private Integer idmulta;
    @Column(name = "monto")
    private Integer monto;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "lectorFK", referencedColumnName = "rut")
    @ManyToOne
    private Lector lectorFK;

    public Multa() {
    }

    public Multa(Integer idmulta) {
        this.idmulta = idmulta;
    }

    public Integer getIdmulta() {
        return idmulta;
    }

    public void setIdmulta(Integer idmulta) {
        this.idmulta = idmulta;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Lector getLectorFK() {
        return lectorFK;
    }

    public void setLectorFK(Lector lectorFK) {
        this.lectorFK = lectorFK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmulta != null ? idmulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Multa)) {
            return false;
        }
        Multa other = (Multa) object;
        if ((this.idmulta == null && other.idmulta != null) || (this.idmulta != null && !this.idmulta.equals(other.idmulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.model.Multa[ idmulta=" + idmulta + " ]";
    }
    
}
