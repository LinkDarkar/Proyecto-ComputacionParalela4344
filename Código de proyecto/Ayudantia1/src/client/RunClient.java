package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;

// import java.awt.*;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;

public class RunClient
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Client client = new Client();

	public static void main(String[] args) throws NotBoundException, IOException
	{
		// client.startClient();
        boolean hasThingsToDo = true;
        boolean loggedIn = false;
        int userInput;
        
        while (hasThingsToDo)
        {
            // que asco
            if (loggedIn == true)
            {
                System.out.println("Amazing Menu that has other options that work: \n1) Show Person list \n2) Add Person to the list \n3) Quit");
            }
            else
            {
                System.out.println("Menu: \n1) login \n2) sign in \n3) Quit");
            }

            try
            {
                userInput = Integer.parseInt(br.readLine());
            }
            catch (NumberFormatException exception)
            {
                System.out.println("Invalid input. Try again with a valid number.");
                continue;
            }

            if (loggedIn == true)
            {
                // show other menu
                
                switch (userInput)
                {
                    case 1:
                        // client.printUsers();
                        System.err.println("logged in!");
                        break;
                    case 2:
                        // AddPerson(client);
                        System.err.println("rip bozo lol, lmao");
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
            else
            {
                switch (userInput)
                {
                    case 1:
                        // client.printUsers();
                        System.err.println("logged in!");
                        loggedIn = true;
                        break;
                    case 2:
                        // AddPerson(client);
                        System.err.println("rip bozo lol, lmao");
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
	}

    private static void AddPerson(Client client) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String newName = "";
        int newAge = 0;

        boolean hasThingsToDo = true;
        int index = 0;
        while(hasThingsToDo)
        {
            if (index == 0)
            {
                System.out.print("Enter name: ");
                newName = br.readLine();
                System.out.println("");
                index = 1;
            }
            else if (index == 1)
            {
                try
                {
                    System.out.print("Enter age: ");
                    newAge = Integer.parseInt(br.readLine());
                    System.out.println("");
                }
                catch (NumberFormatException exception)
                {
                    System.out.println("Invalid input. Try again with a valid number.");
                    continue;
                }
                index = 2;
            }
            else
            {
                hasThingsToDo = false;
                // client.createPerson(newName, newAge);
                System.out.println("Creation succesfull");
                System.out.println("Returning to menu...");
            }
        }
    }
}
