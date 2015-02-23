package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.ItemFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class CreateItemListener implements ActionListener
{
    
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;

    public CreateItemListener(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	//TODO show a popup frame where a new Item can be created think about a close action
	ItemFrame createItemFrame = new ItemFrame(inventarverwaltungMainFrame);
    }
    
}
