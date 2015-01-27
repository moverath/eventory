package de.overath.cocktailsundco.inventarverwaltung.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import info.clearthought.layout.TableLayout;

import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.IInventarverwaltungFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.InventarverwaltungMainFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.frame.VeranstaltungFrame;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.HomeButtonListener;
import de.overath.cocktailsundco.inventarverwaltung.view.listener.SearchForNameInVeranstaltungSpellerListener;
import de.overath.cocktailsundco.inventarverwaltung.view.model.VeranstaltungTableModel;

public class VeranstaltungPanel extends JPanel implements IInventarverwaltungFrame
{
    private static final int GAP_BORDER = 10;
    private static final int GAP_BUTTON = 5;
    private static final int GAP_TEXT_COMPONENT = 3;
    private static final int BUTTON_HEIGHT = 40;
    private static final int SPELLER_HEIGHT = 25;
    private static final int SPELLER_TEXT_HEIGHT = 15;
    private static final int BUTTON_WIDTH = 215;

    private static final Color BUTTON_BACKGROUND_COLOR = Color.WHITE;
    private static final String HOME_BUTTON_LOGO = "img/homebutton_logo.png";

    private static final String SEARCH_BY_NAME = "Namen suchen";
    private InventarverwaltungMainFrame inventarverwaltungMainFrame;
    private JTextField searchForNameTextField;
    private JTable veranstaltungTable;
    private VeranstaltungTableModel veranstaltungTableModel;
    private LoadDataController loadDataController;

    public VeranstaltungPanel(InventarverwaltungMainFrame inventarverwaltungMainFrame)
    {
	this.inventarverwaltungMainFrame = inventarverwaltungMainFrame;
	init();
    }
    
    private void init() {
	double[][] layoutSize = {
		{ 10, BUTTON_WIDTH, 20, TableLayout.FILL, 10 },
		{ GAP_BORDER, BUTTON_HEIGHT, GAP_BUTTON, SPELLER_TEXT_HEIGHT, GAP_TEXT_COMPONENT, SPELLER_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT, GAP_BUTTON,
		    BUTTON_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT, GAP_BUTTON, BUTTON_HEIGHT, TableLayout.FILL,
		    GAP_BORDER } };
	TableLayout layout = new TableLayout(layoutSize);
	setLayout(layout);
	
	JButton homeButton = createButton(null, new HomeButtonListener(inventarverwaltungMainFrame), HOME_BUTTON_LOGO);
	add(homeButton, "1,1");
	
	add(createTitleLabel(SEARCH_BY_NAME), "1,3");
	add(getSearchForNameTextField(), "1,5");
	
	add(new JScrollPane(getVeranstaltungTable()), "3, 1,3,14");
    }
    
    private JTable getVeranstaltungTable() {
	if(veranstaltungTable == null)
	{
	    veranstaltungTable = new JTable();
	    veranstaltungTable.setModel(getVeranstaltungenTableModel());
	    veranstaltungTable.setRowHeight(35);
	    veranstaltungTable.setFont(TABLE_FONT);
	    veranstaltungTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    veranstaltungTable.setIntercellSpacing(TABLE_CELL_INTERSPACING);
	    veranstaltungTable.setCellSelectionEnabled(true);
	    veranstaltungTable.setAutoCreateRowSorter(true);
	    
	    
	    
	    
	    veranstaltungTable.addMouseListener(new MouseAdapter()
	    {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2)
		    {
			int index = getVeranstaltungTable().getSelectedRow();
			int realIndex = getVeranstaltungTable().convertRowIndexToModel(index);
			new VeranstaltungFrame(inventarverwaltungMainFrame, getVeranstaltungenTableModel().getVeransaltung(realIndex));
		    }
		}
	    });
	}
	return veranstaltungTable;
    }

    private VeranstaltungTableModel getVeranstaltungenTableModel() {
	if(veranstaltungTableModel == null)
	{
	    veranstaltungTableModel = new VeranstaltungTableModel();
	}
	return veranstaltungTableModel;
    }

    private JLabel createTitleLabel(String searchByName) {
	JLabel label = new JLabel(searchByName);
	return label;
    }
    
    private JTextField getSearchForNameTextField() {
	if(searchForNameTextField == null)
	{
	    searchForNameTextField = new JTextField();
	    searchForNameTextField.getDocument().addDocumentListener(new SearchForNameInVeranstaltungSpellerListener(this));
	}
	return searchForNameTextField;
    }
    
    private JButton createButton(String string, ActionListener listener, String iconPath) {
	JButton button = new JButton();
	button.setLayout(new BorderLayout());
	button.setForeground(BUTTON_BACKGROUND_COLOR);
	button.setBackground(BUTTON_BACKGROUND_COLOR);
	if(string != null)
	{
	    JLabel label = new JLabel(string);
	    label.setForeground(new Color(231, 30, 50));
	    label.setFont(new Font("button", Font.BOLD, 16));
	    button.add(label, BorderLayout.PAGE_START);
	}
	if(iconPath != null)
	{
	    JLabel iconLabel = new JLabel();
	    iconLabel.setIcon(new ImageIcon(iconPath));
	    button.add(iconLabel, BorderLayout.CENTER);
	}
	
	button.addActionListener(listener);
	return button;
    }

    public void activated() {
	getVeranstaltungenTableModel().setData(getLoadDataController().loadVeranstaltungen());
    }

    private LoadDataController getLoadDataController() {
	if(loadDataController == null)
	{
	    loadDataController = new LoadDataController();
	}
	
	return loadDataController;
    }
}
