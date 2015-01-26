package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sun.istack.logging.Logger;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class ShowItemListener implements ActionListener
{

    private final Logger log = Logger.getLogger(ShowItemListener.class);
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;

    public ShowItemListener(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	inventarverwaltungMainFrame.getTabPane().setSelectedComponent(inventarverwaltungMainFrame.getInventarPanel());
    }
    
}
