package memo.rmi.service.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import memo.rmi.service.Service;

public class ServiceImpl implements Service {
	
	public String getTest() throws RemoteException {
		return "Hello,test!";
	}

	public static void main(String[] args) throws RemoteException {
		ServiceImpl t=new ServiceImpl();  
		Service tt=(Service)UnicastRemoteObject.exportObject(t,0);  
		Registry registry = LocateRegistry.createRegistry(65533);  
		registry.rebind("test", tt);  
		System.out.println("server is start");  
	}
}
