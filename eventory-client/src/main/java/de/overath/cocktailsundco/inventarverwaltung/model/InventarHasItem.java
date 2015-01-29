package de.overath.cocktailsundco.inventarverwaltung.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: InventarHasItem
 *
 */
@Entity
@Table(name="Inventarhasitem")
public class InventarHasItem implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int inventarHasItemId;
	
	@ManyToOne (cascade=CascadeType.ALL)
	private Item item;
	
	@Basic
	private float anzahl;

	public InventarHasItem() {
	    super();
	}

	public Item getItem() {
	    return item;
	}
	public void setItem(Item item) {
	    this.item = item;
	}
	public float getAnzahl() {
	    return anzahl;
	}
	public void setAnzahl(float anzahl) {
	    this.anzahl = anzahl;
	}
	
	@Override
	public String toString() {
	    return "InventarHasItem: " + item.toString() + " anzahl:" + anzahl;
	}

	public int getInventarHasItemId() {
	    return inventarHasItemId;
	}

	public void setInventarHasItemId(int inventarHasItemId) {
	    this.inventarHasItemId = inventarHasItemId;
	}
}
