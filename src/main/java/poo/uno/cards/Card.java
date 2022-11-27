package poo.uno.cards;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Card {
	private String id;
	private String imageId;
	private int value;
	private boolean faceUp;
	private final PropertyChangeSupport pcs;

	public Card(String anId, String anImageId, int val) {
		this.id = anId;
		this.imageId = anImageId;
		this.value = val;
		this.faceUp = true;
		this.pcs = new PropertyChangeSupport(this);
	}

	public String getId() {
		return id;
	}

	public String getImageId() {
		return imageId;
	}

	public int getValue() {
		return value;
	}

	public boolean isFacedUp() {
		return faceUp;
	}

	public void flip() {
		boolean old = faceUp;
		faceUp = !faceUp;
		pcs.firePropertyChange("facedUp", old, faceUp);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public String toString() {
		return String.format("Card(%s, %d, %s)",id, value, faceUp);
	}

	
}
