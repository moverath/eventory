package de.overath.cocktailsundco.inventarverwaltung.view.panel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import layout.TableLayout;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.IInventarverwaltungFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.CreateItemListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.CreateItemSetListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.CreateKategorieListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.CreateVeranstaltungListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.ShowItemListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.ShowItemSetListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.ShowKategorieListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.ShowVeranstaltungListener;
import de.overath.cocktailsundco.inventarverwaltung.view.util.InventarverwaltungUtil;

public class HomePanel extends JPanel implements IInventarverwaltungFrame
{
    private static final int GAP_BORDER = 10;
    private static final int GAP_BUTTON = 5;
    
    private static final int BUTTON_HEIGHT = 40;
    
    private static final String COCKTAILS_UND_CO_LOGO_ROT = "img/homePanelLogo.png";
    private static final int BUTTON_WIDTH = 215;;
    
    private InventarverwaltungMainFrame mainFrame;
    
    private BufferedImage image;
    
    public HomePanel(InventarverwaltungMainFrame mainFrame)
    {
	try {                
	          image = ImageIO.read(new File(COCKTAILS_UND_CO_LOGO_ROT));
	       } catch (IOException ex) {
		   System.out.println(ex.getStackTrace());
	       }
	this.mainFrame = mainFrame;
	init();
    }
    
    private void init() {
	
	double[][] layoutSize = {
		{ 10, BUTTON_WIDTH, 20, TableLayout.FILL, 10 },
		{ GAP_BORDER, BUTTON_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT, GAP_BUTTON,
		    BUTTON_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT,GAP_BUTTON, BUTTON_HEIGHT,TableLayout.FILL,
		    GAP_BORDER } };
	TableLayout layout = new TableLayout(layoutSize);
	
	setLayout(layout);
	
	// Button to create new Item
	JButton createItemButton = InventarverwaltungUtil.createButton("Equipment hinzufügen", new CreateItemListener(mainFrame));
	
	// Button to create new Item
	JButton showItemButton = InventarverwaltungUtil.createButton("Equipment anzeigen", new ShowItemListener(mainFrame));
	
	// Button to create new Veranstaltung
	JButton createVeranstaltungButton = InventarverwaltungUtil.createButton("Veranstaltung erstellen", new CreateVeranstaltungListener(
		mainFrame));
	
	// Button to create new Veranstaltung
	JButton showVeranstaltungButton = InventarverwaltungUtil.createButton("Veranstaltung anzeigen", new ShowVeranstaltungListener(
		mainFrame));
	
	// Button to create new ItemSet
	JButton createItemSetButton = InventarverwaltungUtil.createButton("Packet / Set erstellen", new CreateItemSetListener(mainFrame));
	
	// Button to create new ItemSet
	JButton showItemSetButton = InventarverwaltungUtil.createButton("Packete / Sets anzeigen", new ShowItemSetListener(mainFrame));

	// Button to create new ItemSet
	JButton createKategorieButton = InventarverwaltungUtil.createButton("Kategorie erstellen", new CreateKategorieListener(mainFrame));
	
	// Button to create new ItemSet
	JButton showKategorieButton = InventarverwaltungUtil.createButton("Kategorien anzeigen", new ShowKategorieListener(mainFrame));

	// Add Buttons to homePanel
	add(createVeranstaltungButton, "1,1");
	add(showVeranstaltungButton, "1,3");
	add(createItemButton, "1,5");
	add(showItemButton, "1,7");
	add(createItemSetButton,"1,9");
	add(showItemSetButton, "1,11");
	add(createKategorieButton,"1,13");
	add(showKategorieButton, "1,15");
	
	
	JLabel picLabel = new JLabel(new ImageIcon(image));
	add(picLabel, "3,1,3,17");
    }
    
    
    public void activated() {
	
    }
    
}
