package de.overath.cocktailsundco.inventarverwaltung.view.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import info.clearthought.layout.TableLayout;

import de.overath.cocktailsundco.inventarverwaltung.controller.PersistenceController;
import de.overath.cocktailsundco.inventarverwaltung.model.Kategorie;

public class KategorieFrame extends JFrame implements IInventarverwaltungFrame, ActionListener
{
    private static final Dimension MINIMUM_SIZE = new Dimension(300,200);
    private static final Dimension DEFAULT_SIZE = new Dimension(300,200);
    
    private static final String TITLE_TEXT = "Neue Kategorie anlegen";
    private InventarverwaltungMainFrame mainFrame;
    private JPanel insertArea;
    private Kategorie kategorie;
    
    public KategorieFrame(InventarverwaltungMainFrame mainFrame)
    {
	this.mainFrame = mainFrame;
	init();
    }
    
    public KategorieFrame(InventarverwaltungMainFrame mainFrame, Kategorie kategorie)
    {
	this.mainFrame = mainFrame;
	this.kategorie = kategorie;
	init();
	fillValues(kategorie);
    }
    
    private void fillValues(Kategorie kategorie) {
	if(kategorie == null)
	{
	    return;
	}
	
	if(kategorie.getName() != null)
	{
	    getNameTextField().setText(kategorie.getName());
	}
	
	if(kategorie.getBeschreibung() != null)
	{
	    getDesciptionTextArea().setText(kategorie.getBeschreibung());
	}
    }
    
    private void init() {
	
	ImageIcon img = new ImageIcon(INVENTARVERWALTUNG_LOGO);
	setIconImage(img.getImage());
	setTitle(TITLE_TEXT);
	insertArea = createInsertArea();
	getContentPane().add(insertArea, BorderLayout.CENTER);
	getContentPane().add(createActionPanel(), BorderLayout.PAGE_END);
	setAlwaysOnTop(true);
	setSize(DEFAULT_SIZE);
	setMinimumSize(MINIMUM_SIZE);
	setVisible(true);
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds(screenSize.width/2 - DEFAULT_SIZE.width/2, screenSize.height/2 - DEFAULT_SIZE.height/2, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
	
	addWindowListener(new WindowAdapter()
	{
	    @Override
	    public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		mainFrame.setEnabled(true);
	    }
	});
	mainFrame.setEnabled(false);
	
    }
    private JTextArea descriptionTextArea;
    private JTextField nameTextField;
    
    private JPanel createInsertArea() {
	double[][] size = {
		{GAP_BORDER, TableLayout.FILL,GAP_BORDER},
		{GAP_BORDER, SIZE_NAME_LABEL, GAP_COMPONENT, TableLayout.FILL, GAP_BORDER}};
	TableLayout layout = new TableLayout(size);
	JPanel panel = new JPanel();
	panel.setLayout(layout);
	panel.add(getNameTextField(), "1,1");
	panel.add(new JScrollPane(getDesciptionTextArea()), "1,3");
	return panel;
    }
    
    private JTextField getNameTextField() {
	if(nameTextField == null)
	{
	    nameTextField = new JTextField();
	    nameTextField.setToolTipText("Name eingeben");
	}
	return nameTextField;
    }
    
    private JTextArea getDesciptionTextArea() {
	if(descriptionTextArea == null)
	{
	    descriptionTextArea = new JTextArea();
	    descriptionTextArea.setLineWrap(true);
	}
	return descriptionTextArea;
    }
    
    private JPanel createActionPanel() {
	JPanel panel = new JPanel();
	JButton abortButton = new JButton(ABORT_TEXT);
	abortButton.addActionListener(new ActionListener()
	{
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mainFrame.setEnabled(true);
		KategorieFrame.this.setVisible(false);
	    }
	});
	
	JButton createButton = new JButton(CREATE_TEXT);
	createButton.addActionListener(this);
	
	
	panel.setLayout(new GridLayout(1, 2, 50, 15));
	panel.add(createButton);
	panel.add(abortButton);
	return panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	
	boolean persistNew = false;
	if(this.kategorie == null)
	{
	    persistNew = true;
	    kategorie = new Kategorie();
	}
	kategorie.setName(getNameTextField().getText().trim());
	kategorie.setBeschreibung(getDesciptionTextArea().getText().trim());
	
	if(persistNew)
	{
	    new PersistenceController().persistNewKategorie(kategorie);
	}
	else
	{
	    new PersistenceController().mergeKategorie(kategorie);
	}
	
	this.setVisible(false);
	mainFrame.setEnabled(true);
	mainFrame.toFront();
    }
}
