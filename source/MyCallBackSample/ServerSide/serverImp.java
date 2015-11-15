package MyCallBackSample.ServerSide;

import MyCallBackSample.ClientSide.ClientCallBackInterface;
import MyCallBackSample.ClientSide.onlineClientList;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Didoy on 11/14/2015.
 */
public class serverImp extends UnicastRemoteObject implements ServerInterface {

    private ArrayList<onlineClientList> onlineClient ;
    private ClientCallBackInterface clientCB;

    protected serverImp() throws RemoteException {
        onlineClient = new ArrayList<onlineClientList>();
    }

    @Override
    public boolean Register(String name, String password, ClientCallBackInterface clientCB, InetAddress ip) throws RemoteException {

        if (password.equals("password")){
            this.clientCB = clientCB;
            onlineClientList client = new onlineClientList(name,ip);
            onlineClient.add(client);
            System.out.println("Client: " + name + " is Added to our List");

           this.clientCB.greetingFromServer("hi "+ name + " You are now listed in the server");

            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean Logout(String name) {
        if (onlineClient.contains(name)){
            onlineClient.remove(name);

            System.out.println(name + " is Removed to the online List");
            System.out.println("current Online: " + onlineClient.size());
            return true;

        }else {
            return false;

        }

    }

    public serverImp getInstance(){
        return this;
    }

    public ArrayList getOnlineList(){
        return onlineClient;
    }

    public ClientCallBackInterface getCallbackInterface(){
        return clientCB;
    }

}
