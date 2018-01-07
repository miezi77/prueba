/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxServerSide.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Factura entity encapsulates the data of each invoice.
 * <ul>
 *  <li><stron>numFactura</strong> is the identifier of the invoice.</li>
 * <li><strong>fechaEmision</strong> is the date the invoice is drawn up.</li>
 * <li><strong>fechaVencimiento</strong> is the expiration date of an invoice.</li>
 * <li><strong>importe</strong> is the total amount of the invoice.</li>
 * <li><strong>estado</strong> points out whether the invoice has been paid or not.</li> * 
 * </ul>
 * @author Arantzazu Azkona
 */
@Entity
@Table(name="factura",schema="dindb")
@NamedQueries({
    @NamedQuery(
            name="findAllFacturas",
            query="SELECT f FROM Factura f ORDER BY f.numFactura"
    ),
    //BÚSQUEDAS PARAMETRIZADAS
    //buscar factura por nif cliente
    @NamedQuery(
            name="findClientsFacturas",
            query="SELECT f FROM Factura f WHERE f.cliente = :cliente"             
    ),
    //buscar facturas por estado
    @NamedQuery(
            name="findFacturasByEstado",
            query="SELECT f FROM Factura f WHERE f.estado= :estado"
    ),
    //buscar facturas cuya fecha de vencimiento sea mayor o igual que fecha
    @NamedQuery(
            name="findFacturasByFechaDesde",
            query="SELECT f FROM Factura f WHERE f.fechaVencimiento>:fecha"
    ),
    //buscar facturas cuya fecha de vencimiento sea menor o igual que fecha
    @NamedQuery(
            name="findFacturasByFechaHasta",
            query="SELECT f FROM Factura f WHERE f.fechaVencimiento<:fecha"
    ),
    //buscar facturas pagadas y por nif cliente
    @NamedQuery(
            name="findFacturasByNifEstado",
            query="SELECT f FROM Factura f WHERE f.cliente=:cliente AND f.estado= :estado"
    ),
    //buscar facturas pagadas, por nif cliente y con fecha de vencimiento mayor o igual que fecha
    @NamedQuery(
            name="findFacturasByNifEstadoFechaDesde",
            query="SELECT f FROM Factura f WHERE f.cliente=:cliente AND f.estado= :estado AND f.fechaVencimiento>:fecha"
    ),
    //buscar facturas con todos los párametros de búsqueda; pagadas, por nif cliente, cuya fecha de vencimiento esté entre dos fechas
    @NamedQuery(
            name="findFacturasByNifEstadoFechaDesdeFechaHasta",
            query="SELECT f FROM Factura f WHERE f.cliente=:cliente AND f.estado= :estado AND f.fechaVencimiento>=:fecha1 AND f.fechaVencimiento<=:fecha2 "
    ),
    //buscar facturas por cliente, cuya fecha de vencimiento esté entre dos fechas
   @NamedQuery(
            name="findFacturasByNifFechaDesdeFechaHasta",
            query="SELECT f FROM Factura f WHERE f.cliente=:cliente AND f.fechaVencimiento>=:fecha1 AND f.fechaVencimiento<=:fecha2 "
    ),
    //buscar facturas por cliente, cuya fecha de vencimiento sea mayor o igual que la fecha
    @NamedQuery(
            name="findFacturasByNifFechaDesde",
            query="SELECT f FROM Factura f WHERE f.cliente=:cliente AND f.fechaVencimiento>=:fecha "
    ),
    //buscar facturas por cliente, cuya fecha de vencimiento sea menor o igual que la fecha
    @NamedQuery(
            name="findFacturasByNifFechaHasta",
            query="SELECT f FROM Factura f WHERE f.cliente=:cliente f.fechaVencimiento<=:fecha "
    ),
    //buscar facturas pagadas cuyo vencimiento esté entre dos fechas
    @NamedQuery(
            name="findFacturasByEstadoFechaDesdeFechaHasta",
            query="SELECT f FROM Factura f WHERE f.estado= :estado AND f.fechaVencimiento>=:fecha1 AND f.fechaVencimiento<=:fecha2 "
    ),
    //buscar facturas cuyo vencimiento esté entre dos fechas
    @NamedQuery(
            name="findFacturasByFechaDesdeFechaHasta",
            query="SELECT f FROM Factura f WHERE f.fechaVencimiento>=:fecha1 AND f.fechaVencimiento<=:fecha2 "
    ),
    //buscar facturas pagadas cuyo vencimiento sea mayor o igual que la fecha
    @NamedQuery(
            name="findFacturasByEstadoFechaDesde",
            query="SELECT f FROM Factura f WHERE f.estado= :estado AND f.fechaVencimiento>=:fecha "
    ),
    //buscar facturas pagadas cuyo vencimiento sea menor o igual que la fecha
    @NamedQuery(
            name="findFacturasByEstadoFechaHasta",
            query="SELECT f FROM Factura f WHERE f.estado= :estado AND f.fechaVencimiento<=:fecha "
    ),
    //buscar facturas pagadas por cliente, cuyo vencimiento sea menor o igual que la fecha
    @NamedQuery(
            name="findFacturasByNifEstadoFechaHasta",
            query="SELECT f FROM Factura f WHERE f.cliente=:cliente AND f.estado= :estado AND f.fechaVencimiento<=:fecha "
    )
    
    
        
})
@XmlRootElement
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String numFactura;    
    private Date fechaEmision;
    private Date fechaVencimiento;
    private Float importe;
    private Boolean estado;
    @ManyToOne
    private Cliente cliente;

    
    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    /**
     * Method used to compare two objects, provides the hash code of an object.
     * @return the hash code
     */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numFactura != null ? numFactura.hashCode() : 0);
        return hash;
    }
    /**
     * Method used to make equal comparison between two objects.
     * @param object
     * @return a boolean variable
     */

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.numFactura == null && other.numFactura != null) || (this.numFactura != null && !this.numFactura.equals(other.numFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxServerSite.Entity.Factura[ id=" + numFactura + " ]";
    }
    
}
