package eu.siacs.conversations.entities;

import java.util.List;

import eu.siacs.conversations.xmpp.jid.Jid;

public interface ListItem extends Comparable<ListItem> {
	String getDisplayName();

	String getDisplayJid();

	Jid getJid();

	List<Tag> getTags();

	public String getStatusMessage();

	final class Tag {
		private final String name;
		private final int color;

		public Tag(final String name, final int color) {
			this.name = name;
			this.color = color;
		}

		public int getColor() {
			return this.color;
		}

		public String getName() {
			return this.name;
		}
	}

	boolean match(final String needle);
}
