package poo.uno.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import poo.uno.cards.NormalCard.Color;
import poo.uno.game.GameEvent;
import poo.uno.game.GameListener;

//import poo.modelo.GameEvent.Action;
//import poo.modelo.GameEvent.Target;

public class CardDeck {
	public static int NCARDS = 7;
	private List<Card> cartas;
	private Card selected;
	private List<GameListener> observers;

	public CardDeck(int nroCartas) {
		cartas = new ArrayList<>(nroCartas);
		selected = null;
		Random r = new Random();
		for (int i = 0; i < nroCartas; i++) {
			int n = r.nextInt(42) + 1;
			if (n >= 1 || n <= 10) {
				NormalCard normalCard = new NormalCard("NormalCard" + n, "img" + n, n, Color.BLUE);
				cartas.add(normalCard);
			} else if (n >= 11 || n <= 20) {
				NormalCard normalCard = new NormalCard("NormalCard" + n, "img" + n, n, Color.GREEN);
				cartas.add(normalCard);
			} else if (n >= 21 || n <= 30) {
				NormalCard normalCard = new NormalCard("NormalCard" + n, "img" + n, n, Color.RED);
				cartas.add(normalCard);
			} else if (n >= 31 || n <= 40) {
				NormalCard normalCard = new NormalCard("NormalCard" + n, "img" + n, n, Color.YELLOW);
				cartas.add(normalCard);
			} else {
				if (n == 41) { 
					PlusFourCard plusFour = new PlusFourCard("PlusFourCard" + n, "img" + n, n); 
					cartas.add(plusFour);
				} else if (n == 42) { 
					ChangeColorCard changeColor = new ChangeColorCard("ChangeColorCard" + n, "img" + n, n); 
					cartas.add(changeColor);
				}
			}	
		}
		observers = new LinkedList<>();
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cartas);
	}

	public int getNumberOfCards() {
		return cartas.size();
	}

	public void removeSel() {
		if (selected == null) {
			return;
		}
		cartas.remove(selected);
		selected = null;
		GameEvent gameEvent = new GameEvent(this, GameEvent.Target.DECK, GameEvent.Action.REMOVESEL, "");
		for (GameListener observer : observers) {
			observer.notify(gameEvent);
		}
	}
	
	public void addCard(Card card) {
		System.out.println("add: " + card);
		cartas.add(card);
		GameEvent gameEvent = new GameEvent(this, GameEvent.Target.DECK, GameEvent.Action.SHOWTABLE, "");
		for (GameListener observer : observers) {
			observer.notify(gameEvent);
		}
	}

	public void setSelectedCard(Card card) {
		selected = card;
	}

	public Card getSelectedCard() {
		return selected;
	}

	public void addGameListener(GameListener listener) {
		observers.add(listener);
	}

}

