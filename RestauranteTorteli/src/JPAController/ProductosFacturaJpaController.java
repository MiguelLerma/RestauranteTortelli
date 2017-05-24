/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAController;

import JPAController.exceptions.NonexistentEntityException;
import JPAController.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import restaurantetorteli.Factura;
import restaurantetorteli.ProductosFactura;
import restaurantetorteli.ProductosFacturaPK;

/**
 *
 * @author User
 */
public class ProductosFacturaJpaController implements Serializable {

    public ProductosFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductosFactura productosFactura) throws PreexistingEntityException, Exception {
        if (productosFactura.getProductosFacturaPK() == null) {
            productosFactura.setProductosFacturaPK(new ProductosFacturaPK());
        }
        productosFactura.getProductosFacturaPK().setFacturaFacturaid(productosFactura.getFactura().getFacturaid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura = productosFactura.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getFacturaid());
                productosFactura.setFactura(factura);
            }
            em.persist(productosFactura);
            if (factura != null) {
                factura.getProductosFacturaCollection().add(productosFactura);
                factura = em.merge(factura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductosFactura(productosFactura.getProductosFacturaPK()) != null) {
                throw new PreexistingEntityException("ProductosFactura " + productosFactura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductosFactura productosFactura) throws NonexistentEntityException, Exception {
        productosFactura.getProductosFacturaPK().setFacturaFacturaid(productosFactura.getFactura().getFacturaid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductosFactura persistentProductosFactura = em.find(ProductosFactura.class, productosFactura.getProductosFacturaPK());
            Factura facturaOld = persistentProductosFactura.getFactura();
            Factura facturaNew = productosFactura.getFactura();
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getFacturaid());
                productosFactura.setFactura(facturaNew);
            }
            productosFactura = em.merge(productosFactura);
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getProductosFacturaCollection().remove(productosFactura);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getProductosFacturaCollection().add(productosFactura);
                facturaNew = em.merge(facturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductosFacturaPK id = productosFactura.getProductosFacturaPK();
                if (findProductosFactura(id) == null) {
                    throw new NonexistentEntityException("The productosFactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductosFacturaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductosFactura productosFactura;
            try {
                productosFactura = em.getReference(ProductosFactura.class, id);
                productosFactura.getProductosFacturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productosFactura with id " + id + " no longer exists.", enfe);
            }
            Factura factura = productosFactura.getFactura();
            if (factura != null) {
                factura.getProductosFacturaCollection().remove(productosFactura);
                factura = em.merge(factura);
            }
            em.remove(productosFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductosFactura> findProductosFacturaEntities() {
        return findProductosFacturaEntities(true, -1, -1);
    }

    public List<ProductosFactura> findProductosFacturaEntities(int maxResults, int firstResult) {
        return findProductosFacturaEntities(false, maxResults, firstResult);
    }

    private List<ProductosFactura> findProductosFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductosFactura.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProductosFactura findProductosFactura(ProductosFacturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductosFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductosFactura> rt = cq.from(ProductosFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
