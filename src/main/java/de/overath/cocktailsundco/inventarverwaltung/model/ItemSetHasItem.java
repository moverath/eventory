package de.overath.cocktailsundco.inventarverwaltung.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: ItemSetHasItem
 *
 */
@Entity

public class ItemSetHasItem implements Serializable {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Basic
    private float anzahl;
    
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private ItemSet itemSet;

    @ManyToOne
    private Item item;
    
    public ItemSetHasItem() {
	super();
    }   
    public float getAnzahl() {
	return this.anzahl;
    }
    
    public void setAnzahl(float anzahl) {
	this.anzahl = anzahl;
    }
    public ItemSet getItemSet() {
	return itemSet;
    }
    public void setItemSet(ItemSet itemSet) {
	this.itemSet = itemSet;
    }
    public Item getItem() {
	return item;
    }
    public void setItem(Item item) {
	this.item = item;
    }
}
