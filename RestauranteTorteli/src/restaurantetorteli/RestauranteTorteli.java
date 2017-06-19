/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantetorteli;

import Log.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Alejandro
 */
public class RestauranteTorteli {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JRException {
        // TODO code application logic here
        /*
        List listaJugadores = new ArrayList();  
            

        for (int i = 1; i <= 49; i++)    
        {        
            listaJugadores.add(new Jugador(i, "Jugador " + i , "Wii"));    
        }     

        for(int i = 50; i <= 79; i++)    
        {        
            listaJugadores.add(new Jugador(i, "Jugador " + i , "XBox"));    
        }     

        for(int i = 80; i <= 100; i++)    
        {        
            listaJugadores.add(new Jugador(i, "Jugador " + i , "PS3"));    
        }     
        
          

        File ija = new File("reporteGrafica.jasper");
        JasperReport reporte = (JasperReport)JRLoader.loadObject(ija);    
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(listaJugadores));     

        JRExporter exporter = new JRPdfExporter();    
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);    
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File("reporte grafica.pdf"));     

        exporter.exportReport(); 
    
        
        
        
        */
        
        
        Log login = new Log();
        
       login.setVisible(true);
    }
    
}
