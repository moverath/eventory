package de.overath.cocktailsundco.inventarverwaltung.controller;

import java.util.HashMap;
import java.util.Map;

import de.overath.cocktailsundco.inventarverwaltung.model.Inventar;
import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.model.Veranstaltung;

public class VeranstaltungsLagerValidierungsController
{
    
    private final Map<Item, Float> missingItemMap;
    
    public VeranstaltungsLagerValidierungsController()
    {
	missingItemMap = new HashMap<Item, Float>();
    }
    
    /**
     * Checks if the given {@link Inventar} has all items available that are needed for the given {@link Veranstaltung}
     * 
     * If all Items are available true will be returned.
     * 
     * If a Item is not available a Map with {@link Item} as key and the missing count as value will be returned by calling getMissingItemList()
     * 
     * @param inventar
     * @param veranstaltung
     * @return true if all items are available false if not.
     */
    public boolean checkInventarForVeranstaltung(Inventar inventar, Veranstaltung veranstaltung)
    {
	Map<Item, Float> itemMap = createMapForItems(veranstaltung);
	
	return true;
    }
    
    private Map<Item, Float> createMapForItems(Veranstaltung veranstaltung) {
	
	
	
	return null;
    }

    /**
     * @return will return the results of missing Icons from calling {@link VeranstaltungsLagerValidierungsController#checkInventarForVeranstaltung(Inventar, VeranstaltungsLagerValidierungsController)}
     */
    public Map<Item, Float> getMissingItemList()
    {
	return missingItemMap;
    }
}
