/**
 * Created by ubuntu on 2015-10-23.
 */
public class Controller {
    private MainWindow mainWindow;
    private ServerManager serverManager;

    public Controller(){
        initialize();
    }

    private void initialize() {
        serverManager = new ServerManager(this);
        mainWindow = new MainWindow(this);

    }

    public ServerManager getServerManager(){
        return serverManager;
    }

    public MainWindow getMainWindow(){
        return mainWindow;
    }

}
