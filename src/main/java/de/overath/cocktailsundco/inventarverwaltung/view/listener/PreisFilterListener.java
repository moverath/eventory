package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import de.overath.cocktailsundco.inventarverwaltung.view.filter.ItemSetFilter;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.ItemSetPanel;

public class PreisFilterListener implements ActionListener
{
    private ItemSetPanel panel;
    private ItemSetFilter itemSetFilter;
    
    public PreisFilterListener(ItemSetPanel itemSetPanel, ItemSetFilter itemSetFilter)
    {
	this.panel = itemSetPanel;
	this.itemSetFilter = itemSetFilter;
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
	
	itemSetFilter.setPreisFilter(von, bis);
    }
}
