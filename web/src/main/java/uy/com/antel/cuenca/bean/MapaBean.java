package uy.com.antel.cuenca.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@RequestScoped
public class MapaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private MapModel simpleModel;
	
	@PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(-34.608873, -56.216843);
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Centro Regional Sur"));
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
	
}
