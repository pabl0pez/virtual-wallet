package co.pablopez.View;

import java.util.Random;

public class RegisterViewUtil {

    public static String generateNumberAccount(){
        String numberAccount = "";

        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);
        numberAccount = String.valueOf(randomNumber);

        return numberAccount;
    }
}
