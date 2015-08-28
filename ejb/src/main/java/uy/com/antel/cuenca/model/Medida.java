package uy.com.antel.cuenca.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "medidas")
public class Medida implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nombre;

	private String unidad;

	private Integer valorMax;

	private Integer valorMin;

	private String estado;

	@OneToOne(optional=false, mappedBy="medida", fetch=FetchType.EAGER)
    private Sensor sensor;
    
    @OneToMany(targetEntity=Medicion.class, mappedBy="medida", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Medicion> mediciones;
    
    public Medida (){
    	super();
    	mediciones = new HashSet<Medicion>();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Integer getValorMax() {
		return valorMax;
	}

	public void setValorMax(Integer valorMax) {
		this.valorMax = valorMax;
	}

	public Integer getValorMin() {
		return valorMin;
	}

	public void setValorMin(Integer valorMin) {
		this.valorMin = valorMin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Set<Medicion> getMediciones() {
		return mediciones;
	}

	public void setMediciones(Set<Medicion> mediciones) {
		this.mediciones = mediciones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		result = prime * result + ((unidad == null) ? 0 : unidad.hashCode());
		result = prime * result
				+ ((valorMax == null) ? 0 : valorMax.hashCode());
		result = prime * result
				+ ((valorMin == null) ? 0 : valorMin.hashCode());
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
		Medida other = (Medida) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		if (unidad == null) {
			if (other.unidad != null)
				return false;
		} else if (!unidad.equals(other.unidad))
			return false;
		if (valorMax == null) {
			if (other.valorMax != null)
				return false;
		} else if (!valorMax.equals(other.valorMax))
			return false;
		if (valorMin == null) {
			if (other.valorMin != null)
				return false;
		} else if (!valorMin.equals(other.valorMin))
			return false;
		return true;
	}


}