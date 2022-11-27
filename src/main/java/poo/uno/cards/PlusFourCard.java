package poo.uno.cards;

import java.util.Random;

import poo.uno.cards.NormalCard.Color;

public class PlusFourCard extends Card {

    public PlusFourCard(String anId, String anImageId, int val) {
        super(anId, anImageId, val);
    }

    public void applyPower(CardDeck anotherDeck) {
        anotherDeck = new CardDeck(4);
        Random r = new Random();
        for (int i = 0; i < 3; i ++) {
            int n = r.nextInt(42) + 1;
			if (n >= 1 || n <= 10) {
				NormalCard normalCard = new NormalCard("NormalCard" + n, "img" + n, n, Color.BLUE);
                anotherDeck.addCard(normalCard);
			} else if (n >= 11 || n <= 20) {
				NormalCard normalCard = new NormalCard("NormalCard" + n, "img" + n, n, Color.GREEN);
				anotherDeck.addCard(normalCard);
			} 
        }
        
    }
  

}
