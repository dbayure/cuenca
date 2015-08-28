

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

import uy.com.antel.cuenca.model.Medida;


@RequestScoped
public class MedidaListProducer {
	
   @Inject
   private EntityManager em;

   private List<Medida> medidas;


   @Produces
   @Named
   public List<Medida> getmedidas() {
      return medidas;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Medida medida) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Medida> criteria = cb.createQuery(Medida.class);
      Root<Medida> medida = criteria.from(Medida.class);
      criteria.select(medida).orderBy(cb.asc(medida.get("nombre")));
      medidas = em.createQuery(criteria).getResultList();
   }
}
