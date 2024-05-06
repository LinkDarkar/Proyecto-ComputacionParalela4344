package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RunCliente
{
	public static void main(String[] args) throws NotBoundException, IOException
	{
		Cliente cliente = new Cliente();
		cliente.startCliente();
		System.out.println("Cliente arriba!!");
		
		System.out.println("Cliente arriba!! ");
		System.out.println("### Bienvenido a Acme Corp ### ");
		System.out.println("¿Qué quiere hacer hoy? ");
		System.out.println("Para ver la Base de Datos de empleados, aprete 1. ");
		System.out.println("Para obtener los valores de la UF, aprete 2. ");
		System.out.println("Para obtener datos de la API, aprete 3. ");
		
		while(true)
		{	
			System.out.println("Ingrese la opción: ");
			char bufferInput = (char) System.in.read();
			
			if (bufferInput == '1')
			{
				cliente.getPersonas();
				System.out.println("###");
			}
			else if (bufferInput == '2')
			{
				cliente.getUF();
				System.out.println("###");
			}
			else if (bufferInput == '3')
			{
				System.out.println(cliente.getDataFromApi());
				System.out.println("###");
			}
			// Limpiar el buffer
            System.in.skip(System.in.available());
		}
	}
/*
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Client client = new Client();

    public static void main (String[] args) throws RemoteException, NotBoundException, IOException
    {
        client.startClient();
        
        boolean hasThingsToDo = true;
        int userInput;

        System.out.println("Menu: \n1) Show Person list \n2) Add Person to the list \n3) Quit");
        while (hasThingsToDo)
        {
            try
            {
                userInput = Integer.parseInt(br.readLine());
            }
            catch (NumberFormatException exception)
            {
                System.out.println("Invalid input. Try again with a valid number.");
                continue;
            }
            
            switch (userInput)
            {
                case 1:
                    PrintPersons(client);
                    System.out.println("Menu: \n1) Show Person list \n2) Add Person to the list \n3) Quit");
                    break;
                case 2:
                    AddPerson(client);
                    System.out.println("Menu: \n1) Show Person list \n2) Add Person to the list \n3) Quit");
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

    private static void PrintPersons(Client client) throws RemoteException
    {
        ArrayList<Person> BD_personas = client.getPersonas();
        for (int i = 0; i < BD_personas.size(); i += 1)
    	{
            System.out.println("Person number: " + i);
            System.out.println("Name: " + BD_personas.get(i).getName());
            System.out.println("Age: " + BD_personas.get(i).getAge());
            System.out.println("-------------------------------------");
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
                client.createPerson(newName, newAge);
                System.out.println("Creation succesfull");
                System.out.println("Returning to menu...");
            }
        }
    }
*/
}
