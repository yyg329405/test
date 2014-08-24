package memo.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
	public String getTest() throws RemoteException; 
}
