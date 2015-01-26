package de.overath.cocktailsundco.inventarverwaltung.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSetHasItem;
import de.overath.cocktailsundco.inventarverwaltung.view.util.InventarverwaltungUtil;

public class ItemTableModel extends AbstractTableModel
{
    
    public static final int COLUMN_ANZAHL = 0;
    public static final int COLUMN_ITEMNAME = 1;
    public static final int COLUMN_PREIS = 2;
    public static final int COLUMN_COUNT = 3;
    private static final String COLUMN_ITEMNAME_HEADER = "Name";
    private static final String COLUMN_ANZAHL_HEADER = "Anzahl";
    private static final String COLUMN_PREIS_HEADER = "Preis";
    
    private List<ItemSetHasItem> itemSetList = new ArrayList<ItemSetHasItem>();
    
    public void setData(List<ItemSetHasItem> itemSetHasItem)
    {
	itemSetList = itemSetHasItem;
    }

    @Override
    public int getRowCount() {
	return itemSetList.size();
    }
    
    @Override
    public int getColumnCount() {
	return COLUMN_COUNT;
    }
    
    @Override
    public String getColumnName(int column) {
	switch (column)
	{
	    case COLUMN_ITEMNAME:
		return COLUMN_ITEMNAME_HEADER;
	    case COLUMN_ANZAHL:
		return COLUMN_ANZAHL_HEADER;
	    case COLUMN_PREIS:
		return COLUMN_PREIS_HEADER;
	    default:
		return "NO NAME";
	}
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch (columnIndex)
	{
	    case COLUMN_ITEMNAME:
		return String.class;
	    case COLUMN_ANZAHL:
		return Float.class;
	    case COLUMN_PREIS:
		return String.class;
	    default:
		return super.getColumnClass(columnIndex);
	}
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	ItemSetHasItem itemSetHasItem = itemSetList.get(rowIndex);
	switch (columnIndex)
	{
	    case COLUMN_ITEMNAME:
		return itemSetHasItem.getItem().getName();
	    case COLUMN_ANZAHL:
		return itemSetHasItem.getAnzahl();
	    case COLUMN_PREIS:
		return InventarverwaltungUtil.formatPreisForTable(itemSetHasItem.getItem().getPreis());
	    default:
		return "NO DATA";
	}
    }

    public void addItem(Item item, float anzahl) {
	ItemSetHasItem itemSetHasItem = new ItemSetHasItem();
	itemSetHasItem.setItem(item);
	itemSetHasItem.setAnzahl(anzahl);
	itemSetList.add(itemSetHasItem);
	fireTableDataChanged();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	ItemSetHasItem itemSetHasItem = itemSetList.get(rowIndex);
	itemSetHasItem.setAnzahl((float) aValue);
	fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == COLUMN_ANZAHL;
    }

    public List<Item> getItems() {
	System.out.println("getItems was called");
	List<Item> list = new ArrayList<Item>();
	for(ItemSetHasItem item : itemSetList)
	{
	    list.add(item.getItem());
	}
	System.out.println(list);
	return list;
    }

    public List<ItemSetHasItem> getData() {
	return itemSetList;
    }

    public void removeRow(int convertRowIndexToModel) {
	itemSetList.remove(convertRowIndexToModel);
	fireTableDataChanged();
    }
    
}
