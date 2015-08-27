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

import uy.com.antel.cuenca.model.Nodo;

@Stateful
@Model
public class RegistroNodo {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Nodo> nodoEventSrc;

	   private Nodo newNodo;

	   @Produces
	   @Named
	   public Nodo getNewNodo() {
	      return newNodo;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newNodo.getNombre());
	      em.persist(newNodo);
	      nodoEventSrc.fire(newNodo);
	      initNewNodo();
	   }
	   
	   public void modificar(Nodo nodo) throws Exception {
		   log.info("Modifico " + nodo);
		   em.merge(nodo);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Nodo nodo = em.find(Nodo.class, id);
		   em.remove(nodo);
		   nodoEventSrc.fire(newNodo);
	   }

	   public Nodo buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Nodo nodo = em.find(Nodo.class, id);
		   return nodo;
	   }
	   
	   @PostConstruct
	   public void initNewNodo() {
		   newNodo = new Nodo();
	   }
}