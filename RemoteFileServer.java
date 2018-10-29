import java.net.*;
import java.io.*;
import java.util.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

/*
*
*@author: Omar Abdullahi , Will, Burger, Dustin Rochette
*@Version: 10/25/2018
*@Course: ISTE-121
*@Description: RemoteFileServer
*
*/
public class RemoteFileServer extends Application implements EventHandler<ActionEvent> {
   // Window Attributes
	private Stage stage;
	private Scene scene;
	private VBox root = new VBox(8);

	// GUI Components
	private Button btnStart = new Button("Start");
	private TextArea taLog = new TextArea();

	// Socket, IO
	private ServerSocket sSocket = null;
	public static final int SERVER_PORT = 32001;
	private PrintWriter pwt = null;
	private Scanner scn = null;

	// Main
	public static void main(String[] args) {
		launch(args);
	}

	// Start
	public void start(Stage _stage) {
		// Window
		stage = _stage;
		stage.setTitle("Remote File Server");

		// Button Set On Action
		btnStart.setOnAction(this);

		// Top GUI
		FlowPane fpTop = new FlowPane(8, 8);
		fpTop.getChildren().addAll(btnStart);
		fpTop.setAlignment(Pos.TOP_RIGHT);
		root.getChildren().addAll(fpTop);

		// Bottom GUI
		FlowPane fpBottom = new FlowPane(8, 8);
		fpBottom.getChildren().addAll(taLog);
		taLog.setWrapText(true);
		root.getChildren().addAll(fpBottom);

		// Show window
		scene = new Scene(root, 475, 250);
		stage.setScene(scene);
		stage.show();
	}

	// Button Handlers
	public void handle(ActionEvent javaGunners) {
		/*String label = ((Button)evt.getSource()).getText();
		switch(label) {
			case "Start":
				// Start ConnectionThread to open socket
				doStart();
				break;
			case "Stop":
				// Close socket
				doStop();
				break;
		}
      */
	}

	// Log
   /*
	public void log(String message) {
		Platform.runLater(new Runnable() {
			public void run() {
				taLog.appendText("<localhost: " + SERVER_PORT + "> " + message + "\n");
			}
		});
	}
   
   
     //  public void handle(ActionEvent ae) {
    /*
      String label = ((Button)ae.getSource()).getText();
      switch(label) {
         case "Connect":
            doConnect();
            break;
         case "Disconnect":
            doDisconnect();
            break;
         case "Send":
            doSend();
            break;
      }
      */
  // }


}