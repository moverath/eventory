package de.overath.cocktailsundco.inventarverwaltung.view.panel;

import de.overath.cocktailsundco.inventarverwaltung.view.filter.InventarTableFilter;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class VerbrauchsartikelPanel extends InventarPanel
{

    public VerbrauchsartikelPanel(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	super(inventarverwaltungMainFrame);
    }
    
    protected InventarTableFilter getInventarTableFilter() {
	if(inventarTableFilter == null)
	{
	    inventarTableFilter = new InventarTableFilter(this, true);
	}
	return inventarTableFilter;
    }
}
