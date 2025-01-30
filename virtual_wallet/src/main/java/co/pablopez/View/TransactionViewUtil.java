package co.pablopez.View;

import java.io.IOException;
import java.util.LinkedList;

import co.pablopez.Model.Transaction;
import co.pablopez.Model.User;
import co.pablopez.Model.Wallet;
import co.pablopez.Persistencia.FileManager;
import co.pablopez.Persistencia.TransactionManager;

public class TransactionViewUtil {

    Wallet wallet = Wallet.getSingleton();

    public boolean verifyDestinationUserExist(String numberAccount) throws IOException{
        FileManager fileManager = new FileManager();
        LinkedList<User> users = fileManager.loadUser(wallet);

        for(User user : users){
            if(user.getNumberAccount().equals(numberAccount)){
                return true;
            }
        }
        return false;
    }

    public boolean verifyAmount(double amount, User currentUser){
        if(currentUser.getAvailableBalance() >= amount){
            return true;
        }
        return false;
    }

    public String getTransactions(LinkedList<Transaction> transactions){
        String transaction = "";
        
        for(Transaction transactionText : transactions){
            transaction += ("Se envió $" + transactionText.getAmount() + " a la cuenta " + transactionText.getDestinationUser() + "\n");
        }
        return transaction;
    }
}
