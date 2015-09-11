package uy.com.antel.cuenca.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@XmlRootElement
@Table(name = "mediciones")
@JsonIgnoreProperties({"sensor","medida"})
public class Medicion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne(optional=false) 
    @JoinColumn(name="sonsor_id", nullable=false, updatable=false)
	private Sensor sensor;
	
    @ManyToOne(optional=false) 
    @JoinColumn(name="medida_id", nullable=false, updatable=false)
	private Medida medida;
	
	private float valor;
	
	private Date fechaMedida;
	
	private Date fechaServer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getFechaMedida() {
		return fechaMedida;
	}

	public void setFechaMedida(Date fechaMedida) {
		this.fechaMedida = fechaMedida;
	}

	public Date getFechaServer() {
		return fechaServer;
	}

	public void setFechaServer(Date fechaServer) {
		this.fechaServer = fechaServer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fechaMedida == null) ? 0 : fechaMedida.hashCode());
		result = prime * result
				+ ((fechaServer == null) ? 0 : fechaServer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(valor);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicion other = (Medicion) obj;
		if (fechaMedida == null) {
			if (other.fechaMedida != null)
				return false;
		} else if (!fechaMedida.equals(other.fechaMedida))
			return false;
		if (fechaServer == null) {
			if (other.fechaServer != null)
				return false;
		} else if (!fechaServer.equals(other.fechaServer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}
	
		
}