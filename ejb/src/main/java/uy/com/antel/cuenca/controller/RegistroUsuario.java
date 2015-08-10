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

import uy.com.antel.cuenca.model.Usuario;


@Stateful
@Model
public class RegistroUsuario {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Usuario> usuarioEventSrc;

	   private Usuario newUsuario;

	   @Produces
	   @Named
	   public Usuario getNewUsuario() {
	      return newUsuario;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newUsuario.getNombre());
	      em.persist(newUsuario);
	      usuarioEventSrc.fire(newUsuario);
	      initNewUsuario();
	   }
	   
	   public void modificar(Usuario usuario) throws Exception {
		   log.info("Modifico " + usuario);
		   em.merge(usuario);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Usuario usuario = em.find(Usuario.class, id);
		   em.remove(usuario);
		   usuarioEventSrc.fire(newUsuario);
	   }

	   @PostConstruct
	   public void initNewUsuario() {
		   newUsuario = new Usuario();
	   }
}