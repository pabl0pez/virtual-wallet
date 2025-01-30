package co.pablopez.Model;

import java.util.LinkedList;

public class Wallet {
    private static Wallet singleton;
    public LinkedList<User> users;
    public LinkedList<User> currentUser;
    public LinkedList<Transaction> transactions;

    public static Wallet getSingleton() {
        if(singleton == null){
            singleton = new Wallet();
        }
        return singleton;
    }

    public Wallet() {
        this.users = new LinkedList<>();
        this.currentUser = new LinkedList<>();
        this.transactions = new LinkedList<>();
    }

    public static void setSingleton(Wallet singleton) {
        Wallet.singleton = singleton;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public LinkedList<User> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(LinkedList<User> currentUser) {
        this.currentUser = currentUser;
    }    

    public void addNewUser(User user){
        users.add(user);
    }

    public void addCurrentUser(User user){
        currentUser.add(user);
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public LinkedList<Transaction> getTransactionsByNumberAccount(User currentUser){
        LinkedList<Transaction> transactionsByNumberAccount = new LinkedList<>();
        for(Transaction transaction : transactions){
            if(transaction.getOriginUser().equals(currentUser.getNumberAccount())){
                transactionsByNumberAccount.add(transaction);
            }
        }
        return transactionsByNumberAccount;
    }

    public User searchUserByNumberAccount(String numberAccount) {
        for (User user : users) {
            if (user.getNumberAccount().equals(numberAccount)) {
                System.out.println("Usuario encontrado: " + user.getName());
                return user;
            }
        }
        return null;
    }
}
