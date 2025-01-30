package co.pablopez.View;

import java.io.IOException;
import java.util.LinkedList;

import co.pablopez.Model.User;
import co.pablopez.Model.Wallet;
import co.pablopez.Persistencia.FileManager;

public class LoginViewUtil {
    Wallet wallet = Wallet.getSingleton();

    public boolean verifyUserExist(String id, String password) throws IOException{
        FileManager fileManager = new FileManager();
        LinkedList<User> users = fileManager.loadUser(wallet);

        for(User user : users){
            if(user.getIdUser().equals(id) && user.getPassword().equals(password)){
                wallet.addCurrentUser(user);
                return true;
            }
        }
        return false;
    }
}
