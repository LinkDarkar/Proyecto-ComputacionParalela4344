package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import common.ServerInterface;
import common.User;
import common.LOCATION;
import common.Product;

public class Client
{
	public ServerInterface server;
	public Client() {	}

	public void startClient() throws RemoteException, NotBoundException
	{
		Registry registry = LocateRegistry.getRegistry("localhost", 5050);
		System.out.println("registry : " + registry.hashCode());
		this.server = (ServerInterface) registry.lookup("server");
	}
	
	public int printProducts (LOCATION location) throws RemoteException
	{
		// For some reason the line below will crash if Products doesn't implements Serializable... no idea why
		// man this language sucks
		ArrayList<Product> productList = this.server.getProducts();
		// ArrayList<String> currencyList;
		Double exchange = this.server.GetExchange(location.value);
		System.out.println("Product Number | Product Name	| Product Description |	Product Price");
		for (int i = 0; i < productList.size(); i++)
		{	
			System.out.print(i + ") " + productList.get(i).getProductName() + " " + productList.get(i).getProductDescription());
			if (location.value != 0)
			{
				System.out.print(" " + (productList.get(i).getProductPrice() / exchange));
			}
			else
			{
				System.out.print(" " + (productList.get(i).getProductPrice()));
			}

			// QUE SI QUE ESTA FEO LUEGO SE CAMBIA
			// maybe an array??????? and get the string from the value?
			// would have to edit 
			if (location.value == 0)
			{
				System.out.println(" CLP");
			}
			if (location.value == 1)
			{
				System.out.println(" USD");
			}
			if (location.value == 2)
			{
				System.out.println(" EUR");
			}
			if (location.value == 3)
			{
				System.out.println(" BRL");
			}
			if (location.value == 4)
			{
				System.out.println(" ARS");
			}
			if (location.value == 5)
			{
				System.out.println(" UYU");
			}
		}
		return productList.size();
	}
	
	public ArrayList<String> getDataFromApi() throws RemoteException
	{
		return server.GetDataFromApi();
	}

	public User Login (String userName, String password) throws RemoteException
	{
		return server.Login(userName, password);
	}
	
	public boolean CreateAccount (String userName, String password, int location) throws RemoteException
	{
		return server.CreateAccount(userName, password, location);
	}
}
