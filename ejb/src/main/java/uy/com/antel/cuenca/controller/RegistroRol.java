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
import javax.persistence.Query;
import uy.com.antel.cuenca.model.Rol;

@Stateful
@Model
public class RegistroRol {
	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Rol> rolEventSrc;

	   private Rol newRol;

	   @Produces
	   @Named
	   public Rol getNewRol() {
	      return newRol;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newRol.getRol());
	      em.persist(newRol);
	      rolEventSrc.fire(newRol);
	      initNewrol();
	   }
	   
	   public void modificar(Rol rol) throws Exception {
		   log.info("Modifico " + rol);
		   em.merge(rol);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Rol rol = em.find(Rol.class, id);
		   em.remove(rol);
		   rolEventSrc.fire(newRol);
	   }
	   
	   public Rol buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Rol rol = em.find(Rol.class, id);
		   return rol;
	   }

	   public Rol buscarPerfilBasico() throws Exception{
		   Query q = em.createQuery("Select r from Rol r where r.descripcion = ?1");
		   q.setParameter(1,"BASICO");
		   
		   Rol rolBasico = (Rol) q.getSingleResult(); 
		   
		   return rolBasico;

	   }
	   
	   @PostConstruct
	   public void initNewrol() {
		   newRol = new Rol();
	   }
	  

}
