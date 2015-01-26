package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.AddItemFrame;

public class AddItemListener implements ActionListener
{
    
    private JFrame createItemsetFrame;
    private AddItemFrame addItemFrame;
    private ISearchForItem searchForItem;

    public AddItemListener(JFrame createItemsetFrame, ISearchForItem searchForItem)
    {
	this.createItemsetFrame = createItemsetFrame;
	this.searchForItem = searchForItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	createItemsetFrame.setEnabled(false);
	addItemFrame = new AddItemFrame(searchForItem, createItemsetFrame);
	addItemFrame.addWindowListener(new WindowAdapter()
	{
	    @Override
	    public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		addItemFrame.setVisible(false);
		createItemsetFrame.setEnabled(true);
		createItemsetFrame.toFront();
	    }
	});
    }
    
}
