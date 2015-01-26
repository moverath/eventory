package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.VeranstaltungFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class CreateVeranstaltungListener implements ActionListener
{
    
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;

    public CreateVeranstaltungListener(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	VeranstaltungFrame createVeranstaltungFrame = new VeranstaltungFrame(inventarverwaltungMainFrame);
    }
    
}
