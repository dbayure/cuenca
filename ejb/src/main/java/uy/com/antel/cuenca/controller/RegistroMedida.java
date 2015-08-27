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

import uy.com.antel.cuenca.model.Medida;

@Stateful
@Model
public class RegistroMedida {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Medida> medidaEventSrc;

	   private Medida newMedida;

	   @Produces
	   @Named
	   public Medida getNewMedida() {
	      return newMedida;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newMedida.getNombre());
	      em.persist(newMedida);
	      medidaEventSrc.fire(newMedida);
	      initNewMedida();
	   }
	   
	   public void modificar(Medida medida) throws Exception {
		   log.info("Modifico " + medida);
		   em.merge(medida);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Medida medida = em.find(Medida.class, id);
		   em.remove(medida);
		   medidaEventSrc.fire(newMedida);
	   }

	   public Medida buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Medida medida = em.find(Medida.class, id);
		   return medida;
	   }
	   
	   @PostConstruct
	   public void initNewMedida() {
		   newMedida = new Medida();
	   }
}