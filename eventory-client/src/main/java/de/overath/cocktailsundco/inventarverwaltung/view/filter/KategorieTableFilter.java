package de.overath.cocktailsundco.inventarverwaltung.view.filter;

import java.util.List;

import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.model.Kategorie;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.KategoriePanel;

public class KategorieTableFilter
{
    private String text = null;
    private LoadDataController loadDataController;
    private KategoriePanel kategoriePanel;

    public KategorieTableFilter(KategoriePanel kategoriePanel)
    {
	this.kategoriePanel = kategoriePanel;
	loadDataController = new LoadDataController();
    }

    public void setTextFilter(String text) {
	this.text = text;
	filterUpdated();
    }

    private void filterUpdated()
    {
	//TODO loaddata
	List<Kategorie> loadVerbrauchsartikel = loadDataController.loadKategorien(text);
	kategoriePanel.getKategorieTableModel().setData(loadVerbrauchsartikel);
	
	kategoriePanel.getKategorieTable().invalidate();
	kategoriePanel.getKategorieTable().repaint();
	kategoriePanel.invalidate();
	kategoriePanel.repaint();;
    }
}
