package co.pablopez.View;

import co.pablopez.Model.User;

public class ProfileViewUtil {
    
    public boolean verifyPassword(String password, User user) {
        if(password.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    
}
