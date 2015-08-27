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

import uy.com.antel.cuenca.model.Medicion;


@Stateful
@Model
public class RegistroMedicion {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Medicion> medicionEventSrc;

	   private Medicion newMedicion;

	   @Produces
	   @Named
	   public Medicion getNewMedicion() {
	      return newMedicion;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newMedicion.getId());
	      em.persist(newMedicion);
	      medicionEventSrc.fire(newMedicion);
	      initNewMedicion();
	   }
	   
	   public void modificar(Medicion medicion) throws Exception {
		   log.info("Modifico " + medicion);
		   em.merge(medicion);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Medicion medicion = em.find(Medicion.class, id);
		   em.remove(medicion);
		   medicionEventSrc.fire(newMedicion);
	   }

	   public Medicion buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Medicion medicion = em.find(Medicion.class, id);
		   return medicion;
	   }
	   
	   @PostConstruct
	   public void initNewMedicion() {
		   newMedicion = new Medicion();
	   }
}