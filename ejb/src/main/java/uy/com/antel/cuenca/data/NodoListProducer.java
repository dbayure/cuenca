

package uy.com.antel.cuenca.data;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.antel.cuenca.model.Nodo;


@RequestScoped
public class NodoListProducer {
	
   @Inject
   private EntityManager em;

   private List<Nodo> nodos;


   @Produces
   @Named
   public List<Nodo> getnodos() {
      return nodos;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Nodo nodo) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Nodo> criteria = cb.createQuery(Nodo.class);
      Root<Nodo> nodo = criteria.from(Nodo.class);
      criteria.select(nodo).orderBy(cb.asc(nodo.get("nodo")));
      nodos = em.createQuery(criteria).getResultList();
   }
}
