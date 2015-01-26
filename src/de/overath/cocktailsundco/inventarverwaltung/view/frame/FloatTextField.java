package de.overath.cocktailsundco.inventarverwaltung.view.frame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FloatTextField extends JTextField implements DocumentListener
{
    String currentText = "";
    
    public FloatTextField()
    {
	getDocument().addDocumentListener(this);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
	check();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
	check();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
	check();
    }

    private void check() {
	String text = getText();
	Pattern pattern = Pattern.compile("[a-zA-Z]");
	Matcher m = pattern.matcher(text);
	if(m.matches())
	{
	    System.out.println("match");
	    setText(currentText);
	}
	else
	{
	    System.out.println("no match");
	    currentText = text;
	}
    }

}
