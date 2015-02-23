package de.overath.cocktailsundco.inventarverwaltung.controller;

import de.overath.cocktailsundco.inventarverwaltung.view.listener.PendingEventListener;

public class CheckPendingEventsController
{

    public void check(LoadDataController loadDataController, PendingEventListener pendingEventListener) {
	//TODO check pending events for reasons descripted in PendingEventListener (maybe extend number of reasons)
	
//	try
//	{
//	    Thread.sleep(2000);
//	} catch (InterruptedException e)
//	{
//	    e.printStackTrace();
//	}
	
	pendingEventListener.noPendingEventFound();
    }
    
}
