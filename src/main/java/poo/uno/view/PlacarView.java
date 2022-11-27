package poo.uno.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import poo.uno.game.Game;
import poo.uno.game.GameEvent;
import poo.uno.game.GameListener;

public class PlacarView extends GridPane implements GameListener {
	private TextField ptsJ1, ptsJ2;

	public PlacarView() {
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));

		Game.getInstance().addGameListener(this);

		ptsJ1 = new TextField();
		ptsJ2 = new TextField();

		ptsJ1.setText("" + Game.getInstance().getDeckJ1().getNumberOfCards());
		ptsJ2.setText("" + Game.getInstance().getDeckJ2().getNumberOfCards());

		this.add(new Label("Nro cartas jogador 1:"), 0, 0);
		this.add(ptsJ1, 1, 0);
		this.add(new Label("Nro cartas jogador 2:"), 0, 1);
		this.add(ptsJ2, 1, 1);
	}

	@Override
	public void notify(GameEvent event) {
		ptsJ1.setText("" + Game.getInstance().getDeckJ1().getNumberOfCards());
		ptsJ2.setText("" + Game.getInstance().getDeckJ2().getNumberOfCards());
	}
}
