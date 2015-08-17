package uy.com.antel.cuenca.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "temperatura")
public class Temperatura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Date fevento;

	private Date sfecha;

	private Integer sensor;

	private String celcius;

	private String humedad;
	
	private String disoxi;
	
	private String voltajebateria;
	
	private String ph;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getfevento() {
		return fevento;
	}

	public void setfevento(Date fevento) {
		this.fevento = fevento;
	}

	public Date getsfecha() {
		return sfecha;
	}

	public void setsfecha(Date sfecha) {
		this.sfecha = sfecha;
	}

	public Integer getSensor() {
		return sensor;
	}

	public void setSensor(Integer sensor) {
		this.sensor = sensor;
	}

	public String getCelcius() {
		return celcius;
	}

	public void setCelcius(String celcius) {
		this.celcius = celcius;
	}

	public String getHumedad() {
		return humedad;
	}

	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}

	public String getdisoxi() {
		return disoxi;
	}

	public void setdisoxi(String disoxi) {
		this.disoxi = disoxi;
	}

	public String getVoltajebateria() {
		return voltajebateria;
	}

	public void setVoltajebateria(String voltajebateria) {
		this.voltajebateria = voltajebateria;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Temperatura [id=" + id + ", fevento=" + fevento + ", sfecha="
				+ sfecha + ", sensor=" + sensor + ", celcius=" + celcius
				+ ", humedad=" + humedad + ", disoxi=" + disoxi + ", voltajebateria="
				+ voltajebateria + ", ph=" + ph + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disoxi == null) ? 0 : disoxi.hashCode());
		result = prime * result + ((fevento == null) ? 0 : fevento.hashCode());
		result = prime * result + ((sfecha == null) ? 0 : sfecha.hashCode());
		result = prime * result + ((celcius == null) ? 0 : celcius.hashCode());
		result = prime * result + ((humedad == null) ? 0 : humedad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ph == null) ? 0 : ph.hashCode());
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		result = prime * result
				+ ((voltajebateria == null) ? 0 : voltajebateria.hashCode());
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
		Temperatura other = (Temperatura) obj;
		if (disoxi == null) {
			if (other.disoxi != null)
				return false;
		} else if (!disoxi.equals(other.disoxi))
			return false;
		if (fevento == null) {
			if (other.fevento != null)
				return false;
		} else if (!fevento.equals(other.fevento))
			return false;
		if (sfecha == null) {
			if (other.sfecha != null)
				return false;
		} else if (!sfecha.equals(other.sfecha))
			return false;
		if (celcius == null) {
			if (other.celcius != null)
				return false;
		} else if (!celcius.equals(other.celcius))
			return false;
		if (humedad == null) {
			if (other.humedad != null)
				return false;
		} else if (!humedad.equals(other.humedad))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ph == null) {
			if (other.ph != null)
				return false;
		} else if (!ph.equals(other.ph))
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		if (voltajebateria == null) {
			if (other.voltajebateria != null)
				return false;
		} else if (!voltajebateria.equals(other.voltajebateria))
			return false;
		return true;
	}
	
	   
}