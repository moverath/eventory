package de.overath.cocktailsundco.inventarverwaltung.view.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.NumberFormatter;

import info.clearthought.layout.TableLayout;

import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.controller.PersistenceController;
import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSetHasItem;
import de.overath.cocktailsundco.inventarverwaltung.model.Kategorie;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.AddItemListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.ISearchForItem;
import de.overath.cocktailsundco.inventarverwaltung.view.model.ItemTableModel;

public class ItemSetFrame extends JFrame implements IInventarverwaltungFrame, ActionListener, ISearchForItem
{
    private static final String TITLE_TEXT = "Equipment set anlegen";
    private static final Dimension MINIMUM_SIZE = new Dimension(400, 525);
    private static final Dimension DEFAULT_SIZE = new Dimension(400, 525);
    private static final String ADD_ITEM_BUTTON_TEXT = "Equipment hinzuf�gen";
    private static final double SIZE_TABLE = 200;
    private static final String DELETE = "Delete";
    private InventarverwaltungMainFrame mainFrame;
    private JTextField nameTextField;
    private JTextArea descriptionTextArea;
    private JButton addItemButton;
    private JTable itemTable;
    private JComboBox<Kategorie> kategorieComboBox;
    private ItemTableModel itemTableModel;
    private JFormattedTextField priceTextField;
    private ItemSet itemSet;
    
    public ItemSetFrame(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.mainFrame = inventarverwaltungMainFrame;
	init();
    }
    
    public ItemSetFrame(InventarverwaltungMainFrame inventarverwaltungMainFrame, ItemSet itemSet)
    {
	this.mainFrame = inventarverwaltungMainFrame;
	init();
	fillValues(itemSet);
	this.itemSet = itemSet;
    }

    private void fillValues(ItemSet itemSet) {
	if(itemSet == null )
	{
	    System.out.println("ItemSetFrame#fillValues - ItemSet is null");
	    return;
	}
	if(itemSet.getName() != null);
	{
	    getNameTextField().setText(itemSet.getName());
	}
	
	if(itemSet.getBeschreibung() != null)
	{
	    getDesciptionTextArea().setText(itemSet.getBeschreibung());
	}
	
	if(itemSet.getItemSet() != null)
	{
	    List<ItemSetHasItem> list = new ArrayList<ItemSetHasItem>();
	    for(ItemSetHasItem hasItem: itemSet.getItemSet())
	    {
		list.add(hasItem);
	    }
	    getItemTableModel().setData(list);
	}

	if(itemSet.getPreis() > 0)
	{
	    getPriceTextField().setText(itemSet.getPreis()+"");
	}
	
	getKategorieComboBox().setSelectedItem(itemSet.getKategorie());
    }

    private void init() {
	ImageIcon img = new ImageIcon(INVENTARVERWALTUNG_LOGO);
	setIconImage(img.getImage());
	setTitle(TITLE_TEXT);
	setSize(DEFAULT_SIZE);
	setMinimumSize(MINIMUM_SIZE);
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds(screenSize.width / 2 - DEFAULT_SIZE.width / 2, screenSize.height / 2 - DEFAULT_SIZE.height / 2,
		DEFAULT_SIZE.width, DEFAULT_SIZE.height);
	
	addWindowListener(new WindowAdapter()
	{
	    @Override
	    public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		mainFrame.setEnabled(true);
		mainFrame.toFront();
	    }
	});
	mainFrame.setEnabled(false);
	
