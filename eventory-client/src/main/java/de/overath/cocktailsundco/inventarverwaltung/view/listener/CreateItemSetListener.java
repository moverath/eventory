package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.ItemSetFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class CreateItemSetListener implements ActionListener
{
    
    private InventarverwaltungMainFrame mainFrame;

    public CreateItemSetListener(InventarverwaltungMainFrame mainFrame)
    {
	this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	ItemSetFrame createItemFrame = new ItemSetFrame(mainFrame);
    }
    
}
