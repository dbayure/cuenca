package uy.com.antel.cuenca.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
  
@ManagedBean
@ViewScoped
public class MapaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject GraficaBean graficas;

	private MapModel mapa;
	private Marker marcador;
	
	@PostConstruct
    public void init() {
        mapa = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(-34.608873, -56.216843);
          
        //Basic marker
        mapa.addOverlay(new Marker(coord1, "Facultad Agronomia","/resources/gfx/crs2.jpg"));
    }
  
    public MapModel getMapa() {
        return mapa;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        marcador = (Marker) event.getOverlay();
    }
      
    public Marker getMarcdor() {
        return marcador;
    }
    
    public String graficas(){
    	return "/paginas/cuenca/graficas.jsf?faces-redirect=true";
    }
	
}
