package de.overath.cocktailsundco.inventarverwaltung.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import de.overath.cocktailsundco.inventarverwaltung.model.Kategorie;
import de.overath.cocktailsundco.inventarverwaltung.view.filter.ItemSetFilter;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.IInventarverwaltungFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.ItemSetFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.HomeButtonListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.PreisFilterListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.SearchForNameInItemSetSpellerListener;
import de.overath.cocktailsundco.inventarverwaltung.view.model.ItemSetTableModel;


public class ItemSetPanel extends JPanel implements IInventarverwaltungFrame
{
    private static final int GAP_BORDER = 10;
    private static final int GAP_BUTTON = 5;
    private static final int GAP_TEXT_COMPONENT = 3;
    private static final int BUTTON_HEIGHT = 40;
    private static final int SPELLER_HEIGHT = 30;
    private static final int SPELLER_TEXT_HEIGHT = 15;
    private static final int BUTTON_WIDTH = 215;
    
    private static final Color BUTTON_BACKGROUND_COLOR = Color.WHITE;
    private static final String HOME_BUTTON_LOGO = "img/homebutton_logo.png";
    
    private static final String SEARCH_BY_NAME = "Namen suchen";
    private static final String KATEGORIE = "Kategorie";
    private static final String PREISSPANNE = "Preisspanne";
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;
    private JTextField searchForNameTextField;
    private JTable inventarTable;
    private ItemSetTableModel itemSetTableModel;
    private LoadDataController loadDataController;
    private ItemSetFilter itemSetFilter;
    private JFormattedTextField vonTextField;
    private JFormattedTextField bisTextField;
    private JButton vonBisFilterButton;
    private JComboBox<Kategorie> kategorieComboBox;
    
    public ItemSetPanel(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
	init();
    }
    
    private void init() {
	double[][] layoutSize = {
		{ 10, BUTTON_WIDTH, 20, TableLayout.FILL, 10 },
		{ GAP_BORDER, BUTTON_HEIGHT, GAP_BUTTON, SPELLER_TEXT_HEIGHT, GAP_TEXT_COMPONENT, SPELLER_HEIGHT, GAP_BUTTON, SPELLER_TEXT_HEIGHT, GAP_TEXT_COMPONENT,
		    BUTTON_HEIGHT, GAP_BUTTON, SPELLER_TEXT_HEIGHT, GAP_TEXT_COMPONENT, SPELLER_HEIGHT*2, TableLayout.FILL,
		    GAP_BORDER } };
	TableLayout layout = new TableLayout(layoutSize);
	setLayout(layout);
	
	JButton homeButton = createButton(null, new HomeButtonListener(inventarverwaltungMainFrame), HOME_BUTTON_LOGO);
	add(homeButton, "1,1");
	
	add(createTitleLabel(SEARCH_BY_NAME), "1,3");
	add(getSearchForNameTextField(), "1,5");
	add(createTitleLabel(KATEGORIE), "1,7");
	add(getSearchForKategorieCombobox(), "1,9");
	add(createTitleLabel(PREISSPANNE), "1,11");
	add(createVonBisPanel(), "1,13");
	
	add(new JScrollPane(getItemSetTable()), "3, 1,3,14");
    }
    
    private JComboBox<Kategorie>getSearchForKategorieCombobox() {
	if(kategorieComboBox == null)
	{
	    List<Kategorie> kategorieList = new LoadDataController().loadKategorien(null);
	    Kategorie element = new Kategorie();
	    element.setId(-1);
	    element.setName("--- Alle ---");
	    kategorieList.add(0, element);
	    kategorieComboBox = new JComboBox<Kategorie>(kategorieList.toArray(new Kategorie[kategorieList.size()]));
	    kategorieComboBox.addActionListener(new ActionListener()
	    {
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Kategorie selectedItem = (Kategorie) kategorieComboBox.getSelectedItem();
	            if(selectedItem.getId() == -1)
	            {
	        	getItemSetFilter().setKategorieFilter(null);
	            }
	            else
	            {
	        	getItemSetFilter().setKategorieFilter(selectedItem);
	            }
	        }
	    });
	}
	return kategorieComboBox;
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
	    vonBisFilterButton = new JButton("Preis filter anwenden");
	    vonBisFilterButton.addActionListener(new PreisFilterListener(this, getItemSetFilter()));
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
    
    public JTable getItemSetTable() {
	if(inventarTable == null)
	{
	    inventarTable = new JTable();
	    inventarTable.setModel(getItemSetTableModel());
	    inventarTable.getColumnModel().getColumn(1).setMaxWidth(100);
	    inventarTable.setRowHeight(35);
	    inventarTable.setFont(TABLE_FONT);
	    inventarTable.setIntercellSpacing(TABLE_CELL_INTERSPACING);
	    inventarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    inventarTable.setCellSelectionEnabled(true);
	    inventarTable.setAutoCreateRowSorter(true);
	    inventarTable.addMouseListener(new MouseAdapter()
	    {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2)
		    {
			int index = getItemSetTable().getSelectedRow();
			int realIndex = getItemSetTable().convertRowIndexToModel(index);
			new ItemSetFrame(inventarverwaltungMainFrame, getItemSetTableModel().getItemSet(realIndex));
		    }
		}
	    });
	}
	return inventarTable;
    }
    
    public ItemSetTableModel getItemSetTableModel() {
	if(itemSetTableModel == null)
	{
	    itemSetTableModel = new ItemSetTableModel();
	}
	return itemSetTableModel;
    }
    
    private JLabel createTitleLabel(String searchByName) {
	JLabel label = new JLabel(searchByName);
	return label;
    }
    
    private JTextField getSearchForNameTextField() {
	if(searchForNameTextField == null)
	{
	    searchForNameTextField = new JTextField();
	    searchForNameTextField.getDocument().addDocumentListener(new SearchForNameInItemSetSpellerListener(getItemSetFilter()));
	}
	return searchForNameTextField;
    }
    
    private ItemSetFilter getItemSetFilter() {
	if(itemSetFilter == null)
	{
	    itemSetFilter = new ItemSetFilter(this);
	}
	return itemSetFilter;
    }
    
    private JButton createButton(String string, ActionListener listener, String iconPath) {
	JButton button = new JButton();
	button.setLayout(new BorderLayout());
	button.setForeground(BUTTON_BACKGROUND_COLOR);
	button.setBackground(BUTTON_BACKGROUND_COLOR);
	if(string != null)
	{
	    JLabel label = new JLabel(string);
	    label.setForeground(new Color(231, 30, 50));
	    label.setFont(new Font("button", Font.BOLD, 16));
	    button.add(label, BorderLayout.PAGE_START);
	}
	if(iconPath != null)
	{
	    JLabel iconLabel = new JLabel();
	    iconLabel.setIcon(new ImageIcon(iconPath));
	    button.add(iconLabel, BorderLayout.CENTER);
	}
	
	button.addActionListener(listener);
	return button;
    }
    
    public void activated() {
	getItemSetTableModel().setData(getLoadDataController().loadItemSets());
    }
    
    private LoadDataController getLoadDataController() {
	if(loadDataController == null)
	{
	    loadDataController = new LoadDataController();
	}
	
	return loadDataController;
    }
}
