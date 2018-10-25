import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

import java.net.*;
import java.io.*;
import java.util.*;

/*
*
*@author: Omar Abdullahi , Will, Burger, Dustin
*@Version: 10/25/2018
*@Course: ISTE-121
*@Description: RemoteFileBrowser
*
*/

public class RemoteFileBrowser extends Application implements EventHandler<ActionEvent> {
   // Window Attributes
   private Stage stage;
   private Scene scene;
   private VBox root;
   
   // Components - TOP
   // The TOP will itself be GridPane ... we will use Row1 and Row2 of the this Grid
   // These are for Row1
   private Label lblServerIP = new Label("Server:");
   private TextField tfServer = new TextField();
   private Button btnConnect = new Button("Connect");

   // These will be in Row2
   private Button btnList = new Button("List");
   private Button btnCD = new Button("Ch Dir");
   private Button btnUpload = new Button("Upload");
   private Button btnDownload = new Button("Download");
   
   //These will be in Row3 for Logs
   private TextArea taLog = new TextArea();

   // IO attributes
   private PrintWriter pwt = null;
   private Scanner scn = null;

   // OTHER attributes
   public static final int SERVER_PORT = 32001;
   private Socket socket = null;

   /**
    * main program 
    */
   public static void main(String[] args) {
      launch(args);
   }

   /**
    * Constructor ... draw and set up GUI
    */
   public void start(Stage _stage) {
      stage = _stage;
      stage.setTitle("RemoteFile Browser");
      stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
         public void handle(WindowEvent evt) { System.exit(0); }
      } );
      stage.setResizable(false);
      root = new VBox(8);
      
      // ROW1 ... FlowPane ... 
      FlowPane fpRow1 = new FlowPane(8,8);
         fpRow1.setAlignment(Pos.CENTER);
         tfServer.setPrefColumnCount(15);
         fpRow1.getChildren().addAll(lblServerIP,tfServer, btnConnect);
      root.getChildren().add(fpRow1);

      // ROW2 - Buttons for list/change Dir/Upload/ and Download
      FlowPane fpRow2 = new FlowPane(8,8);
         fpRow2.setAlignment(Pos.CENTER);
         fpRow2.getChildren().addAll( btnList, btnCD, btnUpload, btnDownload);
         
         // ROW2- buttons disabled until connected
         btnList.setDisable(true);
         btnCD.setDisable(true);
         btnUpload.setDisable(true);
         btnDownload.setDisable(true);
         root.getChildren().add(fpRow2);
      
      // LOG ... Label + text area
      FlowPane fpLog = new FlowPane();
         fpLog.setAlignment(Pos.CENTER);
         taLog.setPrefColumnCount(35);
         taLog.setPrefRowCount(10);
         fpLog.getChildren().addAll(taLog);
      root.getChildren().add(fpLog);
      
      // Listen for the buttons
      btnConnect.setOnAction(this);

      scene = new Scene(root, 475, 300);
      stage.setScene(scene);
      stage.show();      
   }
   

 /** 
    * Button dispatcher
    */
       
    public void handle(ActionEvent ae) {
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
   }

   /**
    * doConnect - Connect button
    */
       /*
   private void doConnect() {
      try {
         // Connect to server and set up two streams, a Scanner for input from the
         // server and a PrintWriter for output to the server
         socket = new Socket(tfServerIP.getText(), SERVER_PORT);
         scn = new Scanner(new InputStreamReader(socket.getInputStream()));
         pwt = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      }
      catch(IOException ioe) {
         taLog.appendText("IO Exception: " + ioe + "\n");
         return;
      }
      taLog.appendText("Connected!\n");
      btnConnect.setText("Disconnect");
      
      // Enable text field and Send button
      tfSentence.setDisable(false);
      btnSend.setDisable(false);
   }

   /**
    * doDisconnect - Disconnect button'
    */
       /*
   private void doDisconnect() {
      try {
         // Close the socket and streams
         socket.close();
         scn.close();
         pwt.close();
      }
      catch(IOException ioe) {
         taLog.appendText("IO Exception: " + ioe + "\n");
         return;
      }
      btnConnect.setText("Connect");
      
      // Disable text field and Send button
      tfSentence.setDisable(true);
      btnSend.setDisable(true);
   }

   /**
    * doSend - Send button'
    */
   /*    
   private void doSend() {
      // Get the sentence, send to server, wait for reply
      pwt.println(tfSentence.getText());
      pwt.flush();
      taLog.appendText("Sent: " + tfSentence.getText() + "\n");
      tfSentence.setText("");
      String reply = scn.nextLine();
      taLog.appendText("Reply: " + reply + "\n");
   }
 */
}