package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.ServerInterface;
import common.User;

public class RunServer
{
	public static void main(String[] args) throws RemoteException, AlreadyBoundException
	{
		ServerInterface server = new ServerImpl();
		Registry registry = LocateRegistry.createRegistry(5000);
		registry.bind("server", server);
		System.out.println("Servidor arriba!!");
	}
}
