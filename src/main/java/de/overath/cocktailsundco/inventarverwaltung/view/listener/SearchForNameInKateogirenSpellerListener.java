package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import de.overath.cocktailsundco.inventarverwaltung.view.filter.KategorieTableFilter;

public class SearchForNameInKateogirenSpellerListener implements DocumentListener
{
    
    private KategorieTableFilter kategorieTableFilter;

    public SearchForNameInKateogirenSpellerListener(KategorieTableFilter kategorieTableFilter)
    {
	this.kategorieTableFilter = kategorieTableFilter;
	// TODO Auto-generated constructor stub
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
	try
	{
	    kategorieTableFilter.setTextFilter(e.getDocument().getText(0, e.getDocument().getLength()));
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
	    kategorieTableFilter.setTextFilter(e.getDocument().getText(0, e.getDocument().getLength()));
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
