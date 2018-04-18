  import java.io.BufferedReader;
  import java.io.InputStreamReader;
  import java.io.PrintWriter;
  import java.net.ServerSocket;
  import java.net.Socket;
       public class Server {
            public static void main(String[] args) throws Exception{
                  System.out.println("Server started");
                  ServerSocket ss = new ServerSocket(9806); //creating a ServerSocket ss and giving the port value as= 9806
                  System.out.println("Waiting for client...");
                  Socket soc = ss.accept();   // creating a socket soc which accepts the client
                  System.out.println("Connection established");

                  BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream())); //reading data from client
                  PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

                  while (true){
                          String str[] = in.readLine().split(":"); //split fn divides the data in different parts depend upon delimiter and store into string aaray
                          int option = Integer.parseInt(str[0]);  //value from client is stored
                          int num1 = Integer.parseInt(str[1]);   //   //       //          //
                          int num2 = Integer.parseInt(str[2]);  //   //       //          //
                          String result = "";      // result variable used to send data back to client
                          int flag = 0;
                          switch(option){
                          case 1: result = "Addition is: "+(num1+num2);break;
                          case 2: result = "Subtraction is: "+(num1-num2);break;
                          case 3: result = "Multiplication is: "+(num1*num2);break;
                          case 4: result = "Division is: "+(num1/num2);break;
                          case 5: flag =1;break;
                          default:break;
                          }
                          if(flag == 1)

                          {
                              break;
                          }
                          out.println(result); //this will sent back the resu to client
                 }
                  System.out.println("Server terminated");
           }
}
