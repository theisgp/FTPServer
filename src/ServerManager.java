import com.sun.corba.se.spi.activation.Server;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

public class ServerManager {

    private UserManager userManager;
    private LocalUserManager localUserManager;

    private int port;

    public ServerManager(){
        port = 2221;
        this.localUserManager = new LocalUserManager();
        this.userManager = localUserManager.getUserManager();

    }
    public static void main(String[] args){
        ServerManager serverManger = new ServerManager();
        try {
            serverManger.startServer();
            System.out.println("service successfully started");
        } catch (FtpException e) {
            System.out.println("Server failed starting...");
            e.printStackTrace();
        }
    }
    private void startServer() throws FtpException {

        userManager.save(localUserManager.getMyUser(0));

        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(port);

        FtpServerFactory factory = new FtpServerFactory();
        factory.setUserManager(userManager);
        factory.addListener("default", listenerFactory.createListener());

        FtpServer server = factory.createServer();
        server.start();
    }

}