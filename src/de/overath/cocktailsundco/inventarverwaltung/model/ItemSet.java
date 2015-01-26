package de.overath.cocktailsundco.inventarverwaltung.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.internal.NotNull;

/**
 * Entity implementation class for Entity: ItemSet
 *
 */
@Entity

public class ItemSet implements Serializable {

	   
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String beschreibung;
	
	private float preis;
	
	private boolean isDeleted;
	
	@NotNull
	private Kategorie kategorie;

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="itemSet")
	private Set<ItemSetHasItem> itemSet; 
	
	public ItemSet() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Set<ItemSetHasItem> getItemSet() {
	    return itemSet;
	}
	public void setItemSet(Set<ItemSetHasItem> itemSet) {
	    this.itemSet = itemSet;
	}
	public String getBeschreibung() {
	    return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
	    this.beschreibung = beschreibung;
	}
	public Kategorie getKategorie() {
	    return kategorie;
	}
	public void setKategorie(Kategorie kategorie) {
	    this.kategorie = kategorie;
	}
	public boolean isDeleted() {
	    return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
	    this.isDeleted = isDeleted;
	}
	public float getPreis() {
	    return preis;
	}
	public void setPreis(float preis) {
	    this.preis = preis;
	}
   
}
