package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class ShowItemSetListener implements ActionListener
{
    
    private InventarverwaltungMainFrame mainFrame;

    public ShowItemSetListener(InventarverwaltungMainFrame mainFrame)
    {
	this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	mainFrame.getTabPane().setSelectedComponent(mainFrame.getItemSetPanel());
    }
    
}
