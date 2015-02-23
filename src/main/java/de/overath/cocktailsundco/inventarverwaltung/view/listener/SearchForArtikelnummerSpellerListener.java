package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import de.overath.cocktailsundco.inventarverwaltung.view.filter.InventarTableFilter;

public class SearchForArtikelnummerSpellerListener implements DocumentListener
{
    private InventarTableFilter inventarTableFilter;

    public SearchForArtikelnummerSpellerListener(InventarTableFilter inventarTableFilter)
    {
	this.inventarTableFilter = inventarTableFilter;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
	try
	{
	    inventarTableFilter.setArtikelnummerFilter(e.getDocument().getText(0, e.getDocument().getLength()));
	} catch (BadLocationException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
	try
	{
	    inventarTableFilter.setArtikelnummerFilter(e.getDocument().getText(0, e.getDocument().getLength()));
	} catch (BadLocationException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
    
    }
    
}
