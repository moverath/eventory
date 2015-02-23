package de.overath.cocktailsundco.inventarverwaltung.beispiele;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.overath.cocktailsundco.inventarverwaltung.model.Inventar;
import de.overath.cocktailsundco.inventarverwaltung.model.InventarHasItem;
import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.model.Veranstaltung;
import de.overath.cocktailsundco.inventarverwaltung.model.VeranstaltungHasItem;


public class TestInventar
{
    
    private static final int ITEM_COUNT = 2000;

    public static void main(String[] args) {
	TestInventar testInventar = new TestInventar();
	testInventar.teste();
    }
    
    private void teste() {
	Inventar inventar  = new Inventar();
	inventar.setName("Cocktails&Co Scheeﬂel");
	
	fillInventar(inventar);
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("inventarverwaltungJPA");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	transaction.begin();
	em.persist(inventar);
	transaction.commit();
	
	Query createNativeQuery = em.createNativeQuery("select * from item", Item.class);
	List<Item> resultList = createNativeQuery.getResultList();
	
    }
    
    private void fillInventar(Inventar inventar) {
	for(int i = 0; i < ITEM_COUNT; i++)
	{
	    Item item = new Item();
	    item.setArtikelnummer(123 + "" + i);
	    item.setName("Item Nr." + i);
	    item.setPreis(i);
	    if(i % 2 == 0)
	    {
		item.setVerbrauchsartikel(true);
	    }
	    
	    InventarHasItem inventarHasItem = new InventarHasItem();
	    inventarHasItem.setItem(item);
	    inventarHasItem.setAnzahl(i);
	    inventar.addInventarHasItem(inventarHasItem);
	}
	
    }

    private void createVeranstaltungHasItem(Item item, Veranstaltung veranstaltung, float anzahl, Set<VeranstaltungHasItem> itemSet) {
	VeranstaltungHasItem hasItem = new VeranstaltungHasItem();
	hasItem.setItem(item);
	hasItem.setAnzahl(anzahl);
	
	itemSet.add(hasItem);
	
    }

    private void createInventarHasItem(Item item, Inventar inventar, Float anzahl, Set<InventarHasItem> itemSet) {
	InventarHasItem hasItem = new InventarHasItem();
	hasItem.setItem(item);
	hasItem.setAnzahl(anzahl);
	
	itemSet.add(hasItem);
    }
    
}
