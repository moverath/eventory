package de.overath.cocktailsundco.inventarverwaltung.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionController
{

    private static EntityManager em = null;
    
    public static EntityManager getEntityManager() {
	if(em == null)
	{
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("inventarverwaltungJPA");
	    em = emf.createEntityManager();
	}
	return em;
    }
    
}
