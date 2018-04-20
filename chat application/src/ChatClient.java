import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

     public class ChatClient {
       static JFrame chatWindow = new JFrame("Chat Application"); //outer window on which we will add our components
       static JTextArea chatArea = new JTextArea(22, 40); //text area obect to display the message
       static JTextField textField = new JTextField(40); // used for entering message
       static JLabel blankLabel = new JLabel("           "); //provides space b/w chatArea and textField
       static JButton sendButton = new JButton("Send");    // button to send message
       static BufferedReader in;
       static PrintWriter out;
       static JLabel nameLabel = new JLabel("         ");

               ChatClient(){   //constructor of the class
                   chatWindow.setLayout(new FlowLayout()); //components are arranged onto JFrame
                   chatWindow.add(nameLabel);
                   chatWindow.add(new JScrollPane(chatArea)); //used to scroll-down
                   chatWindow.add(blankLabel);
                   chatWindow.add(textField);
                   chatWindow.add(sendButton);
                   chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //closes the window
                   chatWindow.setSize(475, 500);                       //when cross butoon is pressed
                   chatWindow.setVisible(true);
                   textField.setEditable(false);
                   chatArea.setEditable(false);

        sendButton.addActionListener(new Listener()); //adding addActionListener to send button
        textField.addActionListener(new Listener()); //addActionListener to enter key
    }
           void startChat() throws Exception{   //it contains the main logic to start a chat
              String ipAddress = JOptionPane.showInputDialog( //dialogue box is showed on
              chatWindow,                                    //screen to get ip address
             "Enter IP Address:",
             "IP Address Required!!",
              JOptionPane.PLAIN_MESSAGE);

                 Socket soc = new Socket(ipAddress, 9806); //socket object
                 in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                 out = new PrintWriter(soc.getOutputStream(), true);
        while (true){
         String str = in.readLine();
           if (str.equals("NAMEREQUIRED")){
           String name = JOptionPane.showInputDialog(
                       chatWindow,
                       "Enter a unique name:",
                       "Name Required!!",
                       JOptionPane.PLAIN_MESSAGE);
               out.println(name);
           }
           else if(str.equals("NAMEALREADYEXISTS")){
           String name = JOptionPane.showInputDialog(
                       chatWindow,
                       "Enter another name:",
                       "Name Already Exits!!",
                       JOptionPane.WARNING_MESSAGE);
               out.println(name);
           }
           else if (str.startsWith("NAMEACCEPTED")){
               textField.setEditable(true);
               nameLabel.setText("You are logged in as: "+str.substring(12));
           }
           else{
               chatArea.append(str + "\n");
           }
       }
   }
      public static void main(String[] args) throws Exception {
                      ChatClient client = new ChatClient();
                    client.startChat();
      }
}
                 class Listener implements ActionListener{
                     @Override
                     public void actionPerformed(ActionEvent e) {
                     ChatClient.out.println(ChatClient.textField.getText());
                    ChatClient.textField.setText("");
      }

}
