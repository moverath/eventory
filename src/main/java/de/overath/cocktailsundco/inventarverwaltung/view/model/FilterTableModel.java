package de.overath.cocktailsundco.inventarverwaltung.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.AddItemFrame;

public class FilterTableModel extends AbstractTableModel implements DocumentListener
{
    
    public final static int COLUMN_ARTIKELNUMMER = 0;
    public final static int COLUMN_NAME = 1;
    public final static int COLUMN_COUNT = 2;
    private static final String COLUM_ARTIKELNUMMER_HEADER = "Art. Nr";
    private static final String COLUM_NAME_HEADER = "Name";
    
    private List<Item> alreadyEnteredItems;
    private List<Item> items;
    private LoadDataController loadDataController;
    private AddItemFrame addItemFrame;
    
    public FilterTableModel(List<Item> alreadyEnteredItems, AddItemFrame addItemFrame)
    {
	this.alreadyEnteredItems = alreadyEnteredItems;
	this.addItemFrame = addItemFrame;
	items = new ArrayList<Item>();
	setData(getLoadDataController().loadItems(null, null, alreadyEnteredItems, LoadDataController.BOTH));
    }
    
    
    public void setData(List<Item> items) {
	this.items = items;
	fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
	return items.size();
    }
    
    @Override
    public int getColumnCount() {
	return COLUMN_COUNT;
    }
    
    @Override
    public String getColumnName(int column) {
	switch (column)
	{
	    case COLUMN_ARTIKELNUMMER:
		return COLUM_ARTIKELNUMMER_HEADER;
	    case COLUMN_NAME:
		return COLUM_NAME_HEADER;
	    default:
		return "NO DATA";
	}
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	
	switch (columnIndex)
	{
	    case COLUMN_ARTIKELNUMMER:
		return items.get(rowIndex).getArtikelnummer();
	    case COLUMN_NAME:
		return items.get(rowIndex).getName();
	    default:
		return items.get(rowIndex);
	}
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
	loadData();
    }
    
    private LoadDataController getLoadDataController() {
	if(loadDataController == null)
	{
	    loadDataController = new LoadDataController();
	}
	return loadDataController;
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
	loadData();
    }
    
    private void loadData() {
	String artikellnummer = addItemFrame.getFilterArtikelnumberField().getText();
	String name = addItemFrame.getFilterTextField().getText();
	List<Item> loadItems = getLoadDataController().loadItems(artikellnummer, name, alreadyEnteredItems, LoadDataController.BOTH);
	setData(loadItems);
    }


    @Override
    public void changedUpdate(DocumentEvent e) {
    }
    
    public Item getItem(int i) {
	return items.get(i);
    }
    
    
    public void setAlreadyEnteredItems(List<Item> alreadyEnteredItems) {
	this.alreadyEnteredItems = alreadyEnteredItems;
    }
}
