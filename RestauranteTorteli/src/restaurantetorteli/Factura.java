/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantetorteli;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByHorapago", query = "SELECT f FROM Factura f WHERE f.horapago = :horapago"),
    @NamedQuery(name = "Factura.findByFacturaid", query = "SELECT f FROM Factura f WHERE f.facturaid = :facturaid"),
    @NamedQuery(name = "Factura.findByEstadofactura", query = "SELECT f FROM Factura f WHERE f.estadofactura = :estadofactura"),
    @NamedQuery(name = "Factura.findByFechafactura", query = "SELECT f FROM Factura f WHERE f.fechafactura = :fechafactura"),
    @NamedQuery(name = "Factura.findByTotalfactura", query = "SELECT f FROM Factura f WHERE f.totalfactura = :totalfactura"),
    @NamedQuery(name = "Factura.findByCedulacliente", query = "SELECT f FROM Factura f WHERE f.cedulacliente = :cedulacliente"),
    @NamedQuery(name = "Factura.findByIva", query = "SELECT f FROM Factura f WHERE f.iva = :iva")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "horapago")
    @Temporal(TemporalType.TIME)
    private Date horapago;
    @Id
    @Basic(optional = false)
    @Column(name = "facturaid")
    private Integer facturaid;
    @Basic(optional = false)
    @Column(name = "estadofactura")
    private String estadofactura;
    @Basic(optional = false)
    @Column(name = "fechafactura")
    @Temporal(TemporalType.DATE)
    private Date fechafactura;
    @Basic(optional = false)
    @Column(name = "totalfactura")
    private int totalfactura;
    @Basic(optional = false)
    @Column(name = "cedulacliente")
    private int cedulacliente;
    @Basic(optional = false)
    @Column(name = "iva")
    private int iva;
    @JoinColumn(name = "pedido_idpedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedido pedidoIdpedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private Collection<ProductosFactura> productosFacturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaFacturaid")
    private Collection<PagoFactura> pagoFacturaCollection;

    public Factura() {
    }

    public Factura(Integer facturaid) {
        this.facturaid = facturaid;
    }

    public Factura(Integer facturaid, Date horapago, String estadofactura, Date fechafactura, int totalfactura, int cedulacliente, int iva) {
        this.facturaid = facturaid;
        this.horapago = horapago;
        this.estadofactura = estadofactura;
        this.fechafactura = fechafactura;
        this.totalfactura = totalfactura;
        this.cedulacliente = cedulacliente;
        this.iva = iva;
    }

    public Date getHorapago() {
        return horapago;
    }

    public void setHorapago(Date horapago) {
        this.horapago = horapago;
    }

    public Integer getFacturaid() {
        return facturaid;
    }

    public void setFacturaid(Integer facturaid) {
        this.facturaid = facturaid;
    }

    public String getEstadofactura() {
        return estadofactura;
    }

    public void setEstadofactura(String estadofactura) {
        this.estadofactura = estadofactura;
    }

    public Date getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(Date fechafactura) {
        this.fechafactura = fechafactura;
    }

    public int getTotalfactura() {
        return totalfactura;
    }

    public void setTotalfactura(int totalfactura) {
        this.totalfactura = totalfactura;
    }

    public int getCedulacliente() {
        return cedulacliente;
    }

    public void setCedulacliente(int cedulacliente) {
        this.cedulacliente = cedulacliente;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public Pedido getPedidoIdpedido() {
        return pedidoIdpedido;
    }

    public void setPedidoIdpedido(Pedido pedidoIdpedido) {
        this.pedidoIdpedido = pedidoIdpedido;
    }

    @XmlTransient
    public Collection<ProductosFactura> getProductosFacturaCollection() {
        return productosFacturaCollection;
    }

    public void setProductosFacturaCollection(Collection<ProductosFactura> productosFacturaCollection) {
        this.productosFacturaCollection = productosFacturaCollection;
    }

    @XmlTransient
    public Collection<PagoFactura> getPagoFacturaCollection() {
        return pagoFacturaCollection;
    }

    public void setPagoFacturaCollection(Collection<PagoFactura> pagoFacturaCollection) {
        this.pagoFacturaCollection = pagoFacturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaid != null ? facturaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.facturaid == null && other.facturaid != null) || (this.facturaid != null && !this.facturaid.equals(other.facturaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurantetorteli.Factura[ facturaid=" + facturaid + " ]";
    }
    
}
