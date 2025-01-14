package eu.siacs.conversations.entities;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import eu.siacs.conversations.xml.Element;

public class Presences {
	private final Hashtable<String, Presence> presences = new Hashtable<>();

	public Hashtable<String, Presence> getPresences() {
		return this.presences;
	}

	public void updatePresence(String resource, Presence presence) {
		synchronized (this.presences) {
			this.presences.put(resource, presence);
		}
	}

	public void removePresence(String resource) {
		synchronized (this.presences) {
			this.presences.remove(resource);
		}
	}

	public void clearPresences() {
		synchronized (this.presences) {
			this.presences.clear();
		}
	}

	public Presence getMostAvailablePresence() {
		synchronized (this.presences) {
			if (presences.size() < 1) { return null; }
			return Collections.min(presences.values());
		}
	}

	public String getMostAvailableResource(){
	    synchronized (this.presences) {
			if (presences.size() < 1) { return ""; }
			Presence p=Collections.min(presences.values());
			Iterator<Entry<String, Presence>> it = presences.entrySet().iterator();
			while (it.hasNext()) {
			    Entry<String, Presence> entry = it.next();
			    if (entry.getValue().equals(p)) return entry.getKey();
			}
			return "";
		}
	}

	public int size() {
		synchronized (this.presences) {
			return presences.size();
		}
	}

	public String[] asStringArray() {
		synchronized (this.presences) {
			final String[] presencesArray = new String[presences.size()];
			presences.keySet().toArray(presencesArray);
			return presencesArray;
		}
	}

	public boolean has(String presence) {
		synchronized (this.presences) {
			return presences.containsKey(presence);
		}
	}
}
