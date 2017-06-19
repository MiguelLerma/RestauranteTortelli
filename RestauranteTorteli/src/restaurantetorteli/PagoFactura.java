/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantetorteli;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "pago_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoFactura.findAll", query = "SELECT p FROM PagoFactura p"),
    @NamedQuery(name = "PagoFactura.findByValorPago", query = "SELECT p FROM PagoFactura p WHERE p.valorPago = :valorPago"),
    @NamedQuery(name = "PagoFactura.findById", query = "SELECT p FROM PagoFactura p WHERE p.id = :id")})
public class PagoFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "valor_pago")
    private int valorPago;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "pago_id_pago", referencedColumnName = "id_pago")
    @ManyToOne(optional = false)
    private Pago pagoIdPago;
    @JoinColumn(name = "factura_facturaid", referencedColumnName = "facturaid")
    @ManyToOne(optional = false)
    private Factura facturaFacturaid;

    public PagoFactura() {
    }

    public PagoFactura(Integer id) {
        this.id = id;
    }

    public PagoFactura(Integer id, int valorPago) {
        this.id = id;
        this.valorPago = valorPago;
    }

    public int getValorPago() {
        return valorPago;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pago getPagoIdPago() {
        return pagoIdPago;
    }

    public void setPagoIdPago(Pago pagoIdPago) {
        this.pagoIdPago = pagoIdPago;
    }

    public Factura getFacturaFacturaid() {
        return facturaFacturaid;
    }

    public void setFacturaFacturaid(Factura facturaFacturaid) {
        this.facturaFacturaid = facturaFacturaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoFactura)) {
            return false;
        }
        PagoFactura other = (PagoFactura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurantetorteli.PagoFactura[ id=" + id + " ]";
    }
    
}
