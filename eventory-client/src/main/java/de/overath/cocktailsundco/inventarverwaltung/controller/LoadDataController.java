package de.overath.cocktailsundco.inventarverwaltung.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.overath.cocktailsundco.inventarverwaltung.model.InventarHasItem;
import de.overath.cocktailsundco.inventarverwaltung.model.Item;
import de.overath.cocktailsundco.inventarverwaltung.model.ItemSet;
import de.overath.cocktailsundco.inventarverwaltung.model.Kategorie;
import de.overath.cocktailsundco.inventarverwaltung.model.Veranstaltung;

public class LoadDataController
{
    
    private static final String WILDCARD = "%";
    private static final String EMPTY_SEARCH = "%";
    
    public static final int ONLY_VERBRAUCHSARTIKEL = 10;
    public static final int ONLY_EQUIPMENT = 20;
    public static final int BOTH = 30;
    
    
    public List<InventarHasItem> loadInventarHasItems(String substring) {
	
	EntityManager entityManager = ConnectionController.getEntityManager();
	Query query;
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	//Query for a List of objects.
	CriteriaQuery<InventarHasItem> cq = cb.createQuery(InventarHasItem.class);
	Root<InventarHasItem> inventarHasItem = cq.from(InventarHasItem.class);
	Join<InventarHasItem, Item> item = inventarHasItem.join("item");
	
	String param = substring.isEmpty() ? EMPTY_SEARCH : WILDCARD + substring + WILDCARD ;
	
	Predicate name = cb.like(item.get("name").as(String.class),param);
	cq.where(name);
	
	query = entityManager.createQuery(cq);
	List<InventarHasItem> bla = query.getResultList();
	System.out.println("###################");
	System.out.println(bla);
	return bla;
    }
    
    public List<Kategorie> loadKategorien(String substring) {
	
	EntityManager entityManager = ConnectionController.getEntityManager();
	Query query;
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	//Query for a List of objects.
	CriteriaQuery<Kategorie> cq = cb.createQuery(Kategorie.class);
	Root<Kategorie> kategorie = cq.from(Kategorie.class);
	
	String nameString = substring!=null && !substring.isEmpty() ? WILDCARD + substring + WILDCARD : EMPTY_SEARCH;
	if(substring != null && !substring.isEmpty())
	{
	    Predicate name = cb.like(cb.upper(kategorie.get("name").as(String.class)),nameString.toUpperCase());
	    cq.where(name);
	}
	
	query = entityManager.createQuery(cq);
	List<Kategorie> bla = query.getResultList();
	return bla;
    }
    
    public List<Item> loadItems(String artikelnummer, String substring, List<Item> alreadyEnteredItems, int verbrauchsartikelMode) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	
	Query query;
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	//Query for a List of objects.
	CriteriaQuery<Item> cq = cb.createQuery(Item.class);
	Root<Item> item = cq.from(Item.class);
	
	String nameString = substring!=null && !substring.isEmpty() ? WILDCARD + substring + WILDCARD : EMPTY_SEARCH;
	String artikelString = artikelnummer!=null && !artikelnummer.isEmpty() ? WILDCARD + artikelnummer + WILDCARD : EMPTY_SEARCH;
	
	List<Predicate> list = new ArrayList<Predicate>();
	
	if(substring != null && !substring.isEmpty())
	{
	    Predicate name = cb.like(cb.upper(item.get("name").as(String.class)),nameString.toUpperCase());
	    list.add(name);
	}
	
	if(artikelnummer != null && !artikelnummer.isEmpty())
	{
	    Predicate artikel = cb.like(cb.upper(item.get("artikelnummer").as(String.class)),artikelString.toUpperCase());
	    list.add(artikel);
	}
	
	if(verbrauchsartikelMode == ONLY_VERBRAUCHSARTIKEL)
	{
	    Predicate verbrauch =  cb.isTrue(item.get("isVerbrauchsartikel").as(Boolean.class));
	    list.add(verbrauch);
	}
	else if(verbrauchsartikelMode == ONLY_EQUIPMENT)
	{
	    Predicate verbrauch =  cb.isFalse(item.get("isVerbrauchsartikel").as(Boolean.class));
	    list.add(verbrauch);
	}
	
	if(alreadyEnteredItems != null && alreadyEnteredItems.size() > 0)
	{
	    Predicate notLikeItemId = cb.not(item.in(alreadyEnteredItems));
	    list.add(notLikeItemId);
	}
	
	cq.where(list.toArray(new Predicate[list.size()]));
	
	cq.orderBy(cb.asc(item.get("artikelnummer")));
	
