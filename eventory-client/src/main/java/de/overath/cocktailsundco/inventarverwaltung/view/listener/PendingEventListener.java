package de.overath.cocktailsundco.inventarverwaltung.view.listener;

import de.overath.cocktailsundco.inventarverwaltung.model.Veranstaltung;

public interface PendingEventListener
{
    
    int PENDING_EVENT_REASON_REMINDER 	= 100;
    int PENDING_EVENT_REASON_FEEDBACK 	= 200;
    int PENDING_EVENT_REASON_ERROR 	= 300;
    void pendingEventFound(Veranstaltung veranstaltung, int reason);
    void noPendingEventFound();
}
