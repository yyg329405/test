package memo.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import memo.rmi.service.Service;

public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 65533);
			Service t = (Service) registry.lookup("test");
			System.out.println("client:" + t.getTest());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
