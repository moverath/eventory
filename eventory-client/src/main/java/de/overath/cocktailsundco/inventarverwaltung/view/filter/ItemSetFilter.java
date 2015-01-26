package de.overath.cocktailsundco.inventarverwaltung.view.filter;

import java.util.List;

import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;
import de.overath.cocktailsundco.inventarverwaltung.model.Kategorie;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.ItemSetPanel;

public class ItemSetFilter
{

    private ItemSetPanel itemSetPanel;
    private String text = null;
    private LoadDataController loadDataController;
    private float von;
    private float bis;
    private Kategorie kategorie;

    public ItemSetFilter(ItemSetPanel itemSetPanel)
    {
	this.itemSetPanel = itemSetPanel;
	loadDataController = new LoadDataController();
    }
    
    public void setTextFilter(String text) {
	this.text = text;
	filterUpdated();
    }
    
    public void setPreisFilter(float von, float bis)
    {
	this.von = von;
	this.bis = bis;
	filterUpdated();
    }
    
    public void setKategorieFilter(Kategorie kategorie)
    {
	this.kategorie = kategorie;
	filterUpdated();
    }
    
    public void reset()
    {
	von = 0;
	bis = 0;
	text = null;
	kategorie = null;
	filterUpdated();
    }

    private void filterUpdated()
    {
	//TODO loaddata
	List<ItemSet> loadVerbrauchsartikel = loadDataController.loadItemSets(text, null, kategorie, von, bis);
	itemSetPanel.getItemSetTableModel().setData(loadVerbrauchsartikel);
	
	itemSetPanel.getItemSetTable().invalidate();
	itemSetPanel.getItemSetTable().repaint();
	itemSetPanel.invalidate();
	itemSetPanel.repaint();;
    }
    
    
}
