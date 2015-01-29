package de.overath.cocktailsundco.inventarverwaltung.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@Table(name="Item")
public class Item implements Serializable {

	   
    	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int itemID;
    	
    	@NotNull
	private String name;
    	
    	private boolean isDeleted;
    	
    	private String artikelnummer;
    	
    	private Kategorie kategorie;
    	
    	private boolean isVerbrauchsartikel;
    	
    	private String beschreibung;
    	
    	private byte[] bildOriginal;

    	private byte[] bildKlein;
    	
    	private float preis;
    	
	private static final long serialVersionUID = 1L;

	public Item() {
		super();
	}   
	public int getItemID() {
		return this.itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
	    return "Name: " + name + "\n"
	    	+ "beschreibung = " + beschreibung + "\n"
	    	+ "Verbrauchsartikel = " + isVerbrauchsartikel + "\n"
	    	+ "Hat Bild = " + (bildOriginal != null) + "\n"
	    	+ "Hat kleines bild = " + (bildKlein != null) + "\n"
	    	+ "Als geloescht Markiert = " + isDeleted;
	}
	
	@Override
	public int hashCode() {
	    return getItemID();
	}
	public boolean isVerbrauchsartikel() {
	    return isVerbrauchsartikel;
	}
	public void setVerbrauchsartikel(boolean isVerbrauchsartikel) {
	    this.isVerbrauchsartikel = isVerbrauchsartikel;
	}
	public String getBeschreibung() {
	    return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
	    this.beschreibung = beschreibung;
	}
	public boolean isDeleted() {
	    return isDeleted;
	}
	public void setDeleted(boolean deleted) {
	    this.isDeleted = deleted;
	}
	public byte[] getBildOriginal() {
	    return bildOriginal;
	}
	public void setBildOriginal(byte[] bildOriginal) {
	    this.bildOriginal = bildOriginal;
	}
	public byte[] getBildKlein() {
	    return bildKlein;
	}
	public void setBildKlein(byte[] bildKlein) {
	    this.bildKlein = bildKlein;
	}
	public float getPreis() {
	    return preis;
	}
	public void setPreis(float preis) {
	    this.preis = preis;
	}
	public String getArtikelnummer() {
	    return artikelnummer;
	}
	public void setArtikelnummer(String artikelnummer) {
	    this.artikelnummer = artikelnummer;
	}
	public Kategorie getKategorie() {
	    return kategorie;
	}
	public void setKategorie(Kategorie kategorie) {
	    this.kategorie = kategorie;
	}
   
}
