/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantetorteli;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "productos_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosFactura.findAll", query = "SELECT p FROM ProductosFactura p")
    , @NamedQuery(name = "ProductosFactura.findByNombreProducto", query = "SELECT p FROM ProductosFactura p WHERE p.nombreProducto = :nombreProducto")
    , @NamedQuery(name = "ProductosFactura.findByIdProducto", query = "SELECT p FROM ProductosFactura p WHERE p.productosFacturaPK.idProducto = :idProducto")
    , @NamedQuery(name = "ProductosFactura.findByPrecioProducto", query = "SELECT p FROM ProductosFactura p WHERE p.precioProducto = :precioProducto")
    , @NamedQuery(name = "ProductosFactura.findByFacturaFacturaid", query = "SELECT p FROM ProductosFactura p WHERE p.productosFacturaPK.facturaFacturaid = :facturaFacturaid")})
public class ProductosFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductosFacturaPK productosFacturaPK;
    @Basic(optional = false)
    @Column(name = "nombre_producto")
    private String nombreProducto;
    @Basic(optional = false)
    @Column(name = "precio_producto")
    private int precioProducto;
    @JoinColumn(name = "factura_facturaid", referencedColumnName = "facturaid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;

    public ProductosFactura() {
    }

    public ProductosFactura(ProductosFacturaPK productosFacturaPK) {
        this.productosFacturaPK = productosFacturaPK;
    }

    public ProductosFactura(ProductosFacturaPK productosFacturaPK, String nombreProducto, int precioProducto) {
        this.productosFacturaPK = productosFacturaPK;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }

    public ProductosFactura(int idProducto, int facturaFacturaid) {
        this.productosFacturaPK = new ProductosFacturaPK(idProducto, facturaFacturaid);
    }

    public ProductosFacturaPK getProductosFacturaPK() {
        return productosFacturaPK;
    }

    public void setProductosFacturaPK(ProductosFacturaPK productosFacturaPK) {
        this.productosFacturaPK = productosFacturaPK;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productosFacturaPK != null ? productosFacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosFactura)) {
            return false;
        }
        ProductosFactura other = (ProductosFactura) object;
        if ((this.productosFacturaPK == null && other.productosFacturaPK != null) || (this.productosFacturaPK != null && !this.productosFacturaPK.equals(other.productosFacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurantetorteli.ProductosFactura[ productosFacturaPK=" + productosFacturaPK + " ]";
    }
    
}
