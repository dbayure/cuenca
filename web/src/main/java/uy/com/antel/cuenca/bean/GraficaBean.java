package uy.com.antel.cuenca.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import uy.com.antel.cuenca.data.MedicionListProducer;
import uy.com.antel.cuenca.data.MedidaListProducer;
import uy.com.antel.cuenca.data.SensorListProducer;
import uy.com.antel.cuenca.model.Medicion;
import uy.com.antel.cuenca.model.Medida;
import uy.com.antel.cuenca.model.Sensor;

@ManagedBean
@RequestScoped
public class GraficaBean {
		
		@Inject
		private MedidaListProducer medidas;
		
		@Inject
		private SensorListProducer sensores;
		
		@Inject
		private MedicionListProducer mediciones;
		
	    private LineChartModel animatedModel1;
	    private LineChartModel animatedModel2;
	    private BarChartModel animatedModel3;
	    private BarChartModel animatedModel4;
	    
	    private Date fechaMin;
	    private Date fechaMax;
	    
	    private boolean graficar = false;
	    
	    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	 
	    @PostConstruct
	    public void init() throws ParseException {
	        createAnimatedModels();
	    }
	 
	    public LineChartModel getAnimatedModel1() {
	        return animatedModel1;
	    }
	 
	    public LineChartModel getAnimatedModel2() {
	        return animatedModel2;
	    }
	    
	    public BarChartModel getAnimatedModel3() {
	        return animatedModel3;
	    }
	    
	    public BarChartModel getAnimatedModel4() {
	        return animatedModel4;
	    }
	    
		public Date getFechaMin() {
			return fechaMin;
		}

		public void setFechaMin(Date fechaMin) {
			this.fechaMin = fechaMin;
		}

		public Date getFechaMax() {
			return fechaMax;
		}

		public void setFechaMax(Date fechaMax) {
			this.fechaMax = fechaMax;
		}
		
		public boolean isGraficar() {
			return graficar;
		}

		public void setGraficar(boolean graficar) {
			this.graficar = graficar;
		}
	    
	    private void createAnimatedModels() throws ParseException {
	        animatedModel1 = initLinearModel1();
	        animatedModel1.setTitle("Valores según medidas");
	        animatedModel1.setAnimate(true);
	        animatedModel1.setLegendPosition("ne");
	        animatedModel1.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
	        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
	        yAxis.setMin(0);
	        yAxis.setMax(50);
	         
	        animatedModel2 = initLinearModel2();
	        animatedModel2.setTitle("Valores según sensores");
	        animatedModel2.setAnimate(true);
	        animatedModel2.setLegendPosition("ne");
	        animatedModel2.setLegendPlacement(LegendPlacement.OUTSIDEGRID);	        
	        Axis yAxis2 = animatedModel2.getAxis(AxisType.Y);
	        yAxis2.setMin(0);
	        yAxis2.setMax(50);
	        
	        animatedModel3 = initBarModel1();
	        animatedModel3.setTitle("Medidas por fecha");
	        animatedModel3.setAnimate(true);
	        animatedModel3.setLegendPosition("ne");
	        animatedModel3.setLegendPlacement(LegendPlacement.OUTSIDEGRID);	        
	        yAxis = animatedModel3.getAxis(AxisType.Y);
	        yAxis.setMin(0);
	        yAxis.setMax(30);
	        
	        animatedModel4 = initBarModel2();
	        animatedModel4.setTitle("Sensores por fecha");
	        animatedModel4.setAnimate(true);
	        animatedModel4.setLegendPosition("ne");
	        animatedModel4.setLegendPlacement(LegendPlacement.OUTSIDEGRID);	        
	        yAxis = animatedModel4.getAxis(AxisType.Y);
	        yAxis.setMin(0);
	        yAxis.setMax(30);
	    }

	    private LineChartModel initLinearModel1() {
	    	LineChartModel model = new LineChartModel();
	        for (Medida meds : medidas.getmedidas()){
		    	LineChartSeries nomSerie = new LineChartSeries();
		    	nomSerie.setLabel(meds.getNombre());
	        	for (Medicion m : meds.getMediciones()){
	        		nomSerie.set(m.getId(), m.getValor());
	        	}
	        	model.addSeries(nomSerie);
	        }
	        	         
	        return model;																																																																												
	    } 
	    
	    private LineChartModel initLinearModel2() {
	    	LineChartModel model = new LineChartModel();
	    	 for (Medida meds : medidas.getmedidas()){
		    	LineChartSeries nomSerie = new LineChartSeries();
		    	nomSerie.setLabel(meds.getNombre());
	        	for (Medicion m : meds.getMediciones()){
	        		nomSerie.set(m.getId(), m.getValor());
	        	}
	        	model.addSeries(nomSerie);
	        }
	        	         
	        return model;	
	    }
	    
	    private BarChartModel initBarModel1() {
	        BarChartModel model = new BarChartModel();
	        for (Sensor sens : sensores.getsensores()){
	        	ChartSeries nomSerie = new ChartSeries();
		    	nomSerie.setLabel(sens.getNombre());
	        	for (Medicion m : sens.getMediciones()){
	        		nomSerie.set(format.format(m.getFecha()), m.getValor());
	        	}
	        	model.addSeries(nomSerie);
	        }
	        	         
	        return model;	

	    }
	    
	    private BarChartModel initBarModel2() {
	        BarChartModel model = new BarChartModel();
	        for (Sensor sens : sensores.getsensores()){
	        	ChartSeries nomSerie = new ChartSeries();
		    	nomSerie.setLabel(sens.getNombre());
	        	for (Medicion m : sens.getMediciones()){
	        		nomSerie.set(format.format(m.getFecha()), m.getValor());
	        	}
	        	model.addSeries(nomSerie);
	        }
	        	         
	        return model;	
	    }

	    public void mostrarFecha(){
	    	if(fechaMax == null || fechaMin == null){
	    		fechaMax = new Date();
	    		fechaMin =  new Date();
	    	}
	    	for (Medicion m : mediciones.getmediciones()){
	    		if(m.getFecha().after(fechaMin) && m.getFecha().before(fechaMax)){
	    			graficar = true;
	    		}
	    	}
	    	if (!graficar){
	    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen datos a graficar en esa fecha ", "");  
		        FacesContext.getCurrentInstance().addMessage(null, msg); 
	    	}
			
	    	System.out.println("Fecha min: " + format.format(fechaMin) + "\n");
	    	System.out.println("Fecha max: " + format.format(fechaMax) + "\n");
	    	
	    }


}
