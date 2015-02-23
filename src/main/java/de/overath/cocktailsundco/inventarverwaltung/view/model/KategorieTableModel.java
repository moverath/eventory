package de.overath.cocktailsundco.inventarverwaltung.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.overath.cocktailsundco.inventarverwaltung.model.Kategorie;

public class KategorieTableModel extends AbstractTableModel
{
    private final static int COLUMN_NAME = 0;
    private final static int COLUMN_BESCHREIBUNG = 1;
    private final static int COLUMN_COUNT = 2;

    private final static String COLUMN_NAME_HEADER = "Name";
    private final static String COLUMN_BESCHREIBUNG_HEADER = "Beschreibung";
    
    private List<Kategorie> kategorien = new ArrayList<Kategorie>();

    @Override
    public int getRowCount() {
	return kategorien.size();
    }
    
    @Override
    public String getColumnName(int column) {
	switch (column)
	{
	    case COLUMN_NAME:
		return COLUMN_NAME_HEADER;
	    case COLUMN_BESCHREIBUNG:
		return COLUMN_BESCHREIBUNG_HEADER;
	    default:
		return "NO DATA";
	}
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Kategorie kategorie = kategorien.get(rowIndex);
	switch (columnIndex)
	{
	    case COLUMN_NAME:
		return kategorie.getName();
	    case COLUMN_BESCHREIBUNG:
		return kategorie.getBeschreibung();
	    default:
		return "NO DATA";
	}
    }
    
    @Override
    public int getColumnCount() {
	return COLUMN_COUNT;
    }
    
    public void setData(List<Kategorie> loadKategorien) {
	this.kategorien = loadKategorien;
	fireTableDataChanged();
    }

    public Kategorie getKategorie(int realIndex) {
	return kategorien.get(realIndex);
    }
    
}
