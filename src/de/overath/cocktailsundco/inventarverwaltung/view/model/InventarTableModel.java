package de.overath.cocktailsundco.inventarverwaltung.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import de.overath.cocktailsundco.inventarverwaltung.beispiele.MissingIcon;
import de.overath.cocktailsundco.inventarverwaltung.controller.PersistenceController;
import de.overath.cocktailsundco.inventarverwaltung.model.InventarHasItem;

public class InventarTableModel extends AbstractTableModel
{
    
    public static final int COLUMN_ANZAHL = 0;
    public static final int COLUMN_ARTIKELNUMMER = 1;
    public static final int COLUMN_NAME = 2;
    public static final int COLUMN_ICON = 3;
    public static final int COLUMN_COUNT = 4;
    
    public static final String COLUMN_ANZAHL_NAME = "Vorrätig";
    public static final String COLUMN_ARTIKELNUMMER_NAME = "Art.Nr";
    public static final String COLUMN_NAME_NAME = "Name";
    public static final String COLUMN_ICON_NAME = "Bild";
    public static final String NO_NAME_DEFINED =  "Kein name definiert";
    
    private volatile List<InventarHasItem> itemList = new ArrayList<InventarHasItem>();
    
    public synchronized void setData(List<InventarHasItem> dataSet)
    {
	itemList = dataSet;
	fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
	return itemList.size();
    }
    
    @Override
    public int getColumnCount() {
	return COLUMN_COUNT;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
	switch(columnIndex)
	{
	    case COLUMN_ANZAHL:
		return COLUMN_ANZAHL_NAME;
	    case COLUMN_NAME:
		return COLUMN_NAME_NAME;
	    case COLUMN_ARTIKELNUMMER:
		return COLUMN_ARTIKELNUMMER_NAME;
	    case COLUMN_ICON:
		return COLUMN_ICON_NAME;
	    default:
		return NO_NAME_DEFINED;
	}
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch(columnIndex)
	{
	    case COLUMN_ANZAHL:
		return Float.class;
	    case COLUMN_ARTIKELNUMMER:
		return String.class;
	    case COLUMN_NAME:
		return String.class;
	    case COLUMN_ICON:
		return Icon.class;
	    default:
		return String.class;
	}
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return false;
    }
    
    @Override
    public synchronized Object getValueAt(int rowIndex, int columnIndex) {
	InventarHasItem inventarHasItem = itemList.get(rowIndex);
	switch(columnIndex)
	{
	    case COLUMN_ANZAHL:
		return inventarHasItem.getAnzahl();
	    case COLUMN_ARTIKELNUMMER:
		return inventarHasItem.getItem().getArtikelnummer();
	    case COLUMN_NAME:
		return inventarHasItem.getItem().getName();
	    case COLUMN_ICON:
		if(inventarHasItem.getItem().getBildKlein() != null)
		{
		    return new ImageIcon(inventarHasItem.getItem().getBildKlein());
		}
		else if(inventarHasItem.getItem().getBildOriginal() != null)
		{
		    return new ImageIcon(inventarHasItem.getItem().getBildOriginal());
		}
		else
		{
		    return new MissingIcon();
		}
	    default:
		return "NO DATA";
	}
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	InventarHasItem inventarHasItem = itemList.get(rowIndex);
	if(columnIndex == COLUMN_ANZAHL)
	{
	    inventarHasItem.setAnzahl((float) aValue);
	    PersistenceController persistenceController = new PersistenceController();
	    persistenceController.mergeInventarHasItem(inventarHasItem);
	    fireTableCellUpdated(rowIndex, columnIndex);
	}
    }
    
    @Override
    public void addTableModelListener(TableModelListener l) {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public void removeTableModelListener(TableModelListener l) {
	// TODO Auto-generated method stub
	
    }

    public InventarHasItem getItem(int selectedRow) {
	return itemList.get(selectedRow);
    }
    
}
