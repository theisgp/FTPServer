import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;

public class ServerManager {

    private FtpServer server;
    private FtpServerFactory factory;
    private ListenerFactory listenerFactory;

    private MasterUserManager masterUserManager;

    private int port;

    public ServerManager() {
        port = 2221;
        this.masterUserManager = new MasterUserManager();
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

        masterUserManager.getInnerUserManager().save(masterUserManager.getMyUser(0));

        listenerFactory = new ListenerFactory();
        listenerFactory.setPort(port);

        factory = new FtpServerFactory();
        factory.setUserManager(masterUserManager.getInnerUserManager());
        factory.addListener("default", listenerFactory.createListener());

        server = factory.createServer();
        server.start();
    }

    public String getServerInformation() {
        String returnString = null;
        returnString = "Username: " + masterUserManager.getMyUser(0).getName() + "\n";
        returnString += "Password: " + masterUserManager.getMyUser(0).getPassword() + "\n";
        return returnString;
    }


}