package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import common.User;

// import javax.swing.JFrame;
// import java.awt.*;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;

public class RunClient
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Client client = new Client();
    static boolean debugFlag = true;

	public static void main(String[] args) throws NotBoundException, IOException
	{
		client.startClient();
        boolean hasThingsToDo = true;
        boolean loggedIn = false;
        int userInput;
        String tempUserName = null;
        String tempPassword = null;
        int tempLocation;
        User currentUser = null;
        int productMenuSize;
        
        while (hasThingsToDo)
        {
            try
            {
                if (loggedIn == true)
                {
                    // show other menu
                    // \n2) Buy Product
                    System.out.println("Store: \n1) See products \n2) Quit");
                    userInput = Integer.parseInt(br.readLine());
                    switch (userInput)
                    {
                        case 1:
                            productMenuSize = client.printProducts(currentUser.getLocation());
                            System.err.println("These are the current available products");
                            break;
/*
                        case 2:
                            // AddPerson(client);
                            break;
*/
                        case 2:
                            hasThingsToDo = false;
                            System.out.println("Quitting...");
                            break;
                        default:
                            System.out.println("Not a valid option.");
                            break;
                    }
                }
                else
                {
                    System.out.println("Menu: \n1) login \n2) sign in \n3) Quit");
                    userInput = Integer.parseInt(br.readLine());
                    switch (userInput)
                    {
                        case 1:
                            System.out.println("Type username:");
                            tempUserName = br.readLine();
                            System.out.println("Type password:");
                            tempPassword = br.readLine();
                            
                            currentUser = client.Login(tempUserName, tempPassword);
                            if (currentUser != null)
                            {
                                System.err.println("logged in!");
                                loggedIn = true;
                                client.getDataFromApi();
                            }
                            else if (debugFlag)
                            {
                                System.err.println("LOGGED IN BY DEBUG");
                                currentUser = client.Login("john2hu", "2hu");
                                loggedIn = true;
                                client.getDataFromApi();
                            }
                            else
                            {
                                System.err.println("Couldn't log in. Username or Password were incorrect.");
                                System.err.println("If you don't have an account use Sign In to create one.");
                            }
                            break;
                        case 2:
                            System.out.println("Type username:");
                            tempUserName = br.readLine();
                            System.out.println("Type password:");
                            tempPassword = br.readLine();
                            System.out.println("Type location 0 = CHILE | 1 = US | 2 = EUROPE | 3 = BRAZIL | 4 = ARGENTINA | 5 = URUGUAY");
                            while (true)
                            {
                                try
                                {
                                    tempLocation = Integer.parseInt(br.readLine());
                                }
                                catch (NumberFormatException numberFormatException)
                                {
                                    System.err.println("Invalid input. Try again with a valid number.");
                                    continue;
                                }
                                break;
                            }
                            if (client.CreateAccount(tempUserName, tempPassword, tempLocation))
                            {
                                System.out.println("Sign up succesful. Now you can go to login to enter.");
                            }
                            else
                            {
                                System.out.println("Sign up error. Try again with a different username.");
                            }
                            break;
                        case 3:
                            hasThingsToDo = false;
                            System.out.println("Quitting...");
                            break;
                        default:
                            System.out.println("Not a valid option.");
                            break;
                    }
                }
            }
            catch (NumberFormatException exception)
            {
                System.out.println("Invalid input. Try again with a valid number.");
                continue;
            }
        }
	}
}
