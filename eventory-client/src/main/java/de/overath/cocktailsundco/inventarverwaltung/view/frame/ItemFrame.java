package de.overath.cocktailsundco.inventarverwaltung.view.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import layout.TableLayout;
import de.overath.cocktailsundco.inventarverwaltung.controller.PersistenceController;
import de.overath.cocktailsundco.inventarverwaltung.model.InventarHasItem;
import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.view.TextConstants;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.IPictureListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.LoadPictureListener;
import de.overath.cocktailsundco.inventarverwaltung.view.util.InventarverwaltungUtil;

public class ItemFrame extends JFrame implements ActionListener, IInventarverwaltungFrame, IPictureListener
{
    private static final int NAME_LABEL_WIDTH = 70;
    private static final Dimension MINIMUM_SIZE = new Dimension(400,380);
    private static final Dimension DEFAULT_SIZE = new Dimension(400,380);
    
    private static final String TITLE_TEXT = "Neues Equipment anlegen";
    private static final String CHECKBOX_VERBRAUCHSARTIKEL = "Verbrauchsartikel";
    private InventarverwaltungMainFrame mainFrame;
    private JPanel insertArea;
    private Item item = null;
    private static final String DATEI_AUSWAEHLEN = "Bild laden..";
    private byte[] extractBytes;
    private JTextField nameTextField;
    private JTextField artikelnummerTextField;
    private JTextArea descriptionTextArea;
    private JLabel imageLabel;
    private byte[] extractBytesSmall;
    private JCheckBox checkBoxVerbrauchsartikel;
    private JTextField priceTextField;
    private InventarHasItem inventarHasItem;
    
    public ItemFrame(InventarverwaltungMainFrame mainFrame)
    {
	this.mainFrame = mainFrame;
	init();
    }

    public ItemFrame(InventarverwaltungMainFrame mainFrame, InventarHasItem inventarHasItem)
    {
	this.mainFrame = mainFrame;
	this.inventarHasItem = inventarHasItem;
	this.item = inventarHasItem.getItem();
	init();
	fillValuesFromItem(this.item);
    }
    
    private void fillValuesFromItem(Item item) {
	//NAME
	if(item.getName() != null && !item.getName().isEmpty())
	{
	    getNameTextField().setText(item.getName());
	}
	
	//DESCRIPTION
	if(item.getBeschreibung() != null && !item.getBeschreibung().isEmpty())
	{
	    getDesciptionTextArea().setText(item.getBeschreibung());
	}
	
	getPriceTextField().setText(item.getPreis()+"");
	getArtikelTextField().setText(item.getArtikelnummer());
	getCheckBoxVerbrauchsartikel().setSelected(item.isVerbrauchsartikel());
	
	//IMAGE
	extractBytes = item.getBildOriginal();
	extractBytesSmall = item.getBildKlein();
	if(extractBytes != null)
	{
	    getImageLabel().setIcon(new ImageIcon(new ImageIcon(extractBytes).getImage().getScaledInstance(getImageLabel().getWidth(), getImageLabel().getHeight(), Image.SCALE_SMOOTH)));
	}
	
	//
    }

    private void init() {
	
	ImageIcon img = new ImageIcon(INVENTARVERWALTUNG_LOGO);
	setIconImage(img.getImage());
	setTitle(TITLE_TEXT);
	insertArea = createInsertArea();
	getContentPane().add(insertArea, BorderLayout.CENTER);
	getContentPane().add(createActionPanel(), BorderLayout.PAGE_END);
	setSize(DEFAULT_SIZE);
	setMinimumSize(MINIMUM_SIZE);
	
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
	setVisible(true);
	toFront();
	
    }
    
    private JPanel createInsertArea() {
	double[][] size = {
		{GAP_BORDER, TableLayout.FILL, GAP_COMPONENT, TableLayout.FILL, GAP_BORDER},
		{GAP_BORDER, SIZE_NAME_LABEL, GAP_COMPONENT, SIZE_NAME_LABEL, GAP_COMPONENT, SIZE_CHECKBOX, GAP_COMPONENT, TableLayout.FILL, GAP_BORDER}};
	TableLayout layout = new TableLayout(size);
	JPanel panel = new JPanel();
	panel.setLayout(layout);
	panel.add(createNamePanel(), "1, 1, 3,1");
	panel.add(getArtikelPricePanel(), "1,3,3,3");
	panel.add(getCheckBoxVerbrauchsartikel(), "1,5");
	panel.add(createFileChooser(), "3,5");
	panel.add(new JScrollPane(getDesciptionTextArea()), "1,7");
	panel.add(getImageLabel(), "3,7");
	return panel;
    }
    
    private Component createNamePanel() {
	double[][] size = {
		{NAME_LABEL_WIDTH, GAP_COMPONENT, TableLayout.FILL},
		{TableLayout.FILL}};
	TableLayout layout = new TableLayout(size);
	JPanel panel = new JPanel();
	panel.setLayout(layout);
	panel.add(new JLabel(TextConstants.NAME_LABEL), "0,0");
	panel.add(getNameTextField(), "2,0");
	
	return panel;
    }

