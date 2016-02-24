package eu.siacs.conversations.entities;

import android.util.Log;

import java.lang.Comparable;
import java.util.Locale;

import eu.siacs.conversations.xml.Element;

public class Presence implements Comparable {

	public enum Status {
		CHAT, ONLINE, AWAY, XA, DND, OFFLINE;

		public String toShowString() {
			switch(this) {
				case CHAT: return "chat";
				case AWAY: return "away";
				case XA:   return "xa";
				case DND:  return "dnd";
			}

			return null;
		}
	}

	protected final Status status;
	protected ServiceDiscoveryResult disco;
	protected final String ver;
	protected final String hash;
	protected final String statusMessage;

	private Presence(Status status, String ver, String hash, String statusMessage) {
		this.status = status;
		this.ver = ver;
		this.hash = hash;
		this.statusMessage = statusMessage;
	}

	public static Presence parse(String show, Element caps, String statusMessage) {
		final String hash = caps == null ? null : caps.getAttribute("hash");
		final String ver = caps == null ? null : caps.getAttribute("ver");
		if (show == null) {
			return new Presence(Status.ONLINE, ver, hash, statusMessage);
		} else {
			switch (show.toLowerCase(Locale.US)) {
				case "away":
					return new Presence(Status.AWAY, ver, hash, statusMessage);
				case "xa":
					return new Presence(Status.XA, ver, hash, statusMessage);
				case "dnd":
					return new Presence(Status.DND, ver, hash, statusMessage);
				case "chat":
					return new Presence(Status.CHAT, ver, hash, statusMessage);
				default:
					return new Presence(Status.ONLINE, ver, hash, statusMessage);
			}
		}
	}

	public int compareTo(Object other) {
		return this.status.compareTo(((Presence)other).status);
	}

	public Status getStatus() {
		return this.status;
	}

	public boolean hasCaps() {
		return ver != null && hash != null;
	}

	public String getVer() {
		return this.ver;
	}

	public String getHash() {
		return this.hash;
	}

	public String getStatusMessage(){
		return this.statusMessage;
	}

	public void setServiceDiscoveryResult(ServiceDiscoveryResult disco) {
		this.disco = disco;
	}
}
