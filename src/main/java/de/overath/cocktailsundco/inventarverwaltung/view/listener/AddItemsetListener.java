package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.AddItemsetFrame;

public class AddItemsetListener implements ActionListener
{
    
    private ISearchForItemset searchForItemset;
    private JFrame frame;

    public AddItemsetListener(JFrame frame, ISearchForItemset searchForItemset)
    {
	this.frame = frame;
	this.searchForItemset = searchForItemset;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	frame.setEnabled(false);
	AddItemsetFrame addItemsetFrame = new AddItemsetFrame(frame, searchForItemset);
	addItemsetFrame.addWindowListener(new WindowAdapter()
	{
	    @Override
	    public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		frame.setEnabled(true);
		frame.toFront();
	    }
	});;
	
    }
    
}
