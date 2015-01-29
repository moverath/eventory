package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class ShowKategorieListener implements ActionListener
{
    private InventarverwaltungMainFrame mainFrame;
    
    public ShowKategorieListener(InventarverwaltungMainFrame mainFrame)
    {
	this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	mainFrame.getTabPane().setSelectedComponent(mainFrame.getKategoriePanel());
    }

}
