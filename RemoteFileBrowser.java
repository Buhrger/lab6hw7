import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;
import java.util.*;

/* 
 * File server
 *
 * @authors Josh Burger, Dustin Rochette
 * @version 3/7/18
 */
 
public class RemoteFileBrowser extends JFrame implements ActionListener
{
   // Attributes declared here
   JLabel jlName = new JLabel("Server:");
   JTextField jtfName = new JTextField(20);
   JButton jbConnect = new JButton("Connect");
   
   JButton jbList = new JButton("List");
   JButton jbCD = new JButton("Ch Dir");
   JButton jbUpload = new JButton("Upload");
   JButton jbDownload = new JButton("Download");
   
   JLabel jlLog = new JLabel("Log:");
   JTextArea jtaLog = new JTextArea(10, 35);
   
   JPanel jpNorth = new JPanel();
   JPanel jpCenter = new JPanel();
   
   public static final int SERVER_PORT = 32001;
   private Socket socket = null;
   private FileOutputStream fos = null;
   private FileInputStream fis = null;
   
   // Main program
   public static void main(String [] args)
   {
      new RemoteFileBrowser();
   }
      // Constructor
   public RemoteFileBrowser()
   {
      setupWindow();
      jpNorth.setLayout(new GridLayout(0, 1));
      
      JPanel jpRow1 = new JPanel();
         jpRow1.setLayout(new FlowLayout(FlowLayout.CENTER));
         jpRow1.add(jlName);
         jpRow1.add(jtfName);
         jpRow1.add(jbConnect);
         jpNorth.add(jpRow1);
         
      JPanel jpRow2 = new JPanel();
         jpRow2.setLayout(new FlowLayout(FlowLayout.CENTER));
         jpRow2.add(jbList);
         jpRow2.add(jbCD);
         jpRow2.add(jbUpload);
         jpRow2.add(jbDownload);
         jpNorth.add(jpRow2);
      this.add(jpNorth, BorderLayout.NORTH);
      
      DefaultCaret caret = (DefaultCaret)jtaLog.getCaret();
      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
      jtaLog.setLineWrap(true);
      jtaLog.setWrapStyleWord(true);
      JScrollPane jsp = new JScrollPane(jtaLog);
      this.add(jsp);
         
      jpCenter.add(jlLog);
      jpCenter.add(jtaLog);
      this.add(jpCenter, BorderLayout.CENTER);
         
      jbConnect.addActionListener(this);
      jbList.addActionListener(this);
      jbCD.addActionListener(this);
      jbUpload.addActionListener(this);
      jbDownload.addActionListener(this);     
      jtaLog.setEditable(false);
      
      
      //jbList.setEnabled(false);
      //jbCD.setEnabled(false);
      //jbUpload.setEnabled(false);
      //jbDownload.setEnabled(false);
         
      this.setVisible(true);
   }
      
   public void setupWindow()
   {
      this.setTitle("Remote File Browser (J. Burger, C. McManus)");
      this.setSize(600, 325);
      this.setLocation(100, 100);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
         
   public void actionPerformed(ActionEvent ae) {
      switch(ae.getActionCommand()) {
         case "Connect":
            doConnect();
            break;
         case "Disconnect":
            doDisconnect();
            break;
         case "List":
            doList();
            break;
         case "Ch Dir":
            doCD();
            break;
         case "Upload":
            doUpload();
            break;
         case "Download":
            doDownload();
            break;
      }
   }
   
   private void doConnect()
   {
      try {
         socket = new Socket(jtfName.getText(), SERVER_PORT);
               }
      catch(IOException ioe) {
         jtaLog.append("IO Exception: " + ioe + "\n");
         return;
      }
      jtaLog.append("Connected!\n");
      jbConnect.setText("Disconnect");
      jbList.setEnabled(true);
      jbCD.setEnabled(true);
      jbUpload.setEnabled(true);
      jbDownload.setEnabled(true);
      
      
   }
   
   private void doDisconnect()
   {
      try {
         socket.close();
      }
      catch(IOException ioe) {
         jtaLog.append("IO Exception: " + ioe + "\n");
         return;
      }
      jbConnect.setText("Connect");
      jbList.setEnabled(false);
      jbCD.setEnabled(false);
      jbUpload.setEnabled(false);
      jbDownload.setEnabled(false);
         }
         
      public void doUpload()
      {
         JOptionPane.showInputDialog(null, 
         "Input Filename: ", "File Upload", 
         JOptionPane.QUESTION_MESSAGE);

      }
      
      public void doDownload()
      {
         JOptionPane.showInputDialog(null, 
         "Input Filename: ", "File Download", 
         JOptionPane.QUESTION_MESSAGE);
      }
      
      public void doList()
      {
         File workingDirectory = new File(System.getProperty("user.dir"));
         jtaLog.append(Arrays.toString(workingDirectory.list()));
      }
      
      public void doCD()
      {
         JOptionPane.showInputDialog(null, 
         "Input New Directory: ", "Change Directory", 
         JOptionPane.QUESTION_MESSAGE);
      }
   
 

   
}