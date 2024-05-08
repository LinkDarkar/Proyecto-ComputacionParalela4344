package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote
{
	// have functions that the client is going to be able to access, preferably in the order that
	// are going to be presented to them
	// TODO change them to what we need
	ArrayList<User> getUsers() throws RemoteException;
	ArrayList<String> GetDataFromApi() throws RemoteException;
	Double GetExchange(String json) throws RemoteException;
}
