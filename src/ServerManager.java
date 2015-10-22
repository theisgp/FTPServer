import com.sun.corba.se.spi.activation.Server;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

public class ServerManager {

    private FtpServer server;
    private FtpServerFactory factory;
    private ListenerFactory listenerFactory;

    private UserManager userManager;
    private LocalUserManager localUserManager;
    
    private int port;

    public ServerManager(){
        port = 2221;
        this.localUserManager = new LocalUserManager();
        this.userManager = localUserManager.getUserManager();
    }
//    public static void main(String[] args){
//        ServerManager serverManger = new ServerManager();
//        try {
//            serverManger.startServer();
//            System.out.println("service successfully started");
//        } catch (FtpException e) {
//            System.out.println("Server failed starting...");
//            e.printStackTrace();
//        }
//    }
    public void startServer() throws FtpException {

        userManager.save(localUserManager.getMyUser(0));

        listenerFactory = new ListenerFactory();
        listenerFactory.setPort(port);

        factory = new FtpServerFactory();
        factory.setUserManager(userManager);
        factory.addListener("default", listenerFactory.createListener());

        server = factory.createServer();
        server.start();
    }

    public String getServerInformation(){
        String returnString = null;
        try {

            returnString = "Username: " + userManager.getUserByName("default").getName() + "\n";
            returnString += "Password: " + localUserManager.getMyUser(0).getPassword() + "\n";
            return returnString;
        } catch (FtpException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


}