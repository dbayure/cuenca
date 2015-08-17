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
import uy.com.antel.cuenca.model.Temperatura;



@RequestScoped
public class TemperaturaListProducer {
	
   @Inject
   private EntityManager em;

   private List<Temperatura> temperaturas;


   @Produces
   @Named
   public List<Temperatura> getTemparturas() {
      return temperaturas;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Temperatura temperatura) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Temperatura> criteria = cb.createQuery(Temperatura.class);
      Root<Temperatura> temperatura = criteria.from(Temperatura.class);
      criteria.select(temperatura).orderBy(cb.asc(temperatura.get("id")));
      temperaturas = em.createQuery(criteria).getResultList();
   }
}
