package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote
{
	ArrayList<Product> getProducts() throws RemoteException;
	User Login(String userName, String password) throws RemoteException;
	boolean CreateAccount(String userName, String password, int location) throws RemoteException;
	ArrayList<String> GetDataFromApi() throws RemoteException;
	Double GetExchange(int location) throws RemoteException;
}
