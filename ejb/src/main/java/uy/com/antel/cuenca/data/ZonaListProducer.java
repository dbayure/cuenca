

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

import uy.com.antel.cuenca.model.Zona;


@RequestScoped
public class ZonaListProducer {
	
   @Inject
   private EntityManager em;

   private List<Zona> zonas;


   @Produces
   @Named
   public List<Zona> getzonas() {
      return zonas;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Zona zona) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Zona> criteria = cb.createQuery(Zona.class);
      Root<Zona> zona = criteria.from(Zona.class);
      criteria.select(zona).orderBy(cb.asc(zona.get("nombre")));
      zonas = em.createQuery(criteria).getResultList();
   }
}
