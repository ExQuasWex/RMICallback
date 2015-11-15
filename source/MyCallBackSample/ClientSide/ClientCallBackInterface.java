package MyCallBackSample.ClientSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Didoy on 11/14/2015.
 */
public interface ClientCallBackInterface extends Remote{

    public boolean imAlive(String name) throws RemoteException;
    public void greetingFromServer(String message) throws RemoteException;


}
