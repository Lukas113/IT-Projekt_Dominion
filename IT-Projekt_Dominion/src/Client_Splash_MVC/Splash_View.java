package Client_Splash_MVC;

import java.net.URISyntaxException;

import Abstract_MVC.View;
import Client_Services.ServiceLocator;
import Client_Services.Translator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Rene
 * @version 1.0
 * @created 31-Okt-2017 17:06:18
 */
public class Splash_View extends View<Splash_Model> {

	protected ProgressBar progress;
	private Label lblStatus;
	

	/**
	 * 
	 * @param stage
	 * @param model
	 */
	public Splash_View(Stage stage, Splash_Model model){
		super(stage, model);
		//stage.initStyle(StageStyle.TRANSPARENT); // also undecorated
	}

	@Override
	protected Scene create_GUI(){

		ServiceLocator sl = ServiceLocator.getServiceLocator();
		//sl.setTranslator(new Translator("de"));
		Translator t = sl.getTranslator();
		
		lblStatus = new Label(t.getString("splash.label"));
		lblStatus.setTextAlignment(TextAlignment.CENTER);
		lblStatus.setId("lblStatus");
		
        progress = new ProgressBar();
		progress.setId("progress");
		
		VBox root = new VBox(lblStatus, progress);
		root.setId("root");
		
		root.setAlignment(Pos.CENTER);
		
		
        
        Scene scene = new Scene(root, 300, 300);
        
        scene.getStylesheets().addAll(this.getClass().getResource("Splash.css").toExternalForm());
		
        return scene;
	}
}//end Splash_View