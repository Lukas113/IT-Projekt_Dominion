package Client_Login_VC;

import java.net.URL;

import Abstract_MVC.View;
import Client_GameApp_MVC.GameApp_Model;
import Client_Services.ServiceLocator;
import Client_Services.Translator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * @author Rene
 * @version 1.0
 * @created 31-Okt-2017 17:04:53
 */
public class Login_View extends View<GameApp_Model> {

	//private ServiceLocator sl = ServiceLocator.getServiceLocator();
	private ServiceLocator sl;
	
	
	// controls -> accessed by controller 
	protected Label loginLbl;
	protected Label ipLbl;
	protected TextField ipText;
	protected Button connectBtn;
	
	protected Label nameLbl;
	protected TextField nameText;
	
	protected Label passwordLbl;
	protected TextField passwordText;
	protected Button loginBtn;
	
	protected Button createNewPlayerBtn;
	protected Button quitBtn;


	/**
	 * 
	 * @param model
	 */
	public Login_View(Stage stage, GameApp_Model model){
		super(stage, model);
		//stage.initStyle(StageStyle.TRANSPARENT);
		
	}

	@Override
	protected Scene create_GUI(){
		
		//sl.setTranslator(new Translator("en"));
		sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getTranslator();
		
		// layouts
		GridPane root = new GridPane();
		root.setId("root");
		VBox centerBox = new VBox();
		centerBox.setId("centerBox");
		VBox ipBox = new VBox();
		ipBox.setId("ipBox");
		VBox nameBox = new VBox();
		nameBox.setId("nameBox");
		VBox passwordBox = new VBox();
		passwordBox.setId("passwordBox");
		
		
		
		// labels and text fields
		loginLbl = new Label(t.getString("login.loginLbl"));
		loginLbl.setId("loginLbl");
		
		ipLbl = new Label(t.getString("login.ipLbl"));
		ipLbl.setId("ipLbl");
		ipText = new TextField();
		ipText.setId("ipText");
		ipText.setPrefSize(220.0, 30.0);
		connectBtn = new Button(t.getString("login.connectBtn"));
		connectBtn.setId("connectBtn");	
		HBox ipAndConnectBox = new HBox(ipText, connectBtn);
		ipAndConnectBox.setId("ipAndConnectBox");
		ipBox.getChildren().addAll(ipLbl, ipAndConnectBox);
		
		nameLbl = new Label(t.getString("login.nameLbl"));
		nameLbl.setId("nameLbl");
		nameText = new TextField();
		nameText.setId("nameText");
		//nameText.setPrefSize(220.0, 30.0);
		nameText.setMaxSize(220.0, 30.0);
		nameBox.getChildren().addAll(nameLbl, nameText);
		
		passwordLbl = new Label(t.getString("login.passwordLbl"));
		passwordLbl.setId("passwordLbl");
		passwordText = new TextField();
		passwordText.setId("passwordText");
		passwordText.setPrefSize(220.0, 30.0);
		loginBtn = new Button(t.getString("login.loginBtn"));
		loginBtn.setId("loginBtn");
		
		HBox pwLoginBox = new HBox(passwordText, loginBtn);
		pwLoginBox.setId("pwLoginBox");
		
		passwordBox.getChildren().addAll(passwordLbl, pwLoginBox);
		
		// buttons
		createNewPlayerBtn = new Button(t.getString("login.createNewPlayerBtn"));
		createNewPlayerBtn.setId("createNewPlayerBtn");
		quitBtn = new Button(t.getString("login.quitBtn"));
		quitBtn.setId("quitBtn");
		
		HBox buttonBox = new HBox(createNewPlayerBtn, quitBtn);
		buttonBox.setId("buttonBox");
		
		
		// VBox for layout and spacing 
		VBox ipNamePasswordBox = new VBox();
		ipNamePasswordBox.setId("ipNamePasswordBox");
		ipNamePasswordBox.getChildren().addAll(ipBox, nameBox, passwordBox);
		
		
		// layout and size configurations 
		root.setPrefSize(1280,720);
		root.setAlignment(Pos.CENTER);
		centerBox.getChildren().addAll(loginLbl, ipNamePasswordBox, buttonBox);
		// centerBox.getChildren().addAll(createNewPlayer, nameBox, passwordBox, buttonBox); // -> ohne Sprachauswahl 
		root.getChildren().add(centerBox);
		
		
		// https://panjutorials.de/tutorials/javafx-8-gui/lektionen/audio-player-in-javafx-2/?cache-flush=1510439948.4916 
		//hier legen wir die Resource an, welche unbedingt im entsprechenden Ordner sein muss
		final URL resource = getClass().getResource("Medieval_Camelot.mp3");
		// wir legen das Mediaobjekt and und weisen unsere Resource zu
		final Media media = new Media(resource.toString());
		// wir legen den Mediaplayer an und weisen ihm das Media Objekt zu
		final MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		mediaPlayer.play();
		//mediaPlayer.stop();
		
		
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		this.stage.setScene(scene);
		//stage.setFullScreen(true); // set Full Screen
		
		return scene;
	}
	
	//public void start() {
	
}//end Login_View