import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import java.util.ArrayList;

/**
 * Created by ubuntu on 2015-10-22.
 */
public class MasterUserManager {

    private ArrayList<MyUser> myUsers;
    private UserManager innerUserManager;
    private PropertiesUserManagerFactory userManagerFactory;

    public MasterUserManager() {
        userManagerFactory = new PropertiesUserManagerFactory();
        innerUserManager = userManagerFactory.createUserManager();
        myUsers = new ArrayList<>();
    }

    public UserManager getInnerUserManager() {
        return innerUserManager;
    }

    public MyUser createBaseUser(String username, String password, String directory) {

        MyUser myUser = new MyUser(username, password, "/tmp/"+username);

        myUsers.add(myUser);
        return myUser;
    }

    public MyUser getMyUser(int id){
        if (myUsers.isEmpty()){
            return createDefaultUser();
        }
        else if (myUsers.size()< id){
            return createDefaultUser();
        }
        return myUsers.get(id);
    }

    private MyUser createDefaultUser(){
        MyUser defaultMyUser = createBaseUser("default","default","/tmp/test");
        return defaultMyUser;
    }

    public ArrayList<MyUser> getMyUsers(){
        return myUsers;
    }
}
