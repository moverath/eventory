package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import de.overath.cocktailsundco.inventarverwaltung.view.filter.ImageFilter;

public class LoadPictureListener implements ActionListener
{
    
    private IPictureListener createItemFrame;

    public LoadPictureListener(IPictureListener createItemFrame)
    {
	this.createItemFrame = createItemFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	fileChooser.setAcceptAllFileFilterUsed(false);
	fileChooser.addChoosableFileFilter(new ImageFilter());

	int showOpenDialog = fileChooser.showOpenDialog((Component) e.getSource());
	
	if(showOpenDialog == JFileChooser.APPROVE_OPTION)
	{
	    createItemFrame.setPicture(fileChooser.getSelectedFile());
	}
    }
    
}
