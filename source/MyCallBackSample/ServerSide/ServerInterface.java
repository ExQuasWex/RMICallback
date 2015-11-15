package MyCallBackSample.ServerSide;

import MyCallBackSample.ClientSide.ClientCallBackInterface;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Didoy on 11/14/2015.
 */
public interface ServerInterface extends Remote{

    public boolean Register(String name, String password, ClientCallBackInterface clientCB, String ip) throws RemoteException;
    public boolean Logout(String name) throws RemoteException;

}
