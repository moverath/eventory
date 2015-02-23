package de.overath.cocktailsundco.inventarverwaltung.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: VeranstaltungHasItemSet
 *
 */
@Entity

public class VeranstaltungHasItemSet implements Serializable {
    
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private float anzahl;
    
    @ManyToOne (cascade=CascadeType.ALL)
    private ItemSet itemSet;
    private static final long serialVersionUID = 1L;
    
    public VeranstaltungHasItemSet() {
	super();
    }   
    public int getId() {
	return this.id;
    }
    
    public void setId(int id) {
	this.id = id;
    }
    public float getAnzahl() {
        return anzahl;
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
    
    
    
}