    private Component getArtikelPricePanel() {
	JPanel panel = new JPanel();
	TableLayout mgr = new TableLayout();
	mgr.setColumn(new double[] {NAME_LABEL_WIDTH, GAP_COMPONENT, TableLayout.FILL, GAP_COMPONENT, 60, GAP_COMPONENT, 70});
	mgr.setRow(new double[] {TableLayout.FILL});
	panel.setLayout(mgr);
	
	panel.add(new JLabel(TextConstants.ARTIKELNUMMER), "0,0");
	panel.add(getArtikelTextField(), "2,0");
	panel.add(new JLabel("Preis [€]"), "4,0");
	panel.add(getPriceTextField(), "6,0");
	return panel;
    }

    private JTextField getArtikelTextField() {
	if(artikelnummerTextField == null)
	{
	    artikelnummerTextField = new JTextField();
	}
	return artikelnummerTextField;
    }

    private JTextField getPriceTextField()
    {
	if(priceTextField == null)
	{
	    NumberFormat format = NumberFormat.getInstance(); 
	    format.setGroupingUsed(false); 
	    NumberFormatter formatter = new NumberFormatter(format); 
	    priceTextField = new JFormattedTextField(formatter);
	}
	return priceTextField;
    }
    
    private JLabel getImageLabel() {
	if(imageLabel == null)
	{
	    imageLabel = new JLabel();
	}
	return imageLabel;
    }

    private Component createFileChooser() {
	
	JButton fileChooser = new JButton();
	fileChooser.setText(DATEI_AUSWAEHLEN);
	fileChooser.addActionListener(new LoadPictureListener(this));
	return fileChooser;
    }
    
    private JCheckBox getCheckBoxVerbrauchsartikel() {
	if(checkBoxVerbrauchsartikel == null)
	{
	    checkBoxVerbrauchsartikel = new JCheckBox(CHECKBOX_VERBRAUCHSARTIKEL);
	}
	return checkBoxVerbrauchsartikel;
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
    
    //    private JLabel createTitle() {
    //	JLabel label = new JLabel(TITLE_TEXT);
    //	label.setForeground(new Color(231, 30, 50));
    //	label.setFont(new Font("button", Font.BOLD, 16));
    //	return label;
    //    }
    
    private JPanel createActionPanel() {
	JPanel panel = new JPanel();
	JButton abortButton = new JButton(ABORT_TEXT);
	abortButton.addActionListener(new ActionListener()
	{
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mainFrame.setEnabled(true);
		ItemFrame.this.setVisible(false);
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
	//gather info for Item
	int mode = PersistenceController.MERGE;
	if(this.inventarHasItem == null)
	{
	    mode = PersistenceController.PERSIST;
	    inventarHasItem = new InventarHasItem();
	    inventarHasItem.setAnzahl(0);
	    item = new Item();
	    item.setName(nameTextField.getText().trim());
	    item.setBeschreibung(descriptionTextArea.getText().trim());
	    item.setBildOriginal(extractBytes);
	    item.setBildKlein(extractBytesSmall);
	    item.setPreis(Float.parseFloat(getPriceTextField().getText().replace(",", ".")));
	    item.setVerbrauchsartikel(getCheckBoxVerbrauchsartikel().isSelected());
	    item.setArtikelnummer(getArtikelTextField().getText());
	    inventarHasItem.setItem(item);
	}

	else
	{
	    mode = PersistenceController.MERGE;
	    item = inventarHasItem.getItem();
	    item.setName(nameTextField.getText().trim());
	    item.setBeschreibung(descriptionTextArea.getText().trim());
	    item.setBildOriginal(extractBytes);
	    item.setBildKlein(extractBytesSmall);
	    item.setPreis(Float.parseFloat(getPriceTextField().getText().replace(",", ".")));
	    item.setArtikelnummer(getArtikelTextField().getText());
	    item.setVerbrauchsartikel(getCheckBoxVerbrauchsartikel().isSelected());
	}
	
	new PersistenceController().persistNewItem(inventarHasItem, mode, inventarHasItem.getAnzahl());
	mainFrame.setEnabled(true);
	ItemFrame.this.setVisible(false);
    }
    
    public void setPicture(File selectedFile) {
	if(insertArea != null)
	{
	    extractBytes = InventarverwaltungUtil.createByteFromImage(selectedFile);
	    InventarverwaltungUtil.createImageForLabel(selectedFile.getAbsolutePath(), getImageLabel());
	    extractBytesSmall = InventarverwaltungUtil.createThumbnail(selectedFile.getAbsolutePath(), THUMBNAIL_SIZE.getWidth(), THUMBNAIL_SIZE.getHeight());
	    validate();
	    repaint();
	}
    }
}
