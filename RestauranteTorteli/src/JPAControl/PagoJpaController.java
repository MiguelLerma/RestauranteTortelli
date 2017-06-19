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
import restaurantetorteli.PagoFactura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import restaurantetorteli.Pago;

/**
 *
 * @author Alejandro
 */
public class PagoJpaController implements Serializable {

    public PagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pago pago) throws PreexistingEntityException, Exception {
        if (pago.getPagoFacturaCollection() == null) {
            pago.setPagoFacturaCollection(new ArrayList<PagoFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PagoFactura> attachedPagoFacturaCollection = new ArrayList<PagoFactura>();
            for (PagoFactura pagoFacturaCollectionPagoFacturaToAttach : pago.getPagoFacturaCollection()) {
                pagoFacturaCollectionPagoFacturaToAttach = em.getReference(pagoFacturaCollectionPagoFacturaToAttach.getClass(), pagoFacturaCollectionPagoFacturaToAttach.getId());
                attachedPagoFacturaCollection.add(pagoFacturaCollectionPagoFacturaToAttach);
            }
            pago.setPagoFacturaCollection(attachedPagoFacturaCollection);
            em.persist(pago);
            for (PagoFactura pagoFacturaCollectionPagoFactura : pago.getPagoFacturaCollection()) {
                Pago oldPagoIdPagoOfPagoFacturaCollectionPagoFactura = pagoFacturaCollectionPagoFactura.getPagoIdPago();
                pagoFacturaCollectionPagoFactura.setPagoIdPago(pago);
                pagoFacturaCollectionPagoFactura = em.merge(pagoFacturaCollectionPagoFactura);
                if (oldPagoIdPagoOfPagoFacturaCollectionPagoFactura != null) {
                    oldPagoIdPagoOfPagoFacturaCollectionPagoFactura.getPagoFacturaCollection().remove(pagoFacturaCollectionPagoFactura);
                    oldPagoIdPagoOfPagoFacturaCollectionPagoFactura = em.merge(oldPagoIdPagoOfPagoFacturaCollectionPagoFactura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPago(pago.getIdPago()) != null) {
                throw new PreexistingEntityException("Pago " + pago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pago pago) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getIdPago());
            Collection<PagoFactura> pagoFacturaCollectionOld = persistentPago.getPagoFacturaCollection();
            Collection<PagoFactura> pagoFacturaCollectionNew = pago.getPagoFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (PagoFactura pagoFacturaCollectionOldPagoFactura : pagoFacturaCollectionOld) {
                if (!pagoFacturaCollectionNew.contains(pagoFacturaCollectionOldPagoFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PagoFactura " + pagoFacturaCollectionOldPagoFactura + " since its pagoIdPago field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<PagoFactura> attachedPagoFacturaCollectionNew = new ArrayList<PagoFactura>();
            for (PagoFactura pagoFacturaCollectionNewPagoFacturaToAttach : pagoFacturaCollectionNew) {
                pagoFacturaCollectionNewPagoFacturaToAttach = em.getReference(pagoFacturaCollectionNewPagoFacturaToAttach.getClass(), pagoFacturaCollectionNewPagoFacturaToAttach.getId());
                attachedPagoFacturaCollectionNew.add(pagoFacturaCollectionNewPagoFacturaToAttach);
            }
            pagoFacturaCollectionNew = attachedPagoFacturaCollectionNew;
            pago.setPagoFacturaCollection(pagoFacturaCollectionNew);
            pago = em.merge(pago);
            for (PagoFactura pagoFacturaCollectionNewPagoFactura : pagoFacturaCollectionNew) {
                if (!pagoFacturaCollectionOld.contains(pagoFacturaCollectionNewPagoFactura)) {
                    Pago oldPagoIdPagoOfPagoFacturaCollectionNewPagoFactura = pagoFacturaCollectionNewPagoFactura.getPagoIdPago();
                    pagoFacturaCollectionNewPagoFactura.setPagoIdPago(pago);
                    pagoFacturaCollectionNewPagoFactura = em.merge(pagoFacturaCollectionNewPagoFactura);
                    if (oldPagoIdPagoOfPagoFacturaCollectionNewPagoFactura != null && !oldPagoIdPagoOfPagoFacturaCollectionNewPagoFactura.equals(pago)) {
                        oldPagoIdPagoOfPagoFacturaCollectionNewPagoFactura.getPagoFacturaCollection().remove(pagoFacturaCollectionNewPagoFactura);
                        oldPagoIdPagoOfPagoFacturaCollectionNewPagoFactura = em.merge(oldPagoIdPagoOfPagoFacturaCollectionNewPagoFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pago.getIdPago();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
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
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getIdPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PagoFactura> pagoFacturaCollectionOrphanCheck = pago.getPagoFacturaCollection();
            for (PagoFactura pagoFacturaCollectionOrphanCheckPagoFactura : pagoFacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pago (" + pago + ") cannot be destroyed since the PagoFactura " + pagoFacturaCollectionOrphanCheckPagoFactura + " in its pagoFacturaCollection field has a non-nullable pagoIdPago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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

    public Pago findPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
