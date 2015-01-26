package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class ShowVeranstaltungListener implements ActionListener
{
    
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;

    public ShowVeranstaltungListener(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	inventarverwaltungMainFrame.getTabPane().setSelectedComponent(inventarverwaltungMainFrame.getVeranstaltungPanel());
    }
    
}