	add(createActionPanel(), BorderLayout.PAGE_END);
	add(createInsertArea(), BorderLayout.CENTER);
	// load all available items from database
	setVisible(true);
	toFront();
    }
    
    private JPanel createInsertArea() {
	double[][] size = {
		{ GAP_BORDER, TableLayout.FILL, GAP_COMPONENT, SIZE_TEXT_AREA, GAP_BORDER },
		{ GAP_BORDER, SIZE_NAME_LABEL, GAP_COMPONENT, SIZE_CHECKBOX, GAP_COMPONENT, SIZE_TEXT_AREA,
			GAP_COMPONENT, SIZE_NAME_LABEL, GAP_COMPONENT, SIZE_TABLE, GAP_BORDER } };
	TableLayout layout = new TableLayout(size);
	JPanel panel = new JPanel();
	panel.setLayout(layout);
	panel.add(getNameTextField(), "1, 1,3,1 ");
	
	panel.add(createKategoriePreisPanel(), "1,3,3,3");
	panel.add(new JScrollPane(getDesciptionTextArea()), "1,5,3,5");
	panel.add(getAddItemButton(), "1,7,3,7");
	panel.add(new JScrollPane(getItemTable()), "1,9,3,9");
	return panel;
    }
    
    private Component createKategoriePreisPanel() {
	JPanel panel = new JPanel();
	
	TableLayout layout = new TableLayout();
	layout.setColumn(new double[] {55, GAP_COMPONENT, 60,GAP_COMPONENT, 60, GAP_COMPONENT, TableLayout.FILL});
	layout.setRow(new double[] {TableLayout.FILL});
	
	panel.setLayout(layout);
	
	panel.add(new JLabel("Preis [�]:"), "0,0");
	panel.add(getPriceTextField(), "2,0");
	panel.add(new JLabel("Kategorie:"), "4,0");
	panel.add(getKategorieComboBox(), "6,0");
	return panel;
    }
    
    private JTextField getPriceTextField()
    {
	if(priceTextField == null)
	{
	    NumberFormat format = NumberFormat.getInstance(); 
	    format.setGroupingUsed(false); 
	    NumberFormatter formatter = new NumberFormatter(format); 
	    priceTextField = new JFormattedTextField(formatter);
	}
	return priceTextField;
    }

    public JComboBox<Kategorie> getKategorieComboBox() {
	if(kategorieComboBox == null)
	{
	    List<Kategorie> kategorieList = new LoadDataController().loadKategorien(null);
	    kategorieComboBox = new JComboBox<Kategorie>(kategorieList.toArray(new Kategorie[kategorieList.size()]));
	}
	return kategorieComboBox;
    }

    private JButton getAddItemButton() {
	if (addItemButton == null)
	{
	    addItemButton = new JButton();
	    addItemButton.setText(ADD_ITEM_BUTTON_TEXT);
	    addItemButton.addActionListener(new AddItemListener(this, this) );
	}
	return addItemButton;
    }
    
    JTable getItemTable() {
	if (itemTable == null)
	{
	    itemTable = new JTable();
	    itemTable.setModel(getItemTableModel());
	 // assume JTable is named "table"
	    int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
	    InputMap inputMap = itemTable.getInputMap(condition);
	    ActionMap actionMap = itemTable.getActionMap();

	    // DELETE is a String constant that for me was defined as "Delete"
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE);
	    actionMap.put(DELETE, new AbstractAction() {
	       public void actionPerformed(ActionEvent e) {
		   getItemTableModel().removeRow(itemTable.convertRowIndexToModel(itemTable.getSelectedRow()));
	       }
	    });
	}
	return itemTable;
    }
    
    public ItemTableModel getItemTableModel() {
	if(itemTableModel == null)
	{
	    itemTableModel = new ItemTableModel();
	}
	return itemTableModel;
    }

    private JTextArea getDesciptionTextArea() {
	if (descriptionTextArea == null)
	{
	    descriptionTextArea = new JTextArea();
	    descriptionTextArea.setLineWrap(true);
	    descriptionTextArea.setToolTipText("Beschreibung f�r das neue EquipmentSet");
	}
	return descriptionTextArea;
    }
    
    private JTextField getNameTextField() {
	if (nameTextField == null)
	{
	    nameTextField = new JTextField();
	    nameTextField.setToolTipText("Name eingeben");
	}
	return nameTextField;
    }
    
    private JPanel createActionPanel() {
	JPanel panel = new JPanel();
	JButton abortButton = new JButton(ABORT_TEXT);
	abortButton.addActionListener(new ActionListener()
	{
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mainFrame.setEnabled(true);
		ItemSetFrame.this.setVisible(false);
		mainFrame.toFront();
	    }
	});
	
	JButton createButton = new JButton(CREATE_TEXT);
	createButton.addActionListener(this);
	
	panel.setLayout(new GridLayout(1, 2, 50, 15));
	panel.add(createButton);
	panel.add(abortButton);
	return panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	//TODO PlausiCheck
	boolean persistNew = false;
	if(itemSet == null)
	{
	    persistNew = true;
	    itemSet = new ItemSet();
	}
	itemSet.setBeschreibung(getDesciptionTextArea().getText().trim());
	itemSet.setKategorie(getKategorieComboBox().getItemAt(getKategorieComboBox().getSelectedIndex()));
	itemSet.setName(getNameTextField().getText().trim());
	itemSet.setPreis(Float.parseFloat(getPriceTextField().getText()));
	List<ItemSetHasItem> data = getItemTableModel().getData();
	Set<ItemSetHasItem> itemSetToAdd = new HashSet<ItemSetHasItem>();
	for(ItemSetHasItem ishi : data)
	{
	    ishi.setItemSet(itemSet);
	    itemSetToAdd.add(ishi);
	}
	itemSet.setItemSet(itemSetToAdd);
	if(persistNew)
	{
	    new PersistenceController().persistNewItemSet(itemSet);
	}
	else
	{
	    new PersistenceController().mergeItemSet(itemSet);
	}
	
	mainFrame.setEnabled(true);
	this.setVisible(false);
	mainFrame.toFront();
    }

    @Override
    public List<Item> getAlreadyEnteredItems() {
	return getItemTableModel().getItems();
    }

    @Override
    public void itemSelected(Item selectedItem, float parseFloat) {
	getItemTableModel().addItem(selectedItem, parseFloat);
    }
}
