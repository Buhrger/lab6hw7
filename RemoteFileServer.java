import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.text.*;


public class RemoteFileServer extends JFrame implements ActionListener {
   // GUI Components
   public JLabel jlLog = new JLabel("Log:");
   public JTextArea jtaLog = new JTextArea(10, 35);
   private JButton jbStart = new JButton("Start");
   
   // Socket stuff
   private ServerSocket sSocket = null;
   public static final int SERVER_PORT = 32001;
   
   private Thread sThread = null;
   private boolean running = true;
   
   /**
    * main program
    */
   public static void main(String[] args) {
      new RemoteFileServer();
   }
   
   /**
    * Constructor, draw and set up GUI
    * Do server stuff
    */
   public RemoteFileServer() {
      // Window setup
      this.setTitle("Remote File Server (J. Burger, C. McManus)");
      this.setSize(600, 300);
      this.setLocation(800, 100);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      DefaultCaret caret = (DefaultCaret)jtaLog.getCaret();
      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
      jtaLog.setLineWrap(true);
      jtaLog.setWrapStyleWord(true);
      JScrollPane jsp = new JScrollPane(jtaLog);
      this.add(jsp);
      
      // CENTER components
      this.add(jlLog);
      this.add(new JScrollPane(jtaLog));
      
      // NORTH COMPONENTS
      JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      jpNorth.add(jbStart);
      this.add(jpNorth, BorderLayout.NORTH);
      jbStart.addActionListener(this);
      
      this.setVisible(true);
      //doServerWork();
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      switch(ae.getActionCommand())
      {
         case "Start":
            doStart();
            break;
         case "Stop":
            doStop();
            break;
      }
   }
   
   public void doStart()
   {
      sThread = new ServerThread();
      sThread.start();
      running = true;
      jbStart.setText("Stop");
      jtaLog.append("Server Running...\n");
   }
   
   public void doStop()
   {
      jbStart.setText("Start");
      //sThread.kill();
      jtaLog.append("Server Stopped...\n");
   }
   
   /** ServerThread - does the basic non-GUI work of the server */
   class ServerThread extends Thread {
      public void run()
      {
         // Claim the port and start listening for new connections
         try {
            sSocket = new ServerSocket(SERVER_PORT);
         }
         catch(IOException ioe) {
            jtaLog.append("IO Exception (1): "+ ioe);
            return;
         }
      
         while(running){
            Socket cSocket = null;
            //label = cSocket.getInetAddress().getHostAddress() + ":" + cSocket.getPort() + "::";
            try {
               cSocket = sSocket.accept();
            }
            catch(IOException ioe) {
               jtaLog.append("IO Exception (2): "+ ioe);
               return;
            }
         
            jtaLog.append("Client connected!\n");
            try{
                  ClientThread ct = new ClientThread(cSocket);
                  ct.start();
               }
            catch(Exception e) {return;}
         } 
      }
      
      
   }//End of ServerThread
    
   class ClientThread extends Thread 
   {
      private Socket cSocket;
      
      public ClientThread(Socket _cSocket)
      {
         cSocket = _cSocket;
      }
      
      public void run()
      {
         try{
            Scanner scn = new Scanner(new InputStreamReader(cSocket.getInputStream()));
            PrintWriter pwt = new PrintWriter(new OutputStreamWriter(cSocket.getOutputStream()));
                
            while(true) {
               String sentence = scn.nextLine();
               synchronized(jtaLog){
                  jtaLog.append("Sentence: " + sentence + "\n");
               }
               String reply = sentence.toUpperCase();
               pwt.println(reply);
               pwt.flush(); 
               synchronized(jtaLog)
               {
                  jtaLog.append("Reply: " + reply + "\n");
               }
            }
         }
         catch(Exception e) {}
      } 
         public void kill()
      {
         running = false;
         try {
            sSocket.close();
         }
         catch(Exception e) {}
      }
      
   }//End of ClientThread

      
}// End of ClientThread