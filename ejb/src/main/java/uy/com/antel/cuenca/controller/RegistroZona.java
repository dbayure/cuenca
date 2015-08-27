package uy.com.antel.cuenca.controller;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import uy.com.antel.cuenca.model.Zona;

@Stateful
@Model
public class RegistroZona {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Zona> zonaEventSrc;

	   private Zona newZona;

	   @Produces
	   @Named
	   public Zona getNewZona() {
	      return newZona;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newZona.getNombre());
	      em.persist(newZona);
	      zonaEventSrc.fire(newZona);
	      initNewZona();
	   }
	   
	   public void modificar(Zona zona) throws Exception {
		   log.info("Modifico " + zona);
		   em.merge(zona);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Zona zona = em.find(Zona.class, id);
		   em.remove(zona);
		   zonaEventSrc.fire(newZona);
	   }

	   public Zona buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Zona zona = em.find(Zona.class, id);
		   return zona;
	   }
	   
	   @PostConstruct
	   public void initNewZona() {
		   newZona = new Zona();
	   }
}