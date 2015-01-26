package de.overath.cocktailsundco.inventarverwaltung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kategorie
{
    	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String beschreibung;

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getBeschreibung() {
	    return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
	    this.beschreibung = beschreibung;
	}
	
	@Override
	public String toString() {
	    return name;
	}
}
