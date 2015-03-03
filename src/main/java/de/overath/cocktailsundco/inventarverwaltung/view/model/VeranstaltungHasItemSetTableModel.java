package de.overath.cocktailsundco.inventarverwaltung.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;
import de.overath.cocktailsundco.inventarverwaltung.model.VeranstaltungHasItemSet;

public class VeranstaltungHasItemSetTableModel extends AbstractTableModel
{
    public static final int COLUMN_ANZAHL = 0;
    public static final int COLUMN_ITEMNAME = 1;
    public static final int COLUMN_COUNT = 2;
    
    private static final String COLUMN_ITEMNAME_HEADER = "Name";
    private static final String COLUMN_ANZAHL_HEADER = "Anzahl";
    
    private List<VeranstaltungHasItemSet> itemSetList = new ArrayList<VeranstaltungHasItemSet>();
    
    @Override
    public int getRowCount() {
	return itemSetList.size();
    }
    
    @Override
    public int getColumnCount() {
	return 2;
    }
    
    @Override
    public String getColumnName(int column) {
	switch (column)
	{
	    case COLUMN_ITEMNAME:
		return COLUMN_ITEMNAME_HEADER;
	    case COLUMN_ANZAHL:
		return COLUMN_ANZAHL_HEADER;
	    default:
		return "NO NAME";
	}
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	VeranstaltungHasItemSet itemSetHasItem = itemSetList.get(rowIndex);
	switch (columnIndex)
	{
	    case COLUMN_ITEMNAME:
		return itemSetHasItem.getItemSet().getName();
	    case COLUMN_ANZAHL:
		return itemSetHasItem.getAnzahl();
	    default:
		return "NO DATA";
	}
    }

    public List<VeranstaltungHasItemSet> getData() {
	return itemSetList;
    }

    public void addItemSet(ItemSet selectedItem, float parseFloat) {
	VeranstaltungHasItemSet itemSetHasItem = new VeranstaltungHasItemSet();
	itemSetHasItem.setItemSet(selectedItem);
	itemSetHasItem.setAnzahl(parseFloat);
	
	itemSetList.add(itemSetHasItem);
	
	fireTableDataChanged();
    }

    public List<ItemSet> getItemsets() {
	System.out.println("getItems was called");
	List<ItemSet> list = new ArrayList<ItemSet>();
	for(VeranstaltungHasItemSet item : itemSetList)
	{
	    list.add(item.getItemSet());
	}
	return list;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	VeranstaltungHasItemSet veranstaltungHasItemSet = itemSetList.get(rowIndex);
	veranstaltungHasItemSet.setAnzahl(Float.parseFloat((String) aValue));
	fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == COLUMN_ANZAHL;
    }

}
