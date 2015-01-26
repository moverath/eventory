package de.overath.cocktailsundco.inventarverwaltung.view.frame;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import layout.TableLayout;
import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.view.TextConstants;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.ISearchForItem;
import de.overath.cocktailsundco.inventarverwaltung.view.model.FilterTableModel;

public class AddItemFrame extends JFrame implements IInventarverwaltungFrame, ActionListener
{
    private static final Dimension DEFAULT_SIZE = new Dimension(400,300);
    private static final Dimension MINIMUM_SIZE = new Dimension(400,300);
    private JFrame createItemsetFrame;
    private JTextField filterTextField;
    private JFormattedTextField anzahlTextField;
    private JTable ergebnisTable;
    private FilterTableModel filterTableModel;
    private ISearchForItem searchForItem;
    private JTextField artikelnumberTextField;
    
    public AddItemFrame(ISearchForItem searchForItem, JFrame createItemsetFrame)
    {
	this.searchForItem = searchForItem;
	this.createItemsetFrame = createItemsetFrame;
	init();
    }
    
    private void init() {
	ImageIcon img = new ImageIcon(INVENTARVERWALTUNG_LOGO);
	setIconImage(img.getImage());
	setTitle(TextConstants.EQUIPMENT_ADD);
	setSize(DEFAULT_SIZE);
	setMinimumSize(MINIMUM_SIZE);
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds(screenSize.width/2 - DEFAULT_SIZE.width/2, screenSize.height/2 - DEFAULT_SIZE.height/2, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
	
	Container contentPane = getContentPane();
	
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] {GAP_BORDER, 80, GAP_COMPONENT, TableLayout.FILL, GAP_BORDER});
	tableLayout.setRow(new double[] {GAP_BORDER, 40, GAP_COMPONENT, TableLayout.FILL, GAP_COMPONENT, 30, GAP_BORDER});
	contentPane.setLayout(tableLayout);
	
	
	
	contentPane.add(getFilterArtikelnumberField(), "1,1");
	contentPane.add(getFilterTextField(), "3,1");
	contentPane.add(getCenterPanel(), "1,3,3,3");
	contentPane.add(createActionPanel(), "1,5,3,5");
	
	setVisible(true);
	toFront();
    }
    
    public JTextField getFilterArtikelnumberField() {
	if(artikelnumberTextField == null)
	{
	    artikelnumberTextField = new JTextField();
	    artikelnumberTextField.getDocument().addDocumentListener(getFilterTableModel());
	}
	return artikelnumberTextField;
    }
    
    private Component getCenterPanel() {
	JPanel panel = new JPanel();
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] {TableLayout.FILL});
	tableLayout.setRow(new double[] {TableLayout.FILL, GAP_COMPONENT, 30});
	panel.setLayout(tableLayout);
	panel.add(new JScrollPane(getErgebnisTable()), "0,0");
	panel.add(createAnzahlPanel(),"0,2,");
	return panel;
    }
    
    private Component createAnzahlPanel() {
	TableLayout tableLayout = new TableLayout();
	tableLayout.setColumn(new double[] {0.6, GAP_COMPONENT, TableLayout.FILL});
	tableLayout.setRow(new double[] {30});
	JPanel panel = new JPanel();
	panel.setLayout(tableLayout);
	panel.add(new JLabel("Anzahl"), "0,0");
	panel.add(getAnzahlTextField(), "2,0");
	return panel;
    }
    
    public JFormattedTextField getAnzahlTextField() {
	if(anzahlTextField == null)
	{
	    NumberFormat format = NumberFormat.getInstance(); 
	    format.setGroupingUsed(false); 
	    NumberFormatter formatter = new NumberFormatter(format); 
	    anzahlTextField = new JFormattedTextField(formatter);
	}
	return anzahlTextField;
    }
    
    public JTable getErgebnisTable() {
	if(ergebnisTable == null)
	{
	    ergebnisTable = new JTable();
	    ergebnisTable.setModel(getFilterTableModel());
	}
	return ergebnisTable;
    }
    
    public FilterTableModel getFilterTableModel() {
	if(filterTableModel == null)
	{
	    filterTableModel = new FilterTableModel(searchForItem.getAlreadyEnteredItems(), this);
	}
	return filterTableModel;
    }
    
    private JPanel createActionPanel() {
	JPanel panel = new JPanel();
	JButton abortButton = new JButton(ABORT_TEXT);
	abortButton.addActionListener(new ActionListener()
	{
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		createItemsetFrame.setEnabled(true);
		AddItemFrame.this.setVisible(false);
	    }
	});
	
	JButton createButton = new JButton(CREATE_TEXT);
	createButton.addActionListener(this);
	
	panel.setLayout(new GridLayout(1, 2, 50, 15));
	panel.add(createButton);
	panel.add(abortButton);
	return panel;
    }
    
    public JTextField getFilterTextField() {
	if(filterTextField == null)
	{
	    filterTextField = new JTextField();
	    filterTextField.getDocument().addDocumentListener(getFilterTableModel());
	}
	return filterTextField;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	int rowCount = getFilterTableModel().getRowCount();
	
	if(getAnzahlTextField().getText().trim().isEmpty())
	{
	    JOptionPane.showMessageDialog(this, "Hey Frank, du musst eine Anzahl eingeben! :-)");
	    return;
	}
	
	Item selectedItem;
	
	if(rowCount == 1)
	{
	    selectedItem = getFilterTableModel().getItem(0);
	}
	else
	{
	    if(getErgebnisTable().getSelectedRow() == -1)
	    {
		JOptionPane.showMessageDialog(this, "Hey Frank, du musst ein Element aus der Liste selektieren :-)");
		return;
	    }
	    else
	    {
		selectedItem = getFilterTableModel().getItem(getErgebnisTable().getSelectedRow());
	    }
	}
	searchForItem.itemSelected(selectedItem, Float.parseFloat(getAnzahlTextField().getText().trim().replace(",", ".")));
	this.setVisible(false);
	createItemsetFrame.setEnabled(true);
	createItemsetFrame.toFront();
    }
    
}
