import org.apache.ftpserver.usermanager.impl.BaseUser;

/**
 * Created by ubuntu on 2015-10-22.
 */
public class MyUser extends BaseUser {
/*    private String firstName;
    private String username;
    private String password;
    private String directory;*/

    public MyUser(String username, String password, String directory){
        this.setName(username);
        this.setPassword(password);
        this.setHomeDirectory(directory);
    }

/*    public String getUsername(){
        return  username;
    }

    public String getHomeDirectory(){
        return  username;
    }

    public String getPassword(){
        return  password;
    }*/
}
