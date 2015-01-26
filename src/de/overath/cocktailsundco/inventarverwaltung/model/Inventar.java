package de.overath.cocktailsundco.inventarverwaltung.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

/**
 * Entity implementation class for Entity: Inventar
 *
 */
@Entity

public class Inventar implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    @Transient
    private static final String INVENTARNAME = "Inventarname: ";
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int inventarID;
    
    @NotNull
    private String name;

    @OneToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<InventarHasItem> itemSet = new HashSet<InventarHasItem>();
    
    public Inventar() {
	super();
    }   
    
    public int getInventarID() {
	return this.inventarID;
    }
    
    public void setInventarID(int inventarID) {
	this.inventarID = inventarID;
    }   
    public String getName() {
	return this.name;
    }
    
    public void setName(String name) {
	this.name = name;
    }
    
    public Set<InventarHasItem> getItemSet() {
	return itemSet;
    }
    
    public void setItemSet(Set<InventarHasItem> itemSet) {
	this.itemSet = itemSet;
    }
    
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append(INVENTARNAME);
	builder.append(name);
	builder.append("\n");
	
	for(InventarHasItem hasItem : itemSet)
	{
	    builder.append("\n");
	    if(hasItem != null && hasItem.getItem() != null)
	    builder.append(hasItem.getItem().toString());
	    builder.append("ANZAHL: ");
	    builder.append(hasItem.getAnzahl());
	}
	
	return builder.toString();
    }
    
    @Override
    public int hashCode() {
	return getInventarID();
    }

    public void addItem(Item item, int anzahl) {
	InventarHasItem inventarHasItem = new InventarHasItem();
	inventarHasItem.setItem(item);
	inventarHasItem.setAnzahl(anzahl);
	itemSet.add(inventarHasItem);
    }

    public void addInventarHasItem(InventarHasItem inventarHasItem) {
	itemSet.add(inventarHasItem);
    }
}
