package MyCallBackSample.ClientSide;

import java.net.InetAddress;

/**
 * Created by reiner on 11/15/2015.
 */
public class onlineClientList {

    private String name;
    private InetAddress ip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public onlineClientList(String name, InetAddress ip){
        this.name = name;
        this.ip = ip;

    }

}
