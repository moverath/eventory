package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.overath.cocktailsundco.inventarverwaltung.view.panel.VeranstaltungPanel;

public class SearchForNameInVeranstaltungSpellerListener implements DocumentListener
{
    
    private VeranstaltungPanel veranstaltungPanel;

    public SearchForNameInVeranstaltungSpellerListener(VeranstaltungPanel veranstaltungPanel)
    {
	this.veranstaltungPanel = veranstaltungPanel;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
    }
    
}
