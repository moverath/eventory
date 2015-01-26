package de.overath.cocktailsundco.inventarverwaltung;

import java.util.logging.LogManager;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.sun.istack.logging.Logger;

import de.overath.cocktailsundco.inventarverwaltung.controller.CheckPendingEventsController;
import de.overath.cocktailsundco.inventarverwaltung.controller.ConnectionController;
import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class InventarverwaltungActivator
{
    public static void main(String[] args) {
	
	try {
	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	            UIManager.setLookAndFeel(info.getClassName());
	            break;
	        }
	    }
	} catch (Exception e) {
	    // If Nimbus is not available, you can set the GUI to another look and feel.
	}
	
//	try { 
//	    UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() ); 
//	} catch( Exception e ) { 
//	    e.printStackTrace(); 
//	}
	
	System.setProperty( "java.util.logging.config.file", "data/logging.properties" );
	
	
	try { LogManager.getLogManager().readConfiguration(); }
	
	
	catch ( Exception e ) { e.printStackTrace(); }
	
	try
	{
	    ConnectionController.getEntityManager();
	    LoadDataController loadDataController = new LoadDataController();
	    loadDataController.loadItems(null, null, LoadDataController.ONLY_EQUIPMENT, 0, loadDataController.findMaxVorratsmenge(LoadDataController.ONLY_EQUIPMENT));
	    loadDataController.loadItems(null, null, LoadDataController.ONLY_VERBRAUCHSARTIKEL,0, loadDataController.findMaxVorratsmenge(LoadDataController.ONLY_VERBRAUCHSARTIKEL));
	    loadDataController.loadVeranstaltungen();
	    
	    InventarverwaltungMainFrame inventarverwaltungMainFrame = new InventarverwaltungMainFrame();
	    inventarverwaltungMainFrame.init("Cocktails&Co softwaresuite");
	    inventarverwaltungMainFrame.setSize(300, 300);
	    inventarverwaltungMainFrame.setVisible(true);
	    
	    
	    new CheckPendingEventsController().check(loadDataController, inventarverwaltungMainFrame);
	}
	catch(Exception ex)
	{
	    Logger logger = Logger.getLogger(InventarverwaltungActivator.class);
	    logger.severe("Fehler bei der Ausführung", ex);
	}
	
	
    }
}
