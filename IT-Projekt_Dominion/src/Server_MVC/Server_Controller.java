package Server_MVC;

import java.io.IOException;

import Abstract_MVC.Controller;

/**
 * The Server_Controller controls the interaction between Server_View and Server_Model
 * and handles the events.
 * 
 * @author Bodo Gruetter 
 * source: Prof. Bradley Richards
 */
public class Server_Controller extends Controller<Server_Model, Server_View> {

	private boolean portValid = false;

	/**
	 * Handles the events between Server_View and Server_Model
	 * 
	 * @author Bodo Gruetter 
	 * source: Prof. Bradley Richards
	 * 
	 * @param model, the Server_Model
	 * @param view, the Server_View
	 */
	public Server_Controller(Server_Model model, Server_View view) {
		super(model, view);

		// changeListener that checks during the typing the portnumber
		view.txtPort.textProperty().addListener((observable, oldValue, newValue) -> {
			validatePortNumber(newValue);
		});

		// starting the server with a button click and disables this button
		view.btnStart.setOnAction((event) -> {
			try {
				view.btnStart.setDisable(true);
				view.btnStop.setDisable(false);
				view.txtPort.setEditable(false);
				model.startServerSocket(Integer.parseInt(view.txtPort.getText()));
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		view.btnStop.setOnAction((event) -> {
			view.btnStart.setDisable(false);
			view.btnStop.setDisable(true);
			view.txtPort.setEditable(true);
			model.stopServer();
		});
		
		view.stage.setOnCloseRequest(event -> model.stopServer());
	}

	/** 
	 * Sets port text green or red depending on if port value is valid.
	 * 
	 * @param newValue
	 * , the new input value.
	 * 
	 * @author Bodo Gruetter 
	 * source: Prof. Bradley Richards
	 */
	private void validatePortNumber(String newValue) {
		boolean valid = model.isValidPortNumber(newValue);

		if (valid) {
			view.txtPort.setStyle("-fx-text-inner-color: green;");
		} else {
			view.txtPort.setStyle("-fx-text-inner-color: red;");
		}

		portValid = valid;

		enableDisableButton();
	}

	/**
	 * Enables Button if the Port is not valid.
	 * 
	 * @author Bodo Gruetter 
	 * source: Prof. Bradley Richards
	 */
	private void enableDisableButton() {
		boolean valid = portValid;
		view.btnStart.setDisable(!valid);
	}
}// end Server_Controller