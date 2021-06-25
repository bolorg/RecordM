package pers.adlered.recordm;

import com.github.timeloveboy.moeserver.Server;
import com.github.timeloveboy.moeserver.ServerDriver.netty.nettyServer;

public class App {

    public static void main(String[] args) throws Exception {
        Server server = Server.getInstance();
        nettyServer nettyServer = new nettyServer() {

        };
        server.RegisterDriver(nettyServer);
        server.RegisterModulePath("pers.adlered.recordm.modules").Static("/static", "/Users/chenhui").SetPort(4399);
        server.Run();
    }

}
