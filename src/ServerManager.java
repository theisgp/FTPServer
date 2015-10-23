import org.apache.ftpserver.ConnectionConfig;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;

import javax.naming.ldap.Control;

public class ServerManager {

    private Controller controller;

    private FtpServer server;
    private FtpServerFactory factory;
    private ListenerFactory listenerFactory;

    private MasterUserManager masterUserManager;

    private int port;

    public ServerManager(Controller controller) {
        port = 2221;
        this.masterUserManager = new MasterUserManager();
        this.controller = controller;
    }

    public void startServer() throws FtpException {

        masterUserManager.getInnerUserManager().save(masterUserManager.getMyUser(1));

        listenerFactory = new ListenerFactory();
        listenerFactory.setPort(port);

        factory = new FtpServerFactory();
        factory.setUserManager(masterUserManager.getInnerUserManager());
        factory.addListener("default", listenerFactory.createListener());

        server = factory.createServer();
        server.start();
    }

    public String getServerInformation() {
        String returnString = "Username: " + masterUserManager.getMyUser(0).getName() + "\n";
        returnString += "Password: " + masterUserManager.getMyUser(0).getPassword() + "\n";
        return returnString;
    }

    public MasterUserManager getMasterUserManager(){
        return masterUserManager;
    }


}