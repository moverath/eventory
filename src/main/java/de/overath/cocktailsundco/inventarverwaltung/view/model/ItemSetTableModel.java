package de.overath.cocktailsundco.inventarverwaltung.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;
import de.overath.cocktailsundco.inventarverwaltung.view.util.InventarverwaltungUtil;

public class ItemSetTableModel extends AbstractTableModel
{
    
    private final static int COLUMN_NAME = 0;
    private final static int COLUMN_KATEGORIE = 1;
    private final static int COLUMN_BESCHREIBUNG = 2;
    private final static int COLUMN_PREIS = 3;
    private final static int COLUMN_COUNT = 4;

    private final static String COLUMN_NAME_HEADER = "Name";
    private final static String COLUMN_KATEGORIE_HEADER = "Kategorie";
    private final static String COLUMN_BESCHREIBUNG_HEADER = "Beschreibung";
    private final static String COLUMN_PREIS_HEADER = "Preis";

    private List<ItemSet> itemSets;

    public ItemSetTableModel()
    {
	itemSets = new ArrayList<ItemSet>();
    }
    
    public void setData(List<ItemSet> itemSets)
    {
	this.itemSets = itemSets;
	fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
	return itemSets.size();
    }

    @Override
    public int getColumnCount() {
	return COLUMN_COUNT;
    }
    
    @Override
    public String getColumnName(int column) {
	switch (column)
	{
	    case COLUMN_NAME:
		return COLUMN_NAME_HEADER;
	    case COLUMN_KATEGORIE:
		return COLUMN_KATEGORIE_HEADER;
	    case COLUMN_BESCHREIBUNG:
		return COLUMN_BESCHREIBUNG_HEADER;
	    case COLUMN_PREIS:
		return COLUMN_PREIS_HEADER;
	    default:
		return "NO DATA";
	}
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	ItemSet itemSet = itemSets.get(rowIndex);
	switch (columnIndex)
	{
	    case COLUMN_NAME:
		return itemSet.getName();
	    case COLUMN_KATEGORIE:
		return itemSet.getKategorie();
	    case COLUMN_BESCHREIBUNG:
		return itemSet.getBeschreibung();
	    case COLUMN_PREIS:
		return InventarverwaltungUtil.formatPreisForTable(itemSet.getPreis());
	    default:
		return "NO DATA";
	}
    }

    public ItemSet getItemSet(int realIndex) {
	return itemSets.get(realIndex);
    }
    
}
