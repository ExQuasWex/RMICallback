package MyCallBackSample.ClientSide;

import MyCallBackSample.Constant;
import MyCallBackSample.ServerSide.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Didoy on 11/14/2015.
 */
public class ClientImp extends UnicastRemoteObject implements ClientCallBackInterface {


    protected ClientImp() throws RemoteException {

}

    @Override
    public boolean imAlive(String name) {
        return true;
    }

    @Override
    public void greetingFromServer(String message) throws RemoteException {
        System.out.println(message);
    }

}
