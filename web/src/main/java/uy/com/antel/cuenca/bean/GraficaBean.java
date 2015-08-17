package uy.com.antel.cuenca.bean;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import uy.com.antel.cuenca.data.TemperaturaListProducer;
import uy.com.antel.cuenca.model.Temperatura;

@ManagedBean
@RequestScoped
public class GraficaBean {

		@Inject 
		private TemperaturaListProducer valores;
		
	    private LineChartModel animatedModel1;
	    private LineChartModel animatedModel2;
	    private BarChartModel animatedModel3;
	    private BarChartModel animatedModel4;
	 
	    @PostConstruct
	    public void init() {
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
	    private void createAnimatedModels() {
	        animatedModel1 = initLinearModel1();
	        animatedModel1.setTitle("Evolución PH y DO");
	        animatedModel1.setAnimate(true);
	        animatedModel1.setLegendPosition("se");
	        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
	        yAxis.setMin(0);
	        yAxis.setMax(10);
	         
	        animatedModel2 = initLinearModel2();
	        animatedModel2.setTitle("Evolución Tempertura");
	        animatedModel2.setAnimate(true);
	        animatedModel2.setLegendPosition("se");
	        Axis yAxis2 = animatedModel2.getAxis(AxisType.Y);
	        yAxis2.setMin(0);
	        yAxis2.setMax(30);
	        
	        animatedModel3 = initBarModel1();
	        animatedModel3.setTitle("Nivel de Bateria");
	        animatedModel3.setAnimate(true);
	        animatedModel3.setLegendPosition("ne");
	        yAxis = animatedModel3.getAxis(AxisType.Y);
	        yAxis.setMin(0);
	        yAxis.setMax(10);
	        
	        animatedModel4 = initBarModel2();
	        animatedModel4.setTitle("Niveles Totales");
	        animatedModel4.setAnimate(true);
	        animatedModel4.setLegendPosition("ne");
	        yAxis = animatedModel4.getAxis(AxisType.Y);
	        yAxis.setMin(0);
	        yAxis.setMax(30);
	    }

	    private LineChartModel initLinearModel1() {
	    	LineChartModel model = new LineChartModel();
	    	LineChartSeries PH = new LineChartSeries();
	        PH.setLabel("PH");
	        for (Temperatura medidaPH : valores.getTemparturas()){
	        	PH.set(medidaPH.getId()/100, Float.parseFloat(medidaPH.getPh()));
	        }
	        	
	        LineChartSeries DO = new LineChartSeries();
	        DO.setLabel("DO");
	        for (Temperatura medidaDO : valores.getTemparturas()){
	        	DO.set(medidaDO.getId()/100, Float.parseFloat(medidaDO.getdisoxi())*100);
	        }
	        
	        model.addSeries(PH);
	        model.addSeries(DO);
	         
	        return model;
	    } 
	    
	    private LineChartModel initLinearModel2() {
	    	LineChartModel model = new LineChartModel();
	    	LineChartSeries celcius = new LineChartSeries();
	        celcius.setLabel("Celsius");
	        for (Temperatura medidaC : valores.getTemparturas()){
	        	celcius.set(medidaC.getId()/100, Float.parseFloat(medidaC.getCelcius()));
	        }
	        model.addSeries(celcius); 
	        return model;
	    }
	    
	    private BarChartModel initBarModel1() {
	        BarChartModel model = new BarChartModel();
	        ChartSeries voltaje = new ChartSeries();
	        int nomSensor = valores.getTemparturas().get(0).getSensor();
	        voltaje.setLabel("Sensor: "+nomSensor);
	        float v=0;
	        for (Temperatura medidaV : valores.getTemparturas()){
	        	float temp = Float.parseFloat(medidaV.getVoltajebateria());
	        	if (temp>v)
	        		v=temp;
	        }
	        voltaje.set("Nivel", v);
	        model.addSeries(voltaje);
	        return model;
	    }
	    
	    private BarChartModel initBarModel2() {
	        BarChartModel model = new BarChartModel();
	        
	        ChartSeries Bateria = new ChartSeries();
	        Bateria.setLabel("Bateria");
	        float b=0;
	        for (Temperatura med : valores.getTemparturas()){
	        	float temp = Float.parseFloat(med.getVoltajebateria());
	        	if (temp>b)
	        		b=temp;
	        }
	        Bateria.set("Valores", b);
	        
	        ChartSeries PH = new ChartSeries();
	        float p=0;
	        PH.setLabel("PH");
	        for (Temperatura med : valores.getTemparturas()){
	        	float temp = Float.parseFloat(med.getPh());
	        	if (temp>p)
	        		p=temp;
	        }
	        PH.set("Valores", p);
	        
	        ChartSeries Grados = new ChartSeries();
	        float g=0;
	        Grados.setLabel("Grados");
	        for (Temperatura med : valores.getTemparturas()){
	        	float temp = Float.parseFloat(med.getCelcius());
	        	if (temp>g)
	        		g=temp;
	        }
	        Grados.set("Valores",g);
	        
	        ChartSeries DO = new ChartSeries();
	        DO.setLabel("DO");
	        float d=0;
	        for (Temperatura med : valores.getTemparturas()){
	        	float temp = Float.parseFloat(med.getdisoxi())*10;
	           	if (temp>d)
	        		d=temp;
	        }
	        DO.set("Valores", d);
	        
	        model.addSeries(Grados);
	        model.addSeries(Bateria);
	        model.addSeries(PH);
	        model.addSeries(DO);
	        return model;
	    }
	
}
