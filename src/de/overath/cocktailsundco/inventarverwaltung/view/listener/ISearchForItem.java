package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.util.List;

import de.overath.cocktailsundco.inventarverwaltung.model.Item;

public interface ISearchForItem
{

    List<Item> getAlreadyEnteredItems();

    void itemSelected(Item selectedItem, float parseFloat);
    
}
