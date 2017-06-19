/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControl;

import JPAControl.exceptions.IllegalOrphanException;
import JPAControl.exceptions.NonexistentEntityException;
import JPAControl.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import restaurantetorteli.Pedido;
import restaurantetorteli.ProductosFactura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restaurantetorteli.Factura;
import restaurantetorteli.PagoFactura;

/**
 *
 * @author Alejandro
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
        if (factura.getProductosFacturaCollection() == null) {
            factura.setProductosFacturaCollection(new ArrayList<ProductosFactura>());
        }
        if (factura.getPagoFacturaCollection() == null) {
            factura.setPagoFacturaCollection(new ArrayList<PagoFactura>());
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
            Collection<ProductosFactura> attachedProductosFacturaCollection = new ArrayList<ProductosFactura>();
            for (ProductosFactura productosFacturaCollectionProductosFacturaToAttach : factura.getProductosFacturaCollection()) {
                productosFacturaCollectionProductosFacturaToAttach = em.getReference(productosFacturaCollectionProductosFacturaToAttach.getClass(), productosFacturaCollectionProductosFacturaToAttach.getProductosFacturaPK());
                attachedProductosFacturaCollection.add(productosFacturaCollectionProductosFacturaToAttach);
            }
            factura.setProductosFacturaCollection(attachedProductosFacturaCollection);
            Collection<PagoFactura> attachedPagoFacturaCollection = new ArrayList<PagoFactura>();
            for (PagoFactura pagoFacturaCollectionPagoFacturaToAttach : factura.getPagoFacturaCollection()) {
                pagoFacturaCollectionPagoFacturaToAttach = em.getReference(pagoFacturaCollectionPagoFacturaToAttach.getClass(), pagoFacturaCollectionPagoFacturaToAttach.getId());
                attachedPagoFacturaCollection.add(pagoFacturaCollectionPagoFacturaToAttach);
            }
            factura.setPagoFacturaCollection(attachedPagoFacturaCollection);
            em.persist(factura);
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getFacturaCollection().add(factura);
                pedidoIdpedido = em.merge(pedidoIdpedido);
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
            for (PagoFactura pagoFacturaCollectionPagoFactura : factura.getPagoFacturaCollection()) {
                Factura oldFacturaFacturaidOfPagoFacturaCollectionPagoFactura = pagoFacturaCollectionPagoFactura.getFacturaFacturaid();
                pagoFacturaCollectionPagoFactura.setFacturaFacturaid(factura);
                pagoFacturaCollectionPagoFactura = em.merge(pagoFacturaCollectionPagoFactura);
                if (oldFacturaFacturaidOfPagoFacturaCollectionPagoFactura != null) {
                    oldFacturaFacturaidOfPagoFacturaCollectionPagoFactura.getPagoFacturaCollection().remove(pagoFacturaCollectionPagoFactura);
                    oldFacturaFacturaidOfPagoFacturaCollectionPagoFactura = em.merge(oldFacturaFacturaidOfPagoFacturaCollectionPagoFactura);
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
            Collection<ProductosFactura> productosFacturaCollectionOld = persistentFactura.getProductosFacturaCollection();
            Collection<ProductosFactura> productosFacturaCollectionNew = factura.getProductosFacturaCollection();
            Collection<PagoFactura> pagoFacturaCollectionOld = persistentFactura.getPagoFacturaCollection();
            Collection<PagoFactura> pagoFacturaCollectionNew = factura.getPagoFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (ProductosFactura productosFacturaCollectionOldProductosFactura : productosFacturaCollectionOld) {
                if (!productosFacturaCollectionNew.contains(productosFacturaCollectionOldProductosFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosFactura " + productosFacturaCollectionOldProductosFactura + " since its factura field is not nullable.");
                }
            }
            for (PagoFactura pagoFacturaCollectionOldPagoFactura : pagoFacturaCollectionOld) {
                if (!pagoFacturaCollectionNew.contains(pagoFacturaCollectionOldPagoFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PagoFactura " + pagoFacturaCollectionOldPagoFactura + " since its facturaFacturaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pedidoIdpedidoNew != null) {
                pedidoIdpedidoNew = em.getReference(pedidoIdpedidoNew.getClass(), pedidoIdpedidoNew.getIdpedido());
                factura.setPedidoIdpedido(pedidoIdpedidoNew);
            }
            Collection<ProductosFactura> attachedProductosFacturaCollectionNew = new ArrayList<ProductosFactura>();
            for (ProductosFactura productosFacturaCollectionNewProductosFacturaToAttach : productosFacturaCollectionNew) {
                productosFacturaCollectionNewProductosFacturaToAttach = em.getReference(productosFacturaCollectionNewProductosFacturaToAttach.getClass(), productosFacturaCollectionNewProductosFacturaToAttach.getProductosFacturaPK());
                attachedProductosFacturaCollectionNew.add(productosFacturaCollectionNewProductosFacturaToAttach);
            }
            productosFacturaCollectionNew = attachedProductosFacturaCollectionNew;
            factura.setProductosFacturaCollection(productosFacturaCollectionNew);
            Collection<PagoFactura> attachedPagoFacturaCollectionNew = new ArrayList<PagoFactura>();
            for (PagoFactura pagoFacturaCollectionNewPagoFacturaToAttach : pagoFacturaCollectionNew) {
                pagoFacturaCollectionNewPagoFacturaToAttach = em.getReference(pagoFacturaCollectionNewPagoFacturaToAttach.getClass(), pagoFacturaCollectionNewPagoFacturaToAttach.getId());
                attachedPagoFacturaCollectionNew.add(pagoFacturaCollectionNewPagoFacturaToAttach);
            }
            pagoFacturaCollectionNew = attachedPagoFacturaCollectionNew;
            factura.setPagoFacturaCollection(pagoFacturaCollectionNew);
            factura = em.merge(factura);
            if (pedidoIdpedidoOld != null && !pedidoIdpedidoOld.equals(pedidoIdpedidoNew)) {
                pedidoIdpedidoOld.getFacturaCollection().remove(factura);
                pedidoIdpedidoOld = em.merge(pedidoIdpedidoOld);
            }
            if (pedidoIdpedidoNew != null && !pedidoIdpedidoNew.equals(pedidoIdpedidoOld)) {
                pedidoIdpedidoNew.getFacturaCollection().add(factura);
                pedidoIdpedidoNew = em.merge(pedidoIdpedidoNew);
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
            for (PagoFactura pagoFacturaCollectionNewPagoFactura : pagoFacturaCollectionNew) {
                if (!pagoFacturaCollectionOld.contains(pagoFacturaCollectionNewPagoFactura)) {
                    Factura oldFacturaFacturaidOfPagoFacturaCollectionNewPagoFactura = pagoFacturaCollectionNewPagoFactura.getFacturaFacturaid();
                    pagoFacturaCollectionNewPagoFactura.setFacturaFacturaid(factura);
                    pagoFacturaCollectionNewPagoFactura = em.merge(pagoFacturaCollectionNewPagoFactura);
                    if (oldFacturaFacturaidOfPagoFacturaCollectionNewPagoFactura != null && !oldFacturaFacturaidOfPagoFacturaCollectionNewPagoFactura.equals(factura)) {
                        oldFacturaFacturaidOfPagoFacturaCollectionNewPagoFactura.getPagoFacturaCollection().remove(pagoFacturaCollectionNewPagoFactura);
                        oldFacturaFacturaidOfPagoFacturaCollectionNewPagoFactura = em.merge(oldFacturaFacturaidOfPagoFacturaCollectionNewPagoFactura);
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
            Collection<PagoFactura> pagoFacturaCollectionOrphanCheck = factura.getPagoFacturaCollection();
            for (PagoFactura pagoFacturaCollectionOrphanCheckPagoFactura : pagoFacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the PagoFactura " + pagoFacturaCollectionOrphanCheckPagoFactura + " in its pagoFacturaCollection field has a non-nullable facturaFacturaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedidoIdpedido = factura.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getFacturaCollection().remove(factura);
                pedidoIdpedido = em.merge(pedidoIdpedido);
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
