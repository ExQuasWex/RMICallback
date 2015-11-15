package MyCallBackSample.ServerSide;

import MyCallBackSample.ClientSide.ClientCallBackInterface;
import MyCallBackSample.ClientSide.onlineClientList;
import MyCallBackSample.Constant;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Didoy on 11/14/2015.
 */
public class Server extends UnicastRemoteObject{

    private  static  serverImp serverimp;
    private static  ClientCallBackInterface cb;


    protected Server() throws RemoteException {
    }

    public static void main(String[] args) {


        try {
            serverimp = new serverImp();
            Registry reg = LocateRegistry.createRegistry(Constant.port);
            reg.bind(Constant.host,serverimp);

            System.out.println("server is now Ready");

            checkOnlineStatus();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    protected static void checkOnlineStatus(){


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {


                    while (true){
                        int x = 0;

                        Thread.sleep(5000);

                        ArrayList clientList = serverimp.getOnlineList();


                        System.out.println("checking online status ...... ");
                        System.out.println(" number of Online: " + clientList.size());
                        try {

                            while (x < clientList.size() ){

                                onlineClientList client = (onlineClientList) clientList.get(x);
                                Registry reg =  LocateRegistry.getRegistry(client.getIp().toString(), Constant.port);
                                 cb = (ClientCallBackInterface) reg.lookup(Constant.host);

                                cb.imAlive(client.getName());

                                System.setProperty("java.rmi.server.hostname", client.getIp().toString() );


                                if (clientList.size() == x)
                                {
                                    x = 0;

                                }else
                                {
                                    x++;
                                }
                            }

                        } catch (RemoteException e) {
                            Object obj = clientList.get(x);
                            clientList.remove(obj);
                            System.out.println(obj.toString() + " is Offline");

                            e.printStackTrace();
                        } catch (NotBoundException e) {
                            e.printStackTrace();
                        }

                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        t.start();
    }

}
