import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import java.util.ArrayList;

/**
 * Created by ubuntu on 2015-10-22.
 */
public class LocalUserManager {

    private ArrayList<MyUser> myUsers;
    private UserManager userManager;
    private PropertiesUserManagerFactory userManagerFactory;

    public LocalUserManager() {
        userManagerFactory = new PropertiesUserManagerFactory();
        userManager = userManagerFactory.createUserManager();
        myUsers = new ArrayList<>();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public MyUser createBaseUser(String username, String password, String directory) {

        MyUser myUser = new MyUser(username, password, directory);

        System.out.println("Name: " + myUser.getName());
        System.out.println("Password: " + myUser.getPassword());

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
        MyUser defaultMyUser = createBaseUser("default","default","/tmp");
        return defaultMyUser;
    }

}
