package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.HomePanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.InventarPanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.ItemSetPanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.KategoriePanel;
import de.overath.cocktailsundco.inventarverwaltung.view.panel.VeranstaltungPanel;

public class TabChangeListener implements ChangeListener
{
    
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;

    public TabChangeListener(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;

    }

    /*
     * TODO
     * Beim wechsel der tabs kann sich die toolbar anpassen
     * itemliste + für neues item etc
     */
    
    @Override
    public void stateChanged(ChangeEvent e) {
	Object source = e.getSource();
	if(source instanceof JTabbedPane)
	{
	    JTabbedPane tabPane = (JTabbedPane) source;
	    
	    if(tabPane.getSelectedComponent() instanceof HomePanel)
	    {
		homePanelActivated((HomePanel)tabPane.getSelectedComponent());
	    }
	    else if(tabPane.getSelectedComponent() instanceof InventarPanel)
	    {
		inventarPanelActivated((InventarPanel)tabPane.getSelectedComponent());
	    }
	    else if(tabPane.getSelectedComponent() instanceof ItemSetPanel)
	    {
		itemSetPanelActivated((ItemSetPanel)tabPane.getSelectedComponent());
	    }
	    else if(tabPane.getSelectedComponent() instanceof KategoriePanel)
	    {
		kategoriePanelActivated((KategoriePanel)tabPane.getSelectedComponent());
	    }
	    else if(tabPane.getSelectedComponent() instanceof VeranstaltungPanel)
	    {
		veranstaltungPanelActivated((VeranstaltungPanel)tabPane.getSelectedComponent());
	    }
	}
    }

    private void veranstaltungPanelActivated(VeranstaltungPanel selectedComponent) {
	selectedComponent.activated();
    }

    private void kategoriePanelActivated(KategoriePanel selectedComponent) {
	selectedComponent.activated();
    }

    private void itemSetPanelActivated(ItemSetPanel selectedComponent) {
	selectedComponent.activated();
    }

    private void homePanelActivated(HomePanel homePanel) {
	homePanel.activated();
    }

    private void inventarPanelActivated(InventarPanel inventarPanel) {
	inventarPanel.activated();
    }
    
}
