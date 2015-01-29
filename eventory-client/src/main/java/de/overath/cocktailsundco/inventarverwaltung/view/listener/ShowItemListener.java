package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;

public class ShowItemListener implements ActionListener
{

    private static Log logger = LogFactory.getLog(ShowItemListener.class);
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
