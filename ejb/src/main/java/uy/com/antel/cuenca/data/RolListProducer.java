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

import uy.com.antel.cuenca.model.Rol;



@RequestScoped
public class RolListProducer {
	
   @Inject
   private EntityManager em;

   private List<Rol> roles;


   @Produces
   @Named
   public List<Rol> getRoles() {
      return roles;
   }

   public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Rol rol) {
	      retrieveAllOrderedByName();
   }

   @PostConstruct
   public void retrieveAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Rol> criteria = cb.createQuery(Rol.class);
      Root<Rol> rol = criteria.from(Rol.class);
      criteria.select(rol).orderBy(cb.asc(rol.get("rol")));
      roles = em.createQuery(criteria).getResultList();
   }
}
