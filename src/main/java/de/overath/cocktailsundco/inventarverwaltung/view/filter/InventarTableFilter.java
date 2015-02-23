package de.overath.cocktailsundco.inventarverwaltung.view.filter;

import java.util.List;

import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.model.InventarHasItem;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.InventarPanel;

public class InventarTableFilter
{

    private String text = null;
    private InventarPanel inventarPanel;
    private String artikelnummer = null;
    private float min = 0;
    private float max = 100;
    private LoadDataController loadDataController;
    private boolean verbrauchsartikel;

    public InventarTableFilter(InventarPanel inventarPanel, boolean verbrauchsartikel)
    {
	this.inventarPanel = inventarPanel;
	this.verbrauchsartikel = verbrauchsartikel;
	loadDataController = new LoadDataController();
	
	//TODO find max from table
    }

    public void setTextFilter(String text) {
	this.text = text;
	filterUpdated();
    }

    public void setArtikelnummerFilter(String artikelnummer) {
	this.artikelnummer = artikelnummer;
	filterUpdated();
    }

    public void setVorratsmengeBetween(float min, float max)
    {
	this.min = min;
	this.max = max;
	filterUpdated();
    }
    
    private void filterUpdated()
    {
	//TODO loaddata
	int mode = verbrauchsartikel ? LoadDataController.ONLY_VERBRAUCHSARTIKEL : LoadDataController.ONLY_EQUIPMENT;
	List<InventarHasItem> loadVerbrauchsartikel = loadDataController.loadItems(text, artikelnummer, mode, min, max);
	inventarPanel.getInventarTableModel().setData(loadVerbrauchsartikel);
	inventarPanel.getInventarTable().invalidate();
	inventarPanel.getInventarTable().repaint();
	inventarPanel.invalidate();
	inventarPanel.repaint();;
    }
}
