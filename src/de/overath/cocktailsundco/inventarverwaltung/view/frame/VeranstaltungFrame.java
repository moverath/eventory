package de.overath.cocktailsundco.inventarverwaltung.view.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import layout.TableLayout;

import com.toedter.calendar.JDateChooser;

import de.overath.cocktailsundco.inventarverwaltung.controller.PersistenceController;
import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSetHasItem;
import de.overath.cocktailsundco.inventarverwaltung.model.Veranstaltung;
import de.overath.cocktailsundco.inventarverwaltung.model.VeranstaltungHasItem;
import de.overath.cocktailsundco.inventarverwaltung.model.VeranstaltungHasItemSet;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.AddItemListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.AddItemsetListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.ISearchForItem;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.ISearchForItemset;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.PrintVeranstaltungListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.RemoveItemListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.RemoveItemsetListener;
import de.overath.cocktailsundco.inventarverwaltung.view.model.ItemTableModel;
import de.overath.cocktailsundco.inventarverwaltung.view.model.VeranstaltungHasItemSetTableModel;

public class VeranstaltungFrame extends JFrame implements IInventarverwaltungFrame, ActionListener, ISearchForItem, ISearchForItemset
{
    private static final int TOP_PANEL_SIZE_ROW_HEIGHT = 30;
    private static final int ACTION_PANEL_SIZE_ROW_HEIGHT = 30;
    private static final double NAME_LABEL_WIDTH = 50;
    private static final double GAESTE_ANZAHL_LABEL_WIDTH = 110;
    private static final double NAME_WIDTH = TableLayout.FILL;
    private static final int GAESTE_ANZAHL_WIDTH = 80;
    private static final int CALENDAR_WIDTH = 120;
    private static final int CALENDAR_END_LABEL_WIDTH = 50;
    private static final int CALENDAR_BEGIN_LABEL_WIDTH = 60;
    private static final String TITLE_TEXT = "Veranstaltung erstellen";
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
    private static final Dimension MINIMUM_SIZE = new Dimension(800, 400);
    private static final String LABEL_NAME_STRING = "Name:";
    private static final String LABEL_GAESTE_ANZAHL_STRING = "Anzahl Gäste:";
    private static final String LABEL_BEGIN_STRING = "Beginn:";
    private static final String LABEL_END_STRING = "Ende:";
    private static final String LABEL_BESCHREIBUNG_STRING = "Beschreibung";
    private static final String DATEI_AUSWAEHLEN = "Logo laden..";
    private static final String ADD_ITEMSET_STRING = "Equipmentset hinzufügen";
    private static final String REMOVE_ITEMSET_STRING = "Equipmentset entfernen";
    private static final String ADD_ITEM_STRING = "Item hinzufügen";
    private static final String REMOVE_ITEM_STRING = "Item entfernen";
    private static final String LABEL_FEEDBACK_STRING = "Feedback";
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;
    private Veranstaltung veranstaltung;
    private JTextField nameTextField;
    private JTextField gaesteAnzahlTextField;
    private JDateChooser beginCalendar;
    private JDateChooser endCalendar;
    private JTextArea descriptionTextArea;
    private JLabel imageLabel;
    private VeranstaltungHasItemSetTableModel itemSetTableModel;
    private JTable itemSetTable;
    private JTable itemTable;
    private ItemTableModel itemTableModel;
    private JTextArea feedbackTextArea;
    
