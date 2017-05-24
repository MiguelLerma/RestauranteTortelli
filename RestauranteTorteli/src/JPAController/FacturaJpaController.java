/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAController;

import JPAController.exceptions.IllegalOrphanException;
import JPAController.exceptions.NonexistentEntityException;
import JPAController.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import restaurantetorteli.Pedido;
import restaurantetorteli.Pago;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restaurantetorteli.Factura;
import restaurantetorteli.ProductosFactura;

/**
 *
 * @author User
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) throws PreexistingEntityException, Exception {
        if (factura.getPagoCollection() == null) {
            factura.setPagoCollection(new ArrayList<Pago>());
        }
        if (factura.getProductosFacturaCollection() == null) {
            factura.setProductosFacturaCollection(new ArrayList<ProductosFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedidoIdpedido = factura.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido = em.getReference(pedidoIdpedido.getClass(), pedidoIdpedido.getIdpedido());
                factura.setPedidoIdpedido(pedidoIdpedido);
            }
            Collection<Pago> attachedPagoCollection = new ArrayList<Pago>();
            for (Pago pagoCollectionPagoToAttach : factura.getPagoCollection()) {
                pagoCollectionPagoToAttach = em.getReference(pagoCollectionPagoToAttach.getClass(), pagoCollectionPagoToAttach.getValorPago());
                attachedPagoCollection.add(pagoCollectionPagoToAttach);
            }
            factura.setPagoCollection(attachedPagoCollection);
            Collection<ProductosFactura> attachedProductosFacturaCollection = new ArrayList<ProductosFactura>();
            for (ProductosFactura productosFacturaCollectionProductosFacturaToAttach : factura.getProductosFacturaCollection()) {
                productosFacturaCollectionProductosFacturaToAttach = em.getReference(productosFacturaCollectionProductosFacturaToAttach.getClass(), productosFacturaCollectionProductosFacturaToAttach.getProductosFacturaPK());
                attachedProductosFacturaCollection.add(productosFacturaCollectionProductosFacturaToAttach);
            }
            factura.setProductosFacturaCollection(attachedProductosFacturaCollection);
            em.persist(factura);
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getFacturaCollection().add(factura);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            for (Pago pagoCollectionPago : factura.getPagoCollection()) {
                pagoCollectionPago.getFacturaCollection().add(factura);
                pagoCollectionPago = em.merge(pagoCollectionPago);
            }
            for (ProductosFactura productosFacturaCollectionProductosFactura : factura.getProductosFacturaCollection()) {
                Factura oldFacturaOfProductosFacturaCollectionProductosFactura = productosFacturaCollectionProductosFactura.getFactura();
                productosFacturaCollectionProductosFactura.setFactura(factura);
                productosFacturaCollectionProductosFactura = em.merge(productosFacturaCollectionProductosFactura);
                if (oldFacturaOfProductosFacturaCollectionProductosFactura != null) {
                    oldFacturaOfProductosFacturaCollectionProductosFactura.getProductosFacturaCollection().remove(productosFacturaCollectionProductosFactura);
                    oldFacturaOfProductosFacturaCollectionProductosFactura = em.merge(oldFacturaOfProductosFacturaCollectionProductosFactura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFactura(factura.getFacturaid()) != null) {
                throw new PreexistingEntityException("Factura " + factura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getFacturaid());
            Pedido pedidoIdpedidoOld = persistentFactura.getPedidoIdpedido();
            Pedido pedidoIdpedidoNew = factura.getPedidoIdpedido();
            Collection<Pago> pagoCollectionOld = persistentFactura.getPagoCollection();
            Collection<Pago> pagoCollectionNew = factura.getPagoCollection();
            Collection<ProductosFactura> productosFacturaCollectionOld = persistentFactura.getProductosFacturaCollection();
            Collection<ProductosFactura> productosFacturaCollectionNew = factura.getProductosFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (ProductosFactura productosFacturaCollectionOldProductosFactura : productosFacturaCollectionOld) {
                if (!productosFacturaCollectionNew.contains(productosFacturaCollectionOldProductosFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosFactura " + productosFacturaCollectionOldProductosFactura + " since its factura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pedidoIdpedidoNew != null) {
                pedidoIdpedidoNew = em.getReference(pedidoIdpedidoNew.getClass(), pedidoIdpedidoNew.getIdpedido());
                factura.setPedidoIdpedido(pedidoIdpedidoNew);
            }
            Collection<Pago> attachedPagoCollectionNew = new ArrayList<Pago>();
            for (Pago pagoCollectionNewPagoToAttach : pagoCollectionNew) {
                pagoCollectionNewPagoToAttach = em.getReference(pagoCollectionNewPagoToAttach.getClass(), pagoCollectionNewPagoToAttach.getValorPago());
                attachedPagoCollectionNew.add(pagoCollectionNewPagoToAttach);
            }
            pagoCollectionNew = attachedPagoCollectionNew;
            factura.setPagoCollection(pagoCollectionNew);
            Collection<ProductosFactura> attachedProductosFacturaCollectionNew = new ArrayList<ProductosFactura>();
            for (ProductosFactura productosFacturaCollectionNewProductosFacturaToAttach : productosFacturaCollectionNew) {
                productosFacturaCollectionNewProductosFacturaToAttach = em.getReference(productosFacturaCollectionNewProductosFacturaToAttach.getClass(), productosFacturaCollectionNewProductosFacturaToAttach.getProductosFacturaPK());
                attachedProductosFacturaCollectionNew.add(productosFacturaCollectionNewProductosFacturaToAttach);
            }
            productosFacturaCollectionNew = attachedProductosFacturaCollectionNew;
            factura.setProductosFacturaCollection(productosFacturaCollectionNew);
            factura = em.merge(factura);
            if (pedidoIdpedidoOld != null && !pedidoIdpedidoOld.equals(pedidoIdpedidoNew)) {
                pedidoIdpedidoOld.getFacturaCollection().remove(factura);
                pedidoIdpedidoOld = em.merge(pedidoIdpedidoOld);
            }
            if (pedidoIdpedidoNew != null && !pedidoIdpedidoNew.equals(pedidoIdpedidoOld)) {
                pedidoIdpedidoNew.getFacturaCollection().add(factura);
                pedidoIdpedidoNew = em.merge(pedidoIdpedidoNew);
            }
            for (Pago pagoCollectionOldPago : pagoCollectionOld) {
                if (!pagoCollectionNew.contains(pagoCollectionOldPago)) {
                    pagoCollectionOldPago.getFacturaCollection().remove(factura);
                    pagoCollectionOldPago = em.merge(pagoCollectionOldPago);
                }
            }
            for (Pago pagoCollectionNewPago : pagoCollectionNew) {
                if (!pagoCollectionOld.contains(pagoCollectionNewPago)) {
                    pagoCollectionNewPago.getFacturaCollection().add(factura);
                    pagoCollectionNewPago = em.merge(pagoCollectionNewPago);
                }
            }
            for (ProductosFactura productosFacturaCollectionNewProductosFactura : productosFacturaCollectionNew) {
                if (!productosFacturaCollectionOld.contains(productosFacturaCollectionNewProductosFactura)) {
                    Factura oldFacturaOfProductosFacturaCollectionNewProductosFactura = productosFacturaCollectionNewProductosFactura.getFactura();
                    productosFacturaCollectionNewProductosFactura.setFactura(factura);
                    productosFacturaCollectionNewProductosFactura = em.merge(productosFacturaCollectionNewProductosFactura);
                    if (oldFacturaOfProductosFacturaCollectionNewProductosFactura != null && !oldFacturaOfProductosFacturaCollectionNewProductosFactura.equals(factura)) {
                        oldFacturaOfProductosFacturaCollectionNewProductosFactura.getProductosFacturaCollection().remove(productosFacturaCollectionNewProductosFactura);
                        oldFacturaOfProductosFacturaCollectionNewProductosFactura = em.merge(oldFacturaOfProductosFacturaCollectionNewProductosFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getFacturaid();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getFacturaid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ProductosFactura> productosFacturaCollectionOrphanCheck = factura.getProductosFacturaCollection();
            for (ProductosFactura productosFacturaCollectionOrphanCheckProductosFactura : productosFacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the ProductosFactura " + productosFacturaCollectionOrphanCheckProductosFactura + " in its productosFacturaCollection field has a non-nullable factura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedidoIdpedido = factura.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getFacturaCollection().remove(factura);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            Collection<Pago> pagoCollection = factura.getPagoCollection();
            for (Pago pagoCollectionPago : pagoCollection) {
                pagoCollectionPago.getFacturaCollection().remove(factura);
                pagoCollectionPago = em.merge(pagoCollectionPago);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
