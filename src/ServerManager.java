//import Old.Controller;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
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

        factory = new FtpServerFactory();
        listenerFactory = new ListenerFactory();
        server = factory.createServer();
    }

    public void startServer() throws FtpException {
        for(MyUser user: masterUserManager.getMyUsers()){
            masterUserManager.getInnerUserManager().save(user);
        }
//        masterUserManager.getInnerUserManager().save(masterUserManager.getMyUser(1));

        listenerFactory.setPort(port);
        factory.setUserManager(masterUserManager.getInnerUserManager());
        factory.addListener("default", listenerFactory.createListener());

        server.start();
    }

    public void stopServer() throws FtpException {
        server.stop();
    }

    public String getServerInformation() {
        String returnString = "Username: " + masterUserManager.getMyUser(0).getName() + "\n";
        returnString += "Password: " + masterUserManager.getMyUser(0).getPassword() + "\n";
        return returnString;
    }

    public MasterUserManager getMasterUserManager(){
        return masterUserManager;
    }

    public FtpServer getServer(){
        return server;
    }

    public int getPort(){
        return port;
    }


}