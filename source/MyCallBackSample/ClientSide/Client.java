package MyCallBackSample.ClientSide;

import MyCallBackSample.Constant;
import MyCallBackSample.ServerSide.ServerInterface;

import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by Didoy on 11/14/2015.
 */
public class Client {


    private static ServerInterface server;
    private static String hostIP = "192.168.1.106";
    private static  InetAddress machineIp = null;

    public static void main(String[] args) {


        machineIp = getIpAddress();


        try {

           // Registry reg = LocateRegistry.getRegistry("localhost",Constant.port);
            System.setProperty("java.rmi.server.hostname",hostIP);
            Registry reg =LocateRegistry.getRegistry(hostIP,Constant.port);
            server = (ServerInterface) reg.lookup(Constant.host);


            // server = (ServerInterface) reg.lookup(Constant.host);

            System.out.println("Client is now ready");

            ClientCallBackInterface clientCallBackObj = new ClientImp();

            showMenu(server, clientCallBackObj);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static void showMenu(ServerInterface serverInterface, ClientCallBackInterface callBackInterface) throws RemoteException {
        int choice = 0;
        InputStreamReader is = new InputStreamReader(System.in);
        Scanner input = new Scanner(is);

        // String var
        String name = "";
        String pass;


        while (choice!=4){
            System.out.println("------------------------------------------------------------\n \n");
            System.out.println("Press menu [1] to Login [2] to Logout [3] to exit");
            choice = input.nextInt();

            if (choice == 1){
                System.out.println("ENTER NAME: ");
                name = input.next();

                System.out.println("ENTER PASSWORD: ");
                pass = input.next();
                try {


                   boolean isRegistered =  serverInterface.Register(name,pass,callBackInterface, machineIp);
                        if (isRegistered){
                            System.out.println(name + " successfully Registered");
                        }else{
                            System.out.println("unsuccessful registed, Please try again later");
                        }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else if (choice == 2){
                System.out.println("Enter your name: ");
                String name1 = input.next();

                if(server.Logout(name1)){
                    System.out.println(name1 + " successfully Logout");
                }else {

                    System.out.println("failed to logout");
                }

            }else if (choice == 3){
                System.exit(1);
            }

        }



    }

    private static  InetAddress getIpAddress(){
        InetAddress ip = null;

        try {
             ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return  ip;
    }


}
