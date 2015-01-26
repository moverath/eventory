package de.overath.cocktailsundco.inventarverwaltung.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;

import de.overath.cocktailsundco.inventarverwaltung.controller.LoadDataController;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;

public class FilterItemSetTableModel extends AbstractTableModel implements DocumentListener
{
    private List<ItemSet> alreadyEnteredItemset;
    private List<ItemSet> items;
    private LoadDataController loadDataController;

    public FilterItemSetTableModel(List<ItemSet> alreadyEnteredItemset)
    {
	this.alreadyEnteredItemset = alreadyEnteredItemset;
	items = new ArrayList<ItemSet>();
	setData(getLoadDataController().loadItemSets(null, alreadyEnteredItemset, null, -1, -1));
    }
    

    public void setData(List<ItemSet> items) {
	this.items = items;
	fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
	return items.size();
    }
    
    @Override
    public int getColumnCount() {
	return 1;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	return items.get(rowIndex).getName();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
	try
	{
	    String text = e.getDocument().getText(0, e.getDocument().getLength());
	    List<ItemSet> loadItems = getLoadDataController().loadItemSets(text, alreadyEnteredItemset, null, -1, -1);
	    setData(loadItems);
	} catch (BadLocationException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }

    private LoadDataController getLoadDataController() {
	if(loadDataController == null)
	{
	    loadDataController = new LoadDataController();
	}
	return loadDataController;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
	try
	{
	    String text = e.getDocument().getText(0, e.getDocument().getLength());
	    List<ItemSet> loadItems = getLoadDataController().loadItemSets(text, alreadyEnteredItemset, null, -1, -1);
	    setData(loadItems);
	} catch (BadLocationException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
    
    public ItemSet getItem(int i) {
	return items.get(i);
    }


    public void setAlreadyEnteredItems(List<ItemSet> alreadyEnteredItems) {
	this.alreadyEnteredItemset = alreadyEnteredItems;
    }
    
}
