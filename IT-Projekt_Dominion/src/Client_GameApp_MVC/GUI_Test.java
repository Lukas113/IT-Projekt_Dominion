package Client_GameApp_MVC;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * @author Adrian
 * Defines the controls and elements of the GUI, aligns and styles them.
 */
public class GUI_Test extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		// Controls Action cards area
		Label lblActionCards = new Label("Action cards");
		TilePane tilepActionCards = new TilePane();

		// Controls Treasure cards area
		Label lblTreasureCards = new Label("Treasure cards");
		HBox hboxTreasureCards = new HBox(3);	

		// Controls Victory cards area
		Label lblVictoryCards = new Label("Victory cards");
		HBox hboxVictoryCards = new HBox(3);

		// Controls chat area
		Label lblChatArea = new Label("Chat area");
		ScrollPane scrlpChatArea = new ScrollPane();
		TextArea txtaChatArea = new TextArea();
		txtaChatArea.setDisable(true);
		TextField txtfChatArea = new TextField();
		Button btnSendChatArea = new Button("Send");

		// Controls log area
		Label lblLog = new Label("Log");
		ScrollPane scrlpLog = new ScrollPane();
		TextArea txtaLog = new TextArea();
		txtaLog.setDisable(true);

		// Controls discard area
		Label lblDiscard = new Label("Discard");
		StackPane stackpDiscard = new StackPane();

		// Controls deck area
		Label lblDeck = new Label("Deck");
		StackPane stackpDeck = new StackPane();

		// Controls played cards area
		Label lblPlayedCards = new Label("Played cards");
		HBox hboxPlayedCards = new HBox(4);

		// Controls hand cards area
		Label lblHandCards = new Label("Hand cards");
		HBox hboxHandCards = new HBox(7);

		// Controls current player area
		Label lblCurrentPlayer = new Label("Current player");
		Label lblCrntHandCards = new Label("Hand cards");
		Label lblNmbrOfCrntHandCards = new Label("0");
		Label lblCrntActions = new Label("Actions");
		Label lblNmbrOfCrntActions = new Label("0");
		Label lblCrntBuys = new Label("Buys");
		Label lblNmbrOfCrntBuys = new Label("0");
		Label lblCrntCoins = new Label("Coins");
		Label lblNmbrOfCrntCoins = new Label("0");

		Button btnCommit = new Button("Commit");

		// Action cards area
		VBox vboxActionCards = new VBox(lblActionCards, tilepActionCards);

		// Treasure cards area
		VBox vboxTreasureCards = new VBox(lblTreasureCards, hboxTreasureCards);

		// Victory cards area
		VBox vboxVictoryCards = new VBox(lblVictoryCards, hboxVictoryCards);

		// Chat area
		scrlpChatArea.setContent(txtaChatArea);
		HBox hboxChatArea = new HBox(txtfChatArea, btnSendChatArea);
		VBox vboxChatArea = new VBox(lblChatArea, scrlpChatArea, hboxChatArea);

		// Log area
		scrlpLog.setContent(txtaLog);
		VBox vboxLog = new VBox(lblLog, scrlpLog);

		// Discard area
		VBox vboxDiscard = new VBox(lblDiscard, stackpDiscard);

		// Deck area
		VBox vboxDeck = new VBox(lblDeck, stackpDeck);

		// Played cards area
		VBox vboxPlayedCards = new VBox(lblPlayedCards, hboxPlayedCards);

		// Hand cards area
		VBox vboxHandCards = new VBox(lblHandCards, hboxHandCards);

		// Current player area
		GridPane gridpCurrentPlayer = new GridPane();
		gridpCurrentPlayer.add(lblCrntHandCards, 0, 0);
		gridpCurrentPlayer.add(lblNmbrOfCrntHandCards, 1, 0);
		gridpCurrentPlayer.add(lblCrntActions, 0, 1);
		gridpCurrentPlayer.add(lblNmbrOfCrntActions, 1, 1);
		gridpCurrentPlayer.add(lblCrntBuys, 0, 2);
		gridpCurrentPlayer.add(lblNmbrOfCrntBuys, 1, 2);
		gridpCurrentPlayer.add(lblCrntCoins, 0, 3);
		gridpCurrentPlayer.add(lblNmbrOfCrntCoins, 1, 3);		

		VBox vboxCurrentPlayer = new VBox(lblCurrentPlayer, gridpCurrentPlayer, btnCommit);

		// Root
		GridPane root = new GridPane();

		// Add the containers to the specified location in the root
		root.add(vboxActionCards, 0, 0, 4, 2);
		root.add(vboxTreasureCards, 4, 0, 3, 1);
		root.add(vboxVictoryCards, 4, 1, 3, 1);
		root.add(vboxChatArea, 7, 0, 2, 1);
		root.add(vboxLog, 7, 1, 2, 1);
		root.add(vboxDiscard, 0, 2, 1, 1);
		root.add(vboxDeck, 0, 3, 1, 1);
		root.add(vboxPlayedCards, 1, 2, 8, 1);
		root.add(vboxHandCards, 1, 3, 7, 1);
		root.add(vboxCurrentPlayer, 8, 3, 1, 1);

		// Resizes the containers to the available size
		root.setHgrow(vboxActionCards, Priority.ALWAYS);
		root.setVgrow(vboxActionCards, Priority.ALWAYS);
		root.setHgrow(vboxTreasureCards, Priority.ALWAYS);
		root.setVgrow(vboxTreasureCards, Priority.ALWAYS);
		root.setHgrow(vboxVictoryCards, Priority.ALWAYS);
		root.setVgrow(vboxVictoryCards, Priority.ALWAYS);
		root.setHgrow(vboxChatArea, Priority.ALWAYS);
		root.setVgrow(vboxChatArea, Priority.ALWAYS);
		root.setHgrow(vboxLog, Priority.ALWAYS);
		root.setVgrow(vboxLog, Priority.ALWAYS);
		root.setHgrow(vboxDiscard, Priority.ALWAYS);
		root.setVgrow(vboxDiscard, Priority.ALWAYS);
		root.setHgrow(vboxDeck, Priority.ALWAYS);
		root.setVgrow(vboxDeck, Priority.ALWAYS);
		root.setHgrow(vboxPlayedCards, Priority.ALWAYS);
		root.setVgrow(vboxPlayedCards, Priority.ALWAYS);
		root.setHgrow(vboxHandCards, Priority.ALWAYS);
		root.setVgrow(vboxHandCards, Priority.ALWAYS);
		root.setHgrow(vboxCurrentPlayer, Priority.ALWAYS);
		root.setVgrow(vboxCurrentPlayer, Priority.ALWAYS);

		Scene scene = new Scene(root, 1000, 600);
		stage.setScene(scene);
		stage.setTitle("Dominion");
		stage.show();

		// Styles the elements of the GUI
		scene.getStylesheets().add(getClass().getResource("GUI_Test.css").toExternalForm());

		vboxActionCards.getStyleClass().add("vbox");
		tilepActionCards.getStyleClass().add("gaps");
		lblActionCards.getStyleClass().add("Label");

		vboxTreasureCards.getStyleClass().add("vbox");
		hboxTreasureCards.getStyleClass().add("hbox");

		vboxVictoryCards.getStyleClass().add("vbox");
		hboxVictoryCards.getStyleClass().add("hbox");

		vboxChatArea.getStyleClass().add("vbox");
		hboxChatArea.getStyleClass().add("hbox");

		vboxLog.getStyleClass().add("vbox");

		vboxDiscard.getStyleClass().add("vbox");

		vboxDeck.getStyleClass().add("vbox");

		vboxPlayedCards.getStyleClass().add("vbox");
		hboxPlayedCards.getStyleClass().add("hbox");

		vboxHandCards.getStyleClass().add("vbox");
		hboxHandCards.getStyleClass().add("hbox");

		vboxCurrentPlayer.getStyleClass().add("vboxCurrentPlayer");
		gridpCurrentPlayer.setHgap(20);

		root.getStyleClass().add("root_format");

		// Im Controller:
		// Commits user input to the server
		btnCommit.setOnAction(event -> {
			// do the work here
		});

		// Test: add of images to the boxes
		ImageView img1 = new ImageView(new Image(getClass().getResourceAsStream("Images/Geld_01.jpg")));
		img1.setFitWidth(50);
		img1.setFitHeight(100);
		hboxTreasureCards.getChildren().add(img1);

		// Adrian
		// Already implemented: Sends a chat message to the server 
		btnSendChatArea.setOnAction(event -> {
			String existingMessages = txtaChatArea.getText();
			String newMessage = txtfChatArea.getText();
			if (newMessage.length() > 0) {
				if (existingMessages.length() == 0)
					txtaChatArea.setText(newMessage);
				else
					txtaChatArea.setText(existingMessages + "\n" + newMessage);
				txtfChatArea.setText("");
			}
		});

	}
}
