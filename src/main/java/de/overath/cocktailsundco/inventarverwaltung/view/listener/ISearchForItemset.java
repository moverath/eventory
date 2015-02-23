package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.util.List;

import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;

public interface ISearchForItemset
{
    List<ItemSet> getAlreadyEnteredItemset();

    void itemsetSelected(ItemSet selectedItem, float parseFloat);
}
