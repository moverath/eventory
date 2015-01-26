package de.overath.cocktailsundco.inventarverwaltung.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.overath.cocktailsundco.inventarverwaltung.model.Veranstaltung;
import de.overath.cocktailsundco.inventarverwaltung.view.util.InventarverwaltungUtil;

public class VeranstaltungTableModel extends AbstractTableModel
{

    public static final int COLUMN_NAME = 0;
    public static final int COLUMN_BESCHREIBUNG = 1;
    public static final int COLUMN_DATUM_BEGIN = 2;
    public static final int COLUMN_DATUM_ENDE = 3;
    public static final int COLUMN_GAESTE_ANZAHL = 4;
    public static final int COLUMN_COUNT = 5;

    public static final String COLUMN_NAME_HEADER = "Name";
    public static final String COLUMN_BESCHREIBUNG_HEADER= "Beschreibung";
    public static final String COLUMN_DATUM_BEGIN_HEADER = "Begin";
    public static final String COLUMN_DATUM_ENDE_HEADER = "Ende";
    public static final String COLUMN_GAESTE_ANZAHL_HEADER = "Gaeste anzahl";
    
    private List<Veranstaltung> veranstaltungen = new ArrayList<Veranstaltung>();

    @Override
    public int getRowCount() {
	return veranstaltungen.size();
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
	    case COLUMN_BESCHREIBUNG:
		return COLUMN_BESCHREIBUNG_HEADER;
	    case COLUMN_DATUM_BEGIN:
		return COLUMN_DATUM_BEGIN_HEADER;
	    case COLUMN_DATUM_ENDE:
		return COLUMN_DATUM_ENDE_HEADER;
	    case COLUMN_GAESTE_ANZAHL:
		return COLUMN_GAESTE_ANZAHL_HEADER;
	    default:
		return "NO DATA";
	}
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Veranstaltung veranstaltung = veranstaltungen.get(rowIndex);
	switch (columnIndex)
	{
	    case COLUMN_NAME:
		return veranstaltung.getName();
	    case COLUMN_BESCHREIBUNG:
		return veranstaltung.getBeschreibung();
	    case COLUMN_DATUM_BEGIN:
		return InventarverwaltungUtil.formatDate(veranstaltung.getDatumBeginn());
	    case COLUMN_DATUM_ENDE:
		return InventarverwaltungUtil.formatDate(veranstaltung.getDatumEnde());
	    case COLUMN_GAESTE_ANZAHL:
		return veranstaltung.getAnzahl();
	    default:
		return "NO DATA";
	}
    }

    public void setData(List<Veranstaltung>  loadVeranstaltungen) {
	this.veranstaltungen = loadVeranstaltungen;
	fireTableDataChanged();
    }

    public Veranstaltung getVeransaltung(int realIndex) {
	return veranstaltungen.get(realIndex);
    }
    
}
