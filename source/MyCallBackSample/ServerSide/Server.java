package MyCallBackSample.ServerSide;

import MyCallBackSample.ClientSide.ClientCallBackInterface;
import MyCallBackSample.Constant;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Didoy on 11/14/2015.
 */
public class Server {

    private  static  serverImp serverimp;
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

                        Thread.sleep(3000);

                        ArrayList onlineClientList = serverimp.getOnlineList();
                        ClientCallBackInterface cb = serverimp.getCallbackInterface();

                        System.out.println("checking online status ...... ");
                        System.out.println(" number of Online: " + onlineClientList.size());
                        try {

                            while (x < onlineClientList.size() ){

                                cb.imAlive(onlineClientList.get(x).toString());

                                if (onlineClientList.size() == x)
                                {
                                    x = 0;

                                }else
                                {
                                    x++;
                                }
                            }

                        } catch (RemoteException e) {
                            Object obj = onlineClientList.get(x);
                            onlineClientList.remove(obj);
                            System.out.println(obj.toString() + " is Offline");
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
