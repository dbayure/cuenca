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

import uy.com.antel.cuenca.model.Sensor;

@Stateful
@Model
public class RegistroSensor {

	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Sensor> sensorEventSrc;

	   private Sensor newSensor;

	   @Produces
	   @Named
	   public Sensor getNewSensor() {
	      return newSensor;
	   }

	   public void registro() throws Exception {
	      log.info("Registro " + newSensor.getNombre());
	      em.persist(newSensor);
	      sensorEventSrc.fire(newSensor);
	      initNewsensor();
	   }
	   
	   public void modificar(Sensor sensor) throws Exception {
		   log.info("Modifico " + sensor);
		   em.merge(sensor);
	   }
	   
	   public void eliminar(Long id) throws Exception {
		   log.info("Elimino " + id);
		   Sensor sensor = em.find(Sensor.class, id);
		   em.remove(sensor);
		   sensorEventSrc.fire(newSensor);
	   }

	   public Sensor buscar(Long id) throws Exception {
		   log.info("Buscar " + id);
		   Sensor sensor = em.find(Sensor.class, id);
		   return sensor;
	   }
	   
	   @PostConstruct
	   public void initNewsensor() {
		   newSensor = new Sensor();
	   }
}