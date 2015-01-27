package de.overath.cocktailsundco.inventarverwaltung.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.NumberFormatter;

import info.clearthought.layout.TableLayout;

import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.view.filter.InventarTableFilter;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.IInventarverwaltungFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.ItemFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.CreateItemListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.HomeButtonListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.SearchForArtikelnummerSpellerListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.SearchForNameSpellerListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.VorratsmengeListener;
import de.overath.cocktailsundco.inventarverwaltung.view.model.InventarTableModel;
import de.overath.cocktailsundco.inventarverwaltung.view.util.InventarverwaltungUtil;

public class InventarPanel extends JPanel implements IInventarverwaltungFrame
{
    
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;
    
    private JTextField searchForNameTextField;
    
    private JTable inventarTable;
    
    private InventarTableModel inventarTableModel;
    
    protected LoadDataController loadInventarDataController;

    protected InventarTableFilter inventarTableFilter;

    private JTextField searchForArtikelnummerTextField;

    private JFormattedTextField vonTextField;

    private JFormattedTextField bisTextField;

    private JButton vonBisFilterButton;
    
    private static final Color BUTTON_BACKGROUND_COLOR = Color.WHITE;
    
    private static final int GAP_BORDER = 10;
    private static final int GAP_BUTTON = 5;
    private static final int GAP_TEXT_COMPONENT = 3;
    private static final int BUTTON_HEIGHT = 40;
    private static final int SPELLER_HEIGHT = 30;
    private static final int SPELLER_TEXT_HEIGHT = 15;
    private static final int BUTTON_WIDTH = 215;
    
    private static final String HOME_BUTTON_LOGO = "img/homebutton_logo.png";
    
    private static final String SEARCH_BY_NAME = "Namen suchen";
    private static final String SEARCH_BY_ARTIKELNUMMER = "Artikelnummer suchen";
    private static final String VORRAT = "Vorrat von - bis";

    
    public InventarPanel(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
	init();
    }
    
    private void init() {
	double[][] layoutSize = {
		{ 10, BUTTON_WIDTH, 20, TableLayout.FILL, 10 },
		{ GAP_BORDER, BUTTON_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT, GAP_TEXT_COMPONENT, SPELLER_TEXT_HEIGHT,
			GAP_BUTTON, SPELLER_HEIGHT, GAP_BUTTON, SPELLER_TEXT_HEIGHT, GAP_BUTTON, SPELLER_HEIGHT, GAP_BUTTON,
			SPELLER_TEXT_HEIGHT, GAP_COMPONENT, SPELLER_HEIGHT*2, TableLayout.FILL, GAP_BORDER } };
	TableLayout layout = new TableLayout(layoutSize);
	setLayout(layout);
	
	JButton homeButton = createButton(null, new HomeButtonListener(inventarverwaltungMainFrame), HOME_BUTTON_LOGO);
	JButton createItemButton = InventarverwaltungUtil.createButton("Equipment erstellen", new CreateItemListener(inventarverwaltungMainFrame));
	add(homeButton, "1,1");
	add(createItemButton, "1,3");
	add(createTitleLabel(SEARCH_BY_NAME), "1,5");
	add(getSearchForNameTextField(), "1,7");
	add(createTitleLabel(SEARCH_BY_ARTIKELNUMMER), "1,9");
	add(getSearchForArtikelnummerTextField(), "1,11");
	add(createTitleLabel(VORRAT), "1,13");
	add(createVonBisPanel(), "1,15");
	
	add(new JScrollPane(getInventarTable()), "3, 1,3,16");
    }
    
    private JPanel createVonBisPanel() {
	JPanel panel = new JPanel();
	TableLayout layout = new TableLayout();
	layout.setColumn(new double[] {TableLayout.FILL, GAP_COMPONENT, 20,GAP_COMPONENT, TableLayout.FILL});
	layout.setRow(new double[] {TableLayout.FILL, GAP_BORDER, TableLayout.FILL});
	panel.setLayout(layout);
	panel.add(getVonNumberTextField(), "0,0");
	panel.add(new JLabel("-"), "2,0");
	panel.add(getBisNumberTextField(), "4,0");
	panel.add(getVonBisFilterButton(), "0,2,4,2");
	panel.setBorder(BorderFactory.createEtchedBorder());
	return panel;
    }

    private JButton getVonBisFilterButton() {
	if(vonBisFilterButton == null)
	{
	    vonBisFilterButton = new JButton("Voratsmenge filter anwenden");
	    vonBisFilterButton.addActionListener(new VorratsmengeListener(this, getInventarTableFilter()));
	}
	return vonBisFilterButton;
    }

    public JFormattedTextField getBisNumberTextField() {
	if(bisTextField == null)
	{
	    NumberFormat format = NumberFormat.getInstance(); 
	    format.setGroupingUsed(false); 
	    NumberFormatter formatter = new NumberFormatter(format); 
	    bisTextField = new JFormattedTextField(formatter);
	}
	return bisTextField;
    }

    public JFormattedTextField getVonNumberTextField() {
	if(vonTextField == null)
	{
	    NumberFormat format = NumberFormat.getInstance(); 
	    format.setGroupingUsed(false); 
	    NumberFormatter formatter = new NumberFormatter(format); 
	    vonTextField = new JFormattedTextField(formatter);
	}
	return vonTextField;
    }

    public JTable getInventarTable() {
	if (inventarTable == null)
	{
	    inventarTable = new JTable();
	    inventarTable.setModel(getInventarTableModel());
	    inventarTable.getColumnModel().getColumn(0).setMaxWidth(100);
	    inventarTable.getColumnModel().getColumn(InventarTableModel.COLUMN_ICON).setMaxWidth(50);
	    inventarTable.setFont(TABLE_FONT);
	    inventarTable.setIntercellSpacing(TABLE_CELL_INTERSPACING);
	    inventarTable.setRowHeight(50);
	    inventarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    inventarTable.setCellSelectionEnabled(true);
	    inventarTable.setAutoCreateRowSorter(true);
	    
	    inventarTable.addMouseListener(new MouseAdapter()
	    {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2)
		    {
			int index = getInventarTable().getSelectedRow();
			int realIndex = getInventarTable().convertRowIndexToModel(index);
			new ItemFrame(inventarverwaltungMainFrame, getInventarTableModel().getItem(realIndex));
		    }
		}
	    });
	}
	return inventarTable;
    }
    
    public InventarTableModel getInventarTableModel() {
	if (inventarTableModel == null)
	{
	    inventarTableModel = new InventarTableModel();
	}
	return inventarTableModel;
    }
    
    private JLabel createTitleLabel(String searchByName) {
	JLabel label = new JLabel(searchByName);
	return label;
    }
    
    protected JTextField getSearchForNameTextField() {
	if (searchForNameTextField == null)
	{
	    searchForNameTextField = new JTextField();
	    searchForNameTextField.getDocument().addDocumentListener(new SearchForNameSpellerListener(getInventarTableFilter()));
	}
	return searchForNameTextField;
    }

    protected JTextField getSearchForArtikelnummerTextField() {
	if (searchForArtikelnummerTextField == null)
	{
	    searchForArtikelnummerTextField = new JTextField();
	    searchForArtikelnummerTextField.getDocument().addDocumentListener(new SearchForArtikelnummerSpellerListener(getInventarTableFilter()));
	}
	return searchForArtikelnummerTextField;
    }
    
    protected InventarTableFilter getInventarTableFilter() {
	if(inventarTableFilter == null)
	{
	    inventarTableFilter = new InventarTableFilter(this, false);
	}
	return inventarTableFilter;
    }

    public void activated() {
	if (loadInventarDataController == null)
	{
	    loadInventarDataController = new LoadDataController();
	}
	String substring = getSearchForNameTextField().getText().trim();
	getInventarTableFilter().setTextFilter(substring);
	
	getInventarTable().invalidate();
	getInventarTable().repaint();
    }
    
    private JButton createButton(String string, ActionListener listener, String iconPath) {
	JButton button = new JButton();
	button.setLayout(new BorderLayout());
	button.setForeground(BUTTON_BACKGROUND_COLOR);
	button.setBackground(BUTTON_BACKGROUND_COLOR);
	if (string != null)
	{
	    JLabel label = new JLabel(string);
	    label.setForeground(new Color(231, 30, 50));
	    label.setFont(new Font("button", Font.BOLD, 16));
	    button.add(label, BorderLayout.PAGE_START);
	}
	if (iconPath != null)
	{
	    JLabel iconLabel = new JLabel();
	    iconLabel.setIcon(new ImageIcon(iconPath));
	    button.add(iconLabel, BorderLayout.CENTER);
	}
	
	button.addActionListener(listener);
	return button;
    }
    
}
