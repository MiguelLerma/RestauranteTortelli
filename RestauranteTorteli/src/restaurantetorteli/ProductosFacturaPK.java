/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantetorteli;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author User
 */
@Embeddable
public class ProductosFacturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_producto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "factura_facturaid")
    private int facturaFacturaid;

    public ProductosFacturaPK() {
    }

    public ProductosFacturaPK(int idProducto, int facturaFacturaid) {
        this.idProducto = idProducto;
        this.facturaFacturaid = facturaFacturaid;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getFacturaFacturaid() {
        return facturaFacturaid;
    }

    public void setFacturaFacturaid(int facturaFacturaid) {
        this.facturaFacturaid = facturaFacturaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (int) facturaFacturaid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosFacturaPK)) {
            return false;
        }
        ProductosFacturaPK other = (ProductosFacturaPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.facturaFacturaid != other.facturaFacturaid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurantetorteli.ProductosFacturaPK[ idProducto=" + idProducto + ", facturaFacturaid=" + facturaFacturaid + " ]";
    }
    
}
