/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControl;

import JPAControl.exceptions.NonexistentEntityException;
import JPAControl.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import restaurantetorteli.Pago;
import restaurantetorteli.Factura;
import restaurantetorteli.PagoFactura;

/**
 *
 * @author Alejandro
 */
public class PagoFacturaJpaController implements Serializable {

    public PagoFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PagoFactura pagoFactura) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago pagoIdPago = pagoFactura.getPagoIdPago();
            if (pagoIdPago != null) {
                pagoIdPago = em.getReference(pagoIdPago.getClass(), pagoIdPago.getIdPago());
                pagoFactura.setPagoIdPago(pagoIdPago);
            }
            Factura facturaFacturaid = pagoFactura.getFacturaFacturaid();
            if (facturaFacturaid != null) {
                facturaFacturaid = em.getReference(facturaFacturaid.getClass(), facturaFacturaid.getFacturaid());
                pagoFactura.setFacturaFacturaid(facturaFacturaid);
            }
            em.persist(pagoFactura);
            if (pagoIdPago != null) {
                pagoIdPago.getPagoFacturaCollection().add(pagoFactura);
                pagoIdPago = em.merge(pagoIdPago);
            }
            if (facturaFacturaid != null) {
                facturaFacturaid.getPagoFacturaCollection().add(pagoFactura);
                facturaFacturaid = em.merge(facturaFacturaid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPagoFactura(pagoFactura.getId()) != null) {
                throw new PreexistingEntityException("PagoFactura " + pagoFactura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PagoFactura pagoFactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagoFactura persistentPagoFactura = em.find(PagoFactura.class, pagoFactura.getId());
            Pago pagoIdPagoOld = persistentPagoFactura.getPagoIdPago();
            Pago pagoIdPagoNew = pagoFactura.getPagoIdPago();
            Factura facturaFacturaidOld = persistentPagoFactura.getFacturaFacturaid();
            Factura facturaFacturaidNew = pagoFactura.getFacturaFacturaid();
            if (pagoIdPagoNew != null) {
                pagoIdPagoNew = em.getReference(pagoIdPagoNew.getClass(), pagoIdPagoNew.getIdPago());
                pagoFactura.setPagoIdPago(pagoIdPagoNew);
            }
            if (facturaFacturaidNew != null) {
                facturaFacturaidNew = em.getReference(facturaFacturaidNew.getClass(), facturaFacturaidNew.getFacturaid());
                pagoFactura.setFacturaFacturaid(facturaFacturaidNew);
            }
            pagoFactura = em.merge(pagoFactura);
            if (pagoIdPagoOld != null && !pagoIdPagoOld.equals(pagoIdPagoNew)) {
                pagoIdPagoOld.getPagoFacturaCollection().remove(pagoFactura);
                pagoIdPagoOld = em.merge(pagoIdPagoOld);
            }
            if (pagoIdPagoNew != null && !pagoIdPagoNew.equals(pagoIdPagoOld)) {
                pagoIdPagoNew.getPagoFacturaCollection().add(pagoFactura);
                pagoIdPagoNew = em.merge(pagoIdPagoNew);
            }
            if (facturaFacturaidOld != null && !facturaFacturaidOld.equals(facturaFacturaidNew)) {
                facturaFacturaidOld.getPagoFacturaCollection().remove(pagoFactura);
                facturaFacturaidOld = em.merge(facturaFacturaidOld);
            }
            if (facturaFacturaidNew != null && !facturaFacturaidNew.equals(facturaFacturaidOld)) {
                facturaFacturaidNew.getPagoFacturaCollection().add(pagoFactura);
                facturaFacturaidNew = em.merge(facturaFacturaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagoFactura.getId();
                if (findPagoFactura(id) == null) {
                    throw new NonexistentEntityException("The pagoFactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagoFactura pagoFactura;
            try {
                pagoFactura = em.getReference(PagoFactura.class, id);
                pagoFactura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoFactura with id " + id + " no longer exists.", enfe);
            }
            Pago pagoIdPago = pagoFactura.getPagoIdPago();
            if (pagoIdPago != null) {
                pagoIdPago.getPagoFacturaCollection().remove(pagoFactura);
                pagoIdPago = em.merge(pagoIdPago);
            }
            Factura facturaFacturaid = pagoFactura.getFacturaFacturaid();
            if (facturaFacturaid != null) {
                facturaFacturaid.getPagoFacturaCollection().remove(pagoFactura);
                facturaFacturaid = em.merge(facturaFacturaid);
            }
            em.remove(pagoFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PagoFactura> findPagoFacturaEntities() {
        return findPagoFacturaEntities(true, -1, -1);
    }

    public List<PagoFactura> findPagoFacturaEntities(int maxResults, int firstResult) {
        return findPagoFacturaEntities(false, maxResults, firstResult);
    }

    private List<PagoFactura> findPagoFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PagoFactura.class));
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

    public PagoFactura findPagoFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PagoFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PagoFactura> rt = cq.from(PagoFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