    public VeranstaltungFrame(InventarverwaltungMainFrame inventarverwaltungMainFrame, Veranstaltung veranstaltung)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
	this.veranstaltung = veranstaltung;
	init();
	fillValuesFromVeranstaltung();
    }
    
    private void fillValuesFromVeranstaltung() {
	
	if(veranstaltung.getName() != null && !veranstaltung.getName().isEmpty())
	{
	    getNameTextField().setText(veranstaltung.getName());
	}
	
	
    }

    public VeranstaltungFrame(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
	this.veranstaltung = null;
	init();
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
		inventarverwaltungMainFrame.setEnabled(true);
	    }
	});
	inventarverwaltungMainFrame.setEnabled(false);
	
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] { GAP_BORDER, TableLayout.FILL, GAP_BORDER });
	tableLayout.setRow(new double[] { GAP_BORDER, TOP_PANEL_SIZE_ROW_HEIGHT, GAP_COMPONENT, TableLayout.FILL,
		GAP_COMPONENT, ACTION_PANEL_SIZE_ROW_HEIGHT, GAP_BORDER });
	setLayout(tableLayout);
	
	add(getTopPanel(), "1,1");
	add(getCenterPanel(), "1,3");
	add(createActionPanel(), "1,5");
	
	setVisible(true);
	toFront();
    }
    
    private JPanel getTopPanel() {
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] {NAME_LABEL_WIDTH, GAP_COMPONENT, NAME_WIDTH, GAP_COMPONENT,
		GAESTE_ANZAHL_LABEL_WIDTH, GAP_COMPONENT, GAESTE_ANZAHL_WIDTH, GAP_COMPONENT,
		CALENDAR_BEGIN_LABEL_WIDTH, GAP_COMPONENT, CALENDAR_WIDTH, GAP_COMPONENT, CALENDAR_END_LABEL_WIDTH,
		GAP_COMPONENT, CALENDAR_WIDTH});
	tableLayout.setRow(new double[] { TableLayout.FILL });
	
	JPanel topPanel = new JPanel();
	topPanel.setLayout(tableLayout);
	topPanel.add(createLabel(LABEL_NAME_STRING), "0,0");
	topPanel.add(getNameTextField(), "2,0");
	topPanel.add(createLabel(LABEL_GAESTE_ANZAHL_STRING), "4,0");
	topPanel.add(getGaesteAnzahlTextField(), "6,0");
	topPanel.add(createLabel(LABEL_BEGIN_STRING), "8,0");
	topPanel.add(getBeginCalendar(), "10,0");
	topPanel.add(createLabel(LABEL_END_STRING), "12,0");
	topPanel.add(getEndCalendar(), "14,0");
	
	return topPanel;
    }
    
    private JDateChooser getEndCalendar() {
	if(endCalendar == null)
	{
	    endCalendar = new JDateChooser();
	}
	return endCalendar;
    }
    
    private JDateChooser getBeginCalendar() {
	if(beginCalendar == null)
	{
	    beginCalendar = new JDateChooser();
	}
	return beginCalendar;
    }
    
    private JTextField getGaesteAnzahlTextField() {
	if (gaesteAnzahlTextField == null)
	{
	    gaesteAnzahlTextField = new JTextField();
	}
	return gaesteAnzahlTextField;
    }
    
    private JTextField getNameTextField() {
	if (nameTextField == null)
	{
	    nameTextField = new JTextField();
	}
	return nameTextField;
    }
    
    private Component createLabel(String labelNameString) {
	JLabel label = new JLabel(labelNameString);
	label.setFont(LABLE_FONT);
	return label;
    }
    
    private JPanel getCenterPanel() {
	JPanel centerPanel = new JPanel();
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] { TableLayout.FILL, GAP_COMPONENT ,TableLayout.FILL});
	tableLayout.setRow(new double[] { TableLayout.FILL });
	
	centerPanel.setLayout(tableLayout);
	centerPanel.add(createLeftCenterPanel(), "0,0");
	centerPanel.add(createRightCenterPanel(), "2,0");
	
	return centerPanel;
    }
    
    private Component createRightCenterPanel() {
	JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	splitPane.setTopComponent(getItemSetPanel());
	splitPane.setBottomComponent(getItemPanel());
	splitPane.setDividerLocation(0.5);
	splitPane.setDividerLocation((int)(DEFAULT_SIZE.getHeight() - TOP_PANEL_SIZE_ROW_HEIGHT) / 2);
	return splitPane;
    }
    
    private Component getItemPanel() {
	JPanel panel = new JPanel();
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] { TableLayout.FILL, GAP_COMPONENT, TableLayout.FILL});
	tableLayout.setRow(new double[] { 30, GAP_COMPONENT, TableLayout.FILL });
	panel.setLayout(tableLayout);
	
	panel.add(createButton(ADD_ITEM_STRING, new AddItemListener(this, this)), "0,0");
	panel.add(createButton(REMOVE_ITEM_STRING, new RemoveItemListener(getItemTableModel())), "2,0");
	
	panel.add(new JScrollPane(getItemTable()), "0,2,2,2");
	
	return panel;
    }
    
    private ItemTableModel getItemTableModel() {
	if(itemTableModel == null)
	{
	    itemTableModel = new ItemTableModel();
	}
	return itemTableModel;
    }

    private VeranstaltungHasItemSetTableModel getItemSetTableModel() {
	if(itemSetTableModel == null)
	{
	    itemSetTableModel = new VeranstaltungHasItemSetTableModel();
	}
	return itemSetTableModel;
    }
    
    private Component createButton(String string, ActionListener listener) {
	JButton button = new JButton();
	button.setText(string);
	button.addActionListener(listener);
	return button;
    }
    
    private Component getItemSetPanel() {
	JPanel panel = new JPanel();
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] { TableLayout.FILL, GAP_COMPONENT, TableLayout.FILL});
	tableLayout.setRow(new double[] { 30, GAP_COMPONENT, TableLayout.FILL });
	panel.setLayout(tableLayout);
	
	panel.add(createButton(ADD_ITEMSET_STRING, new AddItemsetListener(this, this)), "0,0");
	panel.add(createButton(REMOVE_ITEMSET_STRING, new RemoveItemsetListener(getItemSetTableModel())), "2,0");
	
	panel.add(new JScrollPane(getItemsetTable()), "0,2,2,2");
	
	return panel;
    }
    
    private JTable getItemTable() {
	if(itemTable == null)
	{
	    itemTable = new JTable();
	    itemTable.setModel(getItemTableModel());
	    itemTable.getColumnModel().getColumn(0).setMaxWidth(70);
	    itemTable.setRowHeight(35);
	    itemTable.setFont(TABLE_FONT);
	    itemTable.setIntercellSpacing(TABLE_CELL_INTERSPACING);
	    itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    itemTable.setCellSelectionEnabled(true);
	    itemTable.setAutoCreateRowSorter(true);
	}
	return itemTable;
    }

    private JTable getItemsetTable() {
	if(itemSetTable == null)
	{
	    itemSetTable = new JTable();
	    itemSetTable.setModel(getItemSetTableModel());
	    itemSetTable.getColumnModel().getColumn(0).setMaxWidth(70);
	    itemSetTable.setRowHeight(35);
	    itemSetTable.setFont(TABLE_FONT);
	    itemSetTable.setIntercellSpacing(TABLE_CELL_INTERSPACING);
	    itemSetTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    itemSetTable.setCellSelectionEnabled(true);
	    itemSetTable.setAutoCreateRowSorter(true);
	}
	return itemSetTable;
    }
    
    private Component createLeftCenterPanel() {
	JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	splitPane.setTopComponent(new JScrollPane(getBeschreibungPanel()));
	splitPane.setBottomComponent(new JScrollPane(getFeedbackPanel()));
	splitPane.setDividerLocation(0.5);
	splitPane.setDividerLocation((int)(DEFAULT_SIZE.getHeight() - TOP_PANEL_SIZE_ROW_HEIGHT) / 2);
	return splitPane;
    }
    
    private Component getFeedbackPanel() {
	JPanel panel = new JPanel();
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] { TableLayout.FILL});
	tableLayout.setRow(new double[] { 30, GAP_COMPONENT, TableLayout.FILL });
	panel.setLayout(tableLayout);
	panel.add(createLabel(LABEL_FEEDBACK_STRING), "0,0");
	panel.add(new JScrollPane(getFeedBackTextArea()), "0,2");
	return panel;
    }

    private JTextArea getFeedBackTextArea() {
	if(feedbackTextArea == null)
	{
	    feedbackTextArea = new JTextArea();
	}
	return feedbackTextArea
		;
    }

    private Component getBeschreibungPanel() {
	JPanel panel = new JPanel();
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] { TableLayout.FILL});
	tableLayout.setRow(new double[] { 30, GAP_COMPONENT, TableLayout.FILL });
	panel.setLayout(tableLayout);
	panel.add(createLabel(LABEL_BESCHREIBUNG_STRING), "0,0");
	panel.add(new JScrollPane(getDescriptionTextArea()), "0,2");
	return panel;
    }
    
    private JTextArea getDescriptionTextArea() {
	if (descriptionTextArea == null)
	{
	    descriptionTextArea = new JTextArea();
	    descriptionTextArea.setLineWrap(true);
	    descriptionTextArea.setBorder(new LineBorder(Color.black));
	    descriptionTextArea.setToolTipText("Beschreibung für die neue Veranstaltung");
	}
	return descriptionTextArea;
    }
    
    private JPanel createActionPanel() {
	JPanel panel = new JPanel();
	JButton abortButton = new JButton(ABORT_TEXT);
	abortButton.addActionListener(new ActionListener()
	{
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		inventarverwaltungMainFrame.setEnabled(true);
		VeranstaltungFrame.this.setVisible(false);
		inventarverwaltungMainFrame.toFront();
	    }
	});
	
	JButton createButton = new JButton(CREATE_TEXT);
	createButton.addActionListener(this);
	
	panel.setLayout(new GridLayout(1, 2, 50, 15));
	panel.add(createButton);
	
	if (veranstaltung != null)
	{
	    JButton printButton = new JButton(PRINT_TEXT);
	    createButton.addActionListener(new PrintVeranstaltungListener(this));
	    panel.add(printButton);
	}
	
	panel.add(abortButton);
	return panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	if(veranstaltung == null)
	{
	    veranstaltung = new Veranstaltung();
	}
	
	veranstaltung.setName(getNameTextField().getText().trim());
	veranstaltung.setBeschreibung(getDescriptionTextArea().getText().trim());
	veranstaltung.setAnzahl(Float.parseFloat(getGaesteAnzahlTextField().getText()));
	
	veranstaltung.setDatumBeginn(getBeginCalendar().getDate());
	veranstaltung.setDatumEnde(getEndCalendar().getDate());
	veranstaltung.setItemSet(extractItemSet());
	veranstaltung.setFeedbackText(getFeedBackTextArea().getText());
	veranstaltung.setVeranstaltungHasItemSet(extractVeranstaltungHasItemSet());
	
	new PersistenceController().persistVeranstaltung(veranstaltung);
	
	this.setVisible(false);
	inventarverwaltungMainFrame.setEnabled(true);
	inventarverwaltungMainFrame.toFront();
    }

    private Set<VeranstaltungHasItemSet> extractVeranstaltungHasItemSet() {
	List<VeranstaltungHasItemSet> itemsets = getItemSetTableModel().getData();
	Set<VeranstaltungHasItemSet> set = new HashSet<VeranstaltungHasItemSet>();
	for(VeranstaltungHasItemSet itemSet : itemsets)
	{
	    set.add(itemSet);
	}
	return set;
    }

    private Set<VeranstaltungHasItem> extractItemSet() {
	List<ItemSetHasItem> itemsets = getItemTableModel().getData();
	Set<VeranstaltungHasItem> set = new HashSet<VeranstaltungHasItem>();
	for(ItemSetHasItem itemSet : itemsets)
	{
	    VeranstaltungHasItem vhi = new VeranstaltungHasItem();
	    vhi.setAnzahl(itemSet.getAnzahl());
	    vhi.setItem(itemSet.getItem());
	    set.add(vhi);
	}
	return set;
    }

    @Override
    public List<Item> getAlreadyEnteredItems() {
	return getItemTableModel().getItems();
    }

    @Override
    public void itemSelected(Item selectedItem, float parseFloat) {
	getItemTableModel().addItem(selectedItem, parseFloat);
    }

    @Override
    public void itemsetSelected(ItemSet selectedItem, float parseFloat) {
	getItemSetTableModel().addItemSet(selectedItem, parseFloat);
    }

    @Override
    public List<ItemSet> getAlreadyEnteredItemset() {
	return getItemSetTableModel().getItemsets();
    }

}
