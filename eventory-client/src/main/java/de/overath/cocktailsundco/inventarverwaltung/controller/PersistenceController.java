package de.overath.cocktailsundco.inventarverwaltung.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.overath.cocktailsundco.inventarverwaltung.model.Inventar;
import de.overath.cocktailsundco.inventarverwaltung.model.InventarHasItem;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;
import de.overath.cocktailsundco.inventarverwaltung.model.Kategorie;
import de.overath.cocktailsundco.inventarverwaltung.model.Veranstaltung;

public class PersistenceController
{

    public static final int MERGE = 0;
    public static final int PERSIST = 1;

    public void persistNewItem(InventarHasItem InventarHasItem, int mode, float anzahl) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();

	Query createNativeQuery = entityManager.createNativeQuery("Select * from Inventar", Inventar.class);
	List<Inventar> resultList = createNativeQuery.getResultList();
	if(resultList.isEmpty())
	{
	    Inventar e = new Inventar();
	    e.setName("Cocktails&Co Lager Scheeﬂel");
	    resultList.add(e);
	}
	Inventar inventar = resultList.get(0); // just first inventar is interesting for me CURRENTLY
	inventar.addInventarHasItem(InventarHasItem);
	if(mode == MERGE)
	{
	    entityManager.merge(inventar);
	}
	else if(mode == PERSIST)
	{
	    entityManager.persist(inventar);
	}
	transaction.commit();
    }

    public void persistInventar(Inventar inventar) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.persist(inventar);
	transaction.commit();
    }

    public void persistNewItemSet(ItemSet itemSet) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.persist(itemSet);
	transaction.commit();
    }
    
    public void mergeItemSet(ItemSet itemSet) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.merge(itemSet);
	transaction.commit();
    }

    public void persistNewKategorie(Kategorie kategorie) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.persist(kategorie);
	transaction.commit();
    }

    public void mergeKategorie(Kategorie kategorie) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.merge(kategorie);
	transaction.commit();
    }

    public void persistVeranstaltung(Veranstaltung veranstaltung) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.persist(veranstaltung);
	transaction.commit();
    }

    public void mergeInventarHasItem(InventarHasItem inventarHasItem) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.merge(inventarHasItem);
	transaction.commit();
	
    }
    
}
