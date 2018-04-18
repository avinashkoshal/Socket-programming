import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
     public class Client {
          public static void main(String[] args) throws Exception{
                   System.out.println("Client started");
                   Socket soc = new Socket("localhost",9806); //creating a socket with two arguments 1st is ip which is localhost and 2nd is port no.
                   BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));  //used to get input from user
                   BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));  //used to get input from Socket soc
                   PrintWriter out = new PrintWriter(soc.getOutputStream(), true);   //this will print out the data

                  int option = 0;
                  int num1 = 0;
                  int num2 = 0;
                  do{            //do while loop is used because we want to display the below code every time
                        System.out.println("Choose an operation:");
                        System.out.println("1. Addition");
                        System.out.println("2. Subtraction");
                        System.out.println("3. Multiplication");
                        System.out.println("4. Division");
                        System.out.println("5. Exit");
                        System.out.println("Enter an option:");
                        option = Integer.parseInt(userInput.readLine());//this will read the input for option
                                                                  //readline method returns a string that's why we used Integer.parseInt

                       if(option != 5){
                              System.out.println("Enter first number");
                              num1 = Integer.parseInt(userInput.readLine());  //this will read the input for num1

                              System.out.println("Enter second number");
                              num2 = Integer.parseInt(userInput.readLine());  //this will read the input for num2

                              out.println(option+":"+num1+":"+num2);//out is a PrintWriter object
                                                             //here colon(:) is used as a delimiter as it separates the values
                        }
                        else
                        {
                              out.println(option+":0:0");
                              break;
                        }
                      String answer = in.readLine();   //this will capture the result from the server
                      System.out.println("Server says: "+answer);  //this will show the result
                      System.out.println("");
                  }while(true);
                  System.out.println("Client terminated");
      }
}
