

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

import uy.com.antel.cuenca.model.Medicion;

@RequestScoped
public class MedicionListProducer {
	
   @Inject
   private EntityManager em;

   private List<Medicion> mediciones;


   @Produces
   @Named
   public List<Medicion> getmediciones() {
      return mediciones;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Medicion medicion) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Medicion> criteria = cb.createQuery(Medicion.class);
      Root<Medicion> medicion = criteria.from(Medicion.class);
      criteria.select(medicion).orderBy(cb.asc(medicion.get("medicion")));
      mediciones = em.createQuery(criteria).getResultList();
   }
}
