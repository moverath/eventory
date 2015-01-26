package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.KategorieFrame;

public class CreateKategorieListener implements ActionListener
{

    private InventarverwaltungMainFrame inventarverwaltungMainFrame;

    public CreateKategorieListener(InventarverwaltungMainFrame mainFrame)
    {
	this.inventarverwaltungMainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	KategorieFrame createItemFrame = new KategorieFrame(inventarverwaltungMainFrame);
    }
    
}
