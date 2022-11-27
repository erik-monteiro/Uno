package poo.uno.game;

import java.util.LinkedList;
import java.util.List;

import poo.uno.cards.CardDeck;
import poo.uno.cards.NormalCard;
import poo.uno.cards.NormalCard.Color;


//import poo.modelo.GameEvent.Action;
//import poo.modelo.GameEvent.Target;

public class Game {
	private static Game game = new Game();
	private int cartasJ1, cartasJ2;
	private CardDeck deckJ1, deckJ2;
	private int player;
	private int jogadas;
	private List<GameListener> observers;

	public static Game getInstance() {
		return game;
	}

	private Game() {
		cartasJ1 = 7;
		cartasJ1 = 7;
		deckJ1 = new CardDeck(CardDeck.NCARDS);
		deckJ2 = new CardDeck(CardDeck.NCARDS);
		player = 1;
		jogadas = CardDeck.NCARDS;
		observers = new LinkedList<>();
	}

	private void nextPlayer() {
		player++;
		if (player == 4) {
			player = 1;
		}
	}

	public CardDeck getDeckJ1() {
		return deckJ1;
	}

	public CardDeck getDeckJ2() {
		return deckJ2;
	}

	public void play(CardDeck deckAcionado) {
		GameEvent gameEvent = null;

		if (player == 3) {
			gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.REMOVESEL, "");
			for (GameListener observer : observers) {
				observer.notify(gameEvent);
			}
			return;
		}
		if (deckAcionado == deckJ1) {
			if (player != 1) {	
				gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.INVPLAY, "2");
				for (GameListener observer : observers) {
					observer.notify(gameEvent);
				}
			} else {
				deckJ1.getSelectedCard();
				
				nextPlayer();
			}
		} else if (deckAcionado == deckJ2) {
			if (player != 2) {
				gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.INVPLAY, "2");
				for (GameListener observer : observers) {
					observer.notify(gameEvent);
				}
			} else {
				deckJ2.getSelectedCard();

				if (((NormalCard) deckJ1.getSelectedCard()).haveTheSameColor((NormalCard) deckJ2.getSelectedCard())) {
					cartasJ1--;
					cartasJ2--;
					nextPlayer();
				} else {
					gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.NOT_MATCH, "2");
					for (GameListener observer : observers) {
						observer.notify(gameEvent);
					}
				}

				for (GameListener observer : observers) {
					observer.notify(gameEvent);
				}			
			}
		}
	}

	public void removeSelected() {
		GameEvent gameEvent = null;
		if (player != 3) {
			return;
		}
		jogadas--;
		if (jogadas == 0) {
			gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.ENDGAME, "");
			for (GameListener observer : observers) {
				observer.notify(gameEvent);
			}
		}

		deckJ1.removeSel();
		deckJ2.removeSel();
		cartasJ1--;
		cartasJ2--;
		nextPlayer();
	}

	public void addCardToPlayer() {
		NormalCard nc = new NormalCard("ChangeColor", "img5", 4, Color.BLUE);
		if (player == 1) {
			deckJ1.addCard(nc);
			cartasJ1++;
		} else if (player == 2) {
			deckJ2.addCard(nc);
			cartasJ2++;
		}

		nextPlayer();
	}

	public void sayUNO() {
		NormalCard nc = new NormalCard("ChangeColor", "img2", 2, Color.BLUE);
		GameEvent gameEvent = null;
		if (player == 1) {
			if (cartasJ1 == 1) {
				gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.UNO, "");
				for (GameListener observer : observers) {
					observer.notify(gameEvent);
				}
			} else {
				deckJ1.addCard(nc);
				gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.FALSE_UNO, "");
				for (GameListener observer : observers) {
					observer.notify(gameEvent);
				}
			}
		} else if (player == 2) {
			if (cartasJ2 == 1) {
				gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.UNO, "");
				for (GameListener observer : observers) {
					observer.notify(gameEvent);
				}
			} else {
				deckJ2.addCard(nc);
				gameEvent = new GameEvent(this, GameEvent.Target.GWIN, GameEvent.Action.FALSE_UNO, "");
				for (GameListener observer : observers) {
					observer.notify(gameEvent);
				}
			}
		}
	}
	
	public void addGameListener(GameListener listener) {
		observers.add(listener);
	}
}
