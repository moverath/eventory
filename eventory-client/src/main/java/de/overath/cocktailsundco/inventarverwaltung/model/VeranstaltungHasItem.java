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
 * Entity implementation class for Entity: VeranstaltungHasItem
 *
 */
@Entity

public class VeranstaltungHasItem implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int veranstaltungHasItemId;
    
    @NotNull
    private float anzahl;
    
    
    @ManyToOne (cascade=CascadeType.ALL)
    private Item item;
    
    public VeranstaltungHasItem() {
	super();
    }
    
    public float getAnzahl() {
	return this.anzahl;
    }
    
    public void setAnzahl(float anzahl) {
	this.anzahl = anzahl;
    }
    public Item getItem() {
	return item;
    }
    public void setItem(Item item) {
	this.item = item;
    }
    
    public int getVeranstaltungId() {
	return veranstaltungHasItemId;
    }
    
    public void setVeranstaltungId(int veranstaltungHasItemId) {
	this.veranstaltungHasItemId = veranstaltungHasItemId;
    }
    
}
