package de.overath.cocktailsundco.inventarverwaltung.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Veranstaltung
 *
 */
@Entity

public class Veranstaltung implements Serializable {
    
    
    private static final long serialVersionUID = 1L;

    public Veranstaltung() {
	super();
    }
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    
    private String beschreibung;
    
    private String feedbackText;

    @Temporal(TemporalType.DATE)
    private Date datumBeginn;

    @Temporal(TemporalType.DATE)
    private Date datumEnde;
    
    @OneToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<VeranstaltungHasItem> itemSet;

    @OneToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<VeranstaltungHasItemSet> veranstaltungHasItemSet;

    @NotNull //Anzahl gaeste
    private float anzahl;
    
    public Long getId() {
	return this.id;
    }
    
    public void setId(Long id) {
	this.id = id;
    }   
    public String getName() {
	return this.name;
    }
    
    public void setName(String name) {
	this.name = name;
    }   
    public float getAnzahl() {
	return this.anzahl;
    }
    
    public void setAnzahl(float anzahl) {
	this.anzahl = anzahl;
    }

    public String getBeschreibung() {
	return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
	this.beschreibung = beschreibung;
    }

    public Date getDatumBeginn() {
	return datumBeginn;
    }

    public void setDatumBeginn(Date datumBeginn) {
	this.datumBeginn = datumBeginn;
    }

    public Date getDatumEnde() {
	return datumEnde;
    }

    public void setDatumEnde(Date datumEnde) {
	this.datumEnde = datumEnde;
    }

    public Set<VeranstaltungHasItem> getItemSet() {
	return itemSet;
    }

    public void setItemSet(Set<VeranstaltungHasItem> itemSet) {
	this.itemSet = itemSet;
    }

    public Set<VeranstaltungHasItemSet> getVeranstaltungHasItemSet() {
	return veranstaltungHasItemSet;
    }

    public void setVeranstaltungHasItemSet(Set<VeranstaltungHasItemSet> veranstaltungHasItemSet) {
	this.veranstaltungHasItemSet = veranstaltungHasItemSet;
    }

    public String getFeedbackText() {
	return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
	this.feedbackText = feedbackText;
    }

}
