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

import uy.com.antel.cuenca.model.Temperatura;


@Stateful
@Model
public class RegistroTemperatura {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Temperatura> temperaturaEventSrc;

	   private Temperatura newTemperatura;

	   @Produces
	   @Named
	   public Temperatura getnewTemperatura() {
	      return newTemperatura;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newTemperatura.getId());
	      em.persist(newTemperatura);
	      temperaturaEventSrc.fire(newTemperatura);
	      initNewTemperatura();
	   }
	   
	   public void modificar(Temperatura temperatura) throws Exception {
		   log.info("Modifico " + temperatura);
		   em.merge(temperatura);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Temperatura temperatura = em.find(Temperatura.class, id);
		   em.remove(temperatura);
		   temperaturaEventSrc.fire(newTemperatura);
	   }

	   public Temperatura buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Temperatura temperatura = em.find(Temperatura.class, id);
		   return temperatura;
	   }
	   
	   @PostConstruct
	   public void initNewTemperatura() {
		   newTemperatura = new Temperatura();
	   }
}