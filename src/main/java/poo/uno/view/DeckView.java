package poo.uno.view;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import poo.uno.cards.Card;
import poo.uno.cards.CardDeck;
import poo.uno.game.Game;
import poo.uno.game.GameEvent;
import poo.uno.game.GameListener;


public class DeckView extends HBox implements CardViewListener, GameListener {
	private int jogador;
	private CardDeck cDeck;
	private Card selectedCard;

	public DeckView(int nroJog) {
		super(4);
		this.setAlignment(Pos.CENTER);

		jogador = nroJog;
		selectedCard = null;

		cDeck = null;
		if (jogador == 1) {
			cDeck = Game.getInstance().getDeckJ1();
		} else if (jogador == 2) {
			cDeck = Game.getInstance().getDeckJ2();
		} 

		cDeck.addGameListener(this);

		for (Card card : cDeck.getCards()) {
			CardView cv = new CardView(card);
			cv.setCardViewObserver(this);
			this.getChildren().add(cv);
		}
	}

	private void showDeck() {
		//ObservableList<Node> cards = getChildren();
		//cDeck.addGameListener(this);

		this.getChildren().clear();

		System.out.println("m1.len>" + cDeck.getNumberOfCards());
		for (Card card : cDeck.getCards()) {
			System.out.println("show>" + card);
			CardView cv = new CardView(card);
			cv.setCardViewObserver(this);
			this.getChildren().add(cv);
		}
	}

	private void removeSel() {
		ObservableList<Node> cards = getChildren();
		for (int i = 0; i < cards.size(); i++) {
			CardView cv = (CardView) cards.get(i);
			if (cv.getCard() == selectedCard) {
				getChildren().remove(cv);
				selectedCard = null;
			}
		}
	}

	@Override
	public void notify(GameEvent event) {
		System.out.println("ev: "+ event);
		if (event.getTarget() != GameEvent.Target.DECK) {
			return;
		}
		if (event.getAction() == GameEvent.Action.SHOWTABLE) {
			showDeck();
		}
		if (event.getAction() == GameEvent.Action.REMOVESEL) {
			removeSel();
		}
	}

	@Override
	public void handle(CardViewEvent event) {
		CardView cv = event.getCardView();
		selectedCard = cv.getCard();
		cDeck.setSelectedCard(selectedCard);
		Game.getInstance().play(cDeck);
	}
}