	query = entityManager.createQuery(cq);
	List<Item> bla = query.getResultList();
	return bla;
    }
    
    public List<ItemSet> loadItemSets() {
	EntityManager entityManager = ConnectionController.getEntityManager();
	Query createQuery = entityManager.createNativeQuery("SELECT * FROM ItemSet", ItemSet.class);
	List<ItemSet> result = createQuery.getResultList();
	return result;
    }
    
    public List<Veranstaltung> loadVeranstaltungen() {
	EntityManager entityManager = ConnectionController.getEntityManager();
	Query createQuery = entityManager.createNativeQuery("SELECT * FROM Veranstaltung", Veranstaltung.class);
	List<Veranstaltung> result = createQuery.getResultList();
	return result;
    }
    
    public List<ItemSet> loadItemSets(String substring, List<ItemSet> alreadyEnteredItems, Kategorie kategorie, float min, float max) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	
	Query query;
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	//Query for a List of objects.
	CriteriaQuery<ItemSet> cq = cb.createQuery(ItemSet.class);
	Root<ItemSet> itemSet = cq.from(ItemSet.class);
	
	String nameString = substring!=null && !substring.isEmpty() ? WILDCARD + substring + WILDCARD : EMPTY_SEARCH;
	
	List<Predicate> list = new ArrayList<Predicate>();
	
	if(substring != null && !substring.isEmpty())
	{
	    Predicate name = cb.like(cb.upper(itemSet.get("name").as(String.class)),nameString.toUpperCase());
	    list.add(name);
	}
	
	if(alreadyEnteredItems != null && alreadyEnteredItems.size() > 0)
	{
	    Predicate notLikeItemId = cb.not(itemSet.in(alreadyEnteredItems));
	    list.add(notLikeItemId);
	}
	
	if(kategorie != null)
	{
	    Predicate gotKategorie = cb.equal(itemSet.get("kategorie").as(Kategorie.class), kategorie);
	    list.add(gotKategorie);
	}
	
	if (min  > 0 && max > 0)
	{
	    Predicate between = cb.between(itemSet.get("preis").as(Float.class), min, max);
	    list.add(between);
	}
	
	cq.where(list.toArray(new Predicate[list.size()]));
	
	cq.orderBy(cb.asc(itemSet.get("name")));
	
	query = entityManager.createQuery(cq);
	List<ItemSet> bla = query.getResultList();
	return bla;
    }
    
    public List<InventarHasItem> loadItems(String substring, String artikelnummer, int verbrauchsartikelMode, float min, float max) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	
	Query query;
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	//Query for a List of objects.
	CriteriaQuery<InventarHasItem> cq = cb.createQuery(InventarHasItem.class);
	Root<InventarHasItem> inventarHasItem = cq.from(InventarHasItem.class);
	Join<InventarHasItem, Item> item = inventarHasItem.join("item");
	
	String nameString = substring!=null && !substring.isEmpty() ? WILDCARD + substring + WILDCARD : EMPTY_SEARCH;
	String artikelString = artikelnummer!=null && !artikelnummer.isEmpty() ? WILDCARD + artikelnummer + WILDCARD : EMPTY_SEARCH;
	
	List<Predicate> list = new ArrayList<Predicate>();
	
	if(substring != null && !substring.isEmpty())
	{
	    Predicate name = cb.like(cb.upper(item.get("name").as(String.class)),nameString.toUpperCase());
	    list.add(name);
	}
	
	if(artikelnummer != null && !artikelnummer.isEmpty())
	{
	    Predicate artikel = cb.like(cb.upper(item.get("artikelnummer").as(String.class)),artikelString.toUpperCase());
	    list.add(artikel);
	}

	if(min  > 0 && max > 0)
	{
	    Predicate between = cb.between(inventarHasItem.get("anzahl").as(Float.class), min, max);
	    list.add(between);
	}
	
	if(verbrauchsartikelMode == ONLY_VERBRAUCHSARTIKEL)
	{
	    Predicate verbrauch =  cb.isTrue(item.get("isVerbrauchsartikel").as(Boolean.class));
	    list.add(verbrauch);
	}
	else if(verbrauchsartikelMode == ONLY_EQUIPMENT)
	{
	    Predicate verbrauch =  cb.isFalse(item.get("isVerbrauchsartikel").as(Boolean.class));
	    list.add(verbrauch);
	}
	
	
	cq.where(list.toArray(new Predicate[list.size()]));
	
	cq.orderBy(cb.asc(item.get("artikelnummer")));
	
	query = entityManager.createQuery(cq);
	List<InventarHasItem> bla = query.getResultList();
	return bla;
    }

    public float findMaxVorratsmenge(int verbrauchsartikelMode) {
	EntityManager entityManager = ConnectionController.getEntityManager();
	
	Query query;
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	//Query for a List of objects.
	CriteriaQuery<Float> cq = cb.createQuery(Float.class);
	Root<InventarHasItem> inventarHasItem = cq.from(InventarHasItem.class);
	Join<InventarHasItem, Item> item = inventarHasItem.join("item");
	
	List<Predicate> list = new ArrayList<Predicate>();
	
	if(verbrauchsartikelMode == ONLY_VERBRAUCHSARTIKEL)
	{
	    Predicate verbrauch =  cb.isTrue(item.get("isVerbrauchsartikel").as(Boolean.class));
	    list.add(verbrauch);
	}
	else if(verbrauchsartikelMode == ONLY_EQUIPMENT)
	{
	    Predicate verbrauch =  cb.isFalse(item.get("isVerbrauchsartikel").as(Boolean.class));
	    list.add(verbrauch);
	}
	cq.where(list.toArray(new Predicate[list.size()]));
	cq.select(cb.max(inventarHasItem.get("anzahl").as(Float.class)));
	
	query = entityManager.createQuery(cq);
	List<Float> bla = query.getResultList();
	return bla.get(0);
    }	
}
