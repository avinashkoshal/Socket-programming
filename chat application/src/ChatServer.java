import java.io.*;
import java.net.*;
import java.util.ArrayList;

    public class ChatServer {  //main class of the program

      static ArrayList<String> userNames = new ArrayList<String>(); //this will store the usernames entered by the clients
      static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

      public static void main(String[] args) throws Exception{
               System.out.println("Waiting for clients...");
               ServerSocket ss = new ServerSocket(9806); //creating a ss obect of ServerSocket
                                                        // the port no. used is 9806
           while (true){  //this while loop is used to wait for the clients
               Socket soc = ss.accept(); //the Socket object soc will axxept the incoming clients
               System.out.println("Connection established");
               ConversationHandler handler = new ConversationHandler(soc);  //creating a handler of ConversationHandler class
               handler.start();    //starting the handler
            }
      }
}

class ConversationHandler extends Thread{   //new class which extends the thred
                                          //there are two ways to create a thread
                                         // 1) implement runnble interface 2)extend thread class
          Socket socket;
          BufferedReader in;            //for input
          PrintWriter out;             // for output
          String name;
          PrintWriter pw;
          static FileWriter fw; //FileWriter to log chat history
          static BufferedWriter bw;

   public ConversationHandler(Socket socket) throws IOException {  //ConversationHandler class
                                            //separate thread class for different clients
        this.socket = socket;
        fw = new FileWriter("/root/Desktop/ChatServer-Logs.txt",true);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw,true);
    }
     public void run(){  //run method when we use it the code inside this method gets executed
        try{            // exceptio handler
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            int count = 0;   //initial the count is set to 0
            while (true){   // so that the else part will be executed and ask the user to enter the name
            if(count > 0){
            out.println("NAMEALREADYEXISTS");
               }
               else
               {
                    out.println("NAMEREQUIRED");
               }
               name = in.readLine();  //this will read the name provided by the client
               if (name == null){
                   return;          //this will again take the user back to ener the name
               }
               if (!ChatServer.userNames.contains(name)){ //it checks that the username is already present
                                                        // in the ArrayList or not

                  ChatServer.userNames.add(name);
                  break;
               }
             count++;
           }
            out.println("NAMEACCEPTED"+name);
            ChatServer.printWriters.add(out);
            while (true)
            {
                String message = in.readLine();
                if (message == null)
                {
                    return;
                }
                pw.println(name + ": " + message);
                for (PrintWriter writer : ChatServer.printWriters) {
                writer.println(name + ": " + message);
                  }
              }
          }
        catch (Exception e){
            System.out.println(e);
        }
   }
}
