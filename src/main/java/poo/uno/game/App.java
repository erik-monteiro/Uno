package poo.uno.game;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import poo.uno.view.DeckView;
import poo.uno.view.PlacarView;

public class App extends Application implements GameListener {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Game.getInstance().addGameListener(this);

		primaryStage.setTitle("UNO");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		DeckView deckJ1 = new DeckView(1);
		ScrollPane sd1 = new ScrollPane();
		sd1.setPrefSize(900, 170);
		sd1.setContent(deckJ1);
		grid.add(sd1, 0, 0);
		 	
		PlacarView placar = new PlacarView();
		grid.add(placar, 0, 1);

		Button removeCards = new Button("Remove Cards");
		grid.add(removeCards, 1, 1);
		removeCards.setTranslateY(-20);
		removeCards.setOnAction(e -> Game.getInstance().removeSelected());
			
		Button butBuyCards = new Button("Buy a new Card");
		grid.add(butBuyCards, 0, 1);
		butBuyCards.setOnAction(e -> Game.getInstance().addCardToPlayer());
					
		Button butUno = new Button("UNO!");
		grid.add(butUno, 1, 0);
		butUno.setTranslateY(180);
		butUno.setOnAction(e -> Game.getInstance().sayUNO());

		DeckView deckJ2 = new DeckView(2);
		ScrollPane sd2 = new ScrollPane();
		sd2.setPrefSize(900, 170);
		sd2.setContent(deckJ2);
		grid.add(sd2, 0, 2);

		Scene scene = new Scene(grid, 1250,680);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

	@Override
	public void notify(GameEvent eg) {
		Alert alert;
		if (eg == null) return;
		if (eg.getTarget() == GameEvent.Target.GWIN) {
			switch (eg.getAction()) {
			case INVPLAY:
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Atenção !!");
				alert.setHeaderText("Jogada inválida!!");
				alert.setContentText("Era a vez do jogador " + eg.getArg());
				alert.showAndWait();
				break;
			case NOT_MATCH:
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Atenção !!");
				alert.setHeaderText(null);
				alert.setContentText("As cartas selecionadas devem possuir a mesma cor! Jogue novamente");
				alert.showAndWait();
				break;
			case UNO:
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Atenção !!");
				alert.setHeaderText(null);
				alert.setContentText("Alguem disse UNO!!");
				alert.showAndWait();
				break;
			case FALSE_UNO:
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Atenção !!");
				alert.setHeaderText("Jogador penalizado! Carta adicionada ao seu deck.");
				alert.setContentText("Alguem disse Uno porem tinha mais de uma carta no deck!");
				alert.showAndWait();
				break;
			case ADDCARD:
				// Esse evento não vem para cá
			case ENDGAME:
				String text = "Fim de Jogo !!\n";
				if (Game.getInstance().getDeckJ1().getNumberOfCards() == 0 && 
					Game.getInstance().getDeckJ2().getNumberOfCards() >= 1) {
					text += "O jogador 1 ganhou !! :-)";
				} else if (Game.getInstance().getDeckJ2().getNumberOfCards() == 0 &&
						   Game.getInstance().getDeckJ1().getNumberOfCards() >= 1) {
					text += "O jogador 2 ganhou !! :-)";
				}
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Parabens !!");
				alert.setHeaderText(null);
				alert.setContentText(text);
				alert.showAndWait();
				break;
			}
		}
	}

}
