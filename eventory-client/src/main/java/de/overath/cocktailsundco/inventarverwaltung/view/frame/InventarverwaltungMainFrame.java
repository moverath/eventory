package de.overath.cocktailsundco.inventarverwaltung.view.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import com.sun.istack.logging.Logger;

import de.overath.cocktailsundco.inventarverwaltung.model.Veranstaltung;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.PendingEventListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.TabChangeListener;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.HomePanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.InventarPanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.ItemSetPanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.KategoriePanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.VeranstaltungPanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.VerbrauchsartikelPanel;

public class InventarverwaltungMainFrame extends JFrame implements IInventarverwaltungFrame, PendingEventListener
{
    private static final String HOME = "Home";
    private static final String KATEGORIEN = "Kategorien";
    private static final String VERANSTALTUNG = "Veranstaltungen";
    private static final String ITEMSET = "Packete / Sets";
    private static final String EQUIPMENT= "Equipment";
    private static final String WARE = "Ware";
    
    Logger logger = Logger.getLogger(InventarverwaltungMainFrame.class);
    
    private static final Dimension DEFAULTSIZE = new Dimension(900, 470);
    private static final Dimension MINIMUMSIZE = new Dimension(900, 470);
    
    private static final String BEENDEN_MENU_STRING = "Beeenden";
    private final String DATEI_MENU_STRING = "Datei";
    
    private JTabbedPane tabbedPane;
    private HomePanel homePanel;
    
    private InventarPanel inventarPanel;
    
    private ItemSetPanel itemSetPanel;
    
    private KategoriePanel kategoriePanel;
    
    private VeranstaltungPanel veranstaltungPanel;
    private VerbrauchsartikelPanel verbrauchsartikelPanel;
    
    public InventarverwaltungMainFrame()
    {
	WindowListener windowListener = new WindowAdapter()
	{
	    @Override
	    public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		System.exit(1);
	    }
	    
	    @Override
	    public void windowClosed(WindowEvent e) {
		super.windowClosed(e);
	    }
	};
	
	addWindowListener(windowListener);
	
	init("Cocktails&Co softwaresuite");
	setSize(DEFAULTSIZE);
	setMinimumSize(MINIMUMSIZE);
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds(screenSize.width / 2 - DEFAULTSIZE.width / 2, screenSize.height / 2 - DEFAULTSIZE.height / 2,
		DEFAULTSIZE.width, DEFAULTSIZE.height);
    }
    
    public void init(String name) {
	logger.finest("Inventarverwaltung#init()");
	setTitle(name);
	
	ImageIcon img = new ImageIcon(INVENTARVERWALTUNG_LOGO);
	setIconImage(img.getImage());
	
	// init menu
	setJMenuBar(createMenuBar());
	
	// init tabbedPane
	this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	// init home tab and add to tabbedPane
	this.homePanel = createHomePanel();
	homePanel.setName(HOME);
	tabbedPane.add(homePanel, 0);

	this.veranstaltungPanel = createVeranstaltungPanel();
	tabbedPane.add(VERANSTALTUNG, veranstaltungPanel);
	
	this.inventarPanel = createInventarPanel();
	tabbedPane.add(EQUIPMENT, inventarPanel);

	this.verbrauchsartikelPanel = createVerbrauchsartikelPanel();
	tabbedPane.add(WARE, verbrauchsartikelPanel);
	
	this.itemSetPanel = createItemSetPanel();
	tabbedPane.add(ITEMSET, itemSetPanel);
	
	this.kategoriePanel = createKategoriePanel();
	tabbedPane.add(KATEGORIEN, kategoriePanel);
	
	tabbedPane.addChangeListener(new TabChangeListener(this));
	
	// add the tabbedPane to the contentPane
	getContentPane().add(tabbedPane);
    }
    
    private VeranstaltungPanel createVeranstaltungPanel() {
	return new VeranstaltungPanel(this);
    }

    private KategoriePanel createKategoriePanel() {
	return new KategoriePanel(this);
    }
    
    private ItemSetPanel createItemSetPanel() {
	return new ItemSetPanel(this);
    }
    
    private InventarPanel createInventarPanel() {
	return new InventarPanel(this);
    }

    private VerbrauchsartikelPanel createVerbrauchsartikelPanel() {
	return new VerbrauchsartikelPanel(this);
    }
    
    private JMenuBar createMenuBar() {
	JMenuBar menuBar = new JMenuBar();
	menuBar.add(getDateiMenu());
	return menuBar;
    }
    
    public JMenu getDateiMenu() {
	JMenu menu = new JMenu(DATEI_MENU_STRING);
	menu.add(getBeendenItem());
	return menu;
    }
    
    public JMenuItem getBeendenItem() {
	JMenuItem endItem = new JMenuItem(BEENDEN_MENU_STRING);
	endItem.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent aE) {
		System.exit(0);
	    }
	});
	return endItem;
    }
    
    private HomePanel createHomePanel() {
	return new HomePanel(this);
    }
    
    public JTabbedPane getTabPane() {
	return tabbedPane;
    }
    
    public HomePanel getHomePanel() {
	return homePanel;
    }
    
    public InventarPanel getInventarPanel() {
	return inventarPanel;
    }
    
    public VerbrauchsartikelPanel getVerbrauchsartikelPanel() {
	return verbrauchsartikelPanel;
    }
    
    public ItemSetPanel getItemSetPanel() {
	return itemSetPanel;
    }
    
    public KategoriePanel getKategoriePanel() {
	return kategoriePanel;
    }

    public VeranstaltungPanel getVeranstaltungPanel() {
	return veranstaltungPanel;
    }

    @Override
    public void pendingEventFound(Veranstaltung veranstaltung, int reason) {
	//TODO show pendingEvents
    }

    @Override
    public void noPendingEventFound() {
	// TODO hide "check for pending event window"
	
    }
}
