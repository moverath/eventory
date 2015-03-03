package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import de.overath.cocktailsundco.inventarverwaltung.view.filter.InventarTableFilter;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.InventarPanel;

public class VorratsmengeListener implements ActionListener
{
    private InventarPanel panel;
    private InventarTableFilter inventarTableFilter;
    
    public VorratsmengeListener(InventarPanel panel, InventarTableFilter inventarTableFilter)
    {
	this.panel = panel;
	this.inventarTableFilter = inventarTableFilter;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	float von = Float.parseFloat(panel.getVonNumberTextField().getText().replace(",", "."));
	float bis = Float.parseFloat(panel.getBisNumberTextField().getText().replace(",", "."));

	if(von < 0 )
	{
	    JOptionPane.showMessageDialog(panel, "VON muss positiv sein");
	    return;
	}
	
	if(bis < 0)
	{
	    JOptionPane.showMessageDialog(panel, "BIS muss positiv sein");
	    return;
	}
	
	if(von > bis)
	{
	    JOptionPane.showMessageDialog(panel, "Von (" + von + ") ist größer als bis ("+ bis+"). Bitte trage einen plausiblen Wert für von-bis ein (von < bis)");
	    return;
	}
	
	inventarTableFilter.setVorratsmengeBetween(von, bis);
    }
    
}
