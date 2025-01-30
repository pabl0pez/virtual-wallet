package co.pablopez.Model;

import java.util.LinkedList;

public class User {
    public String idUser;
    private String name;
    private String password;
    private double availableBalance;
    public String numberAccount;
    private LinkedList<Transaction> transactions;
    private LinkedList<Budget> budgets;

    public User(){

    }

    public User(String idUser, String name, String password, Double availableBalance, String numberAccount) {
        this.idUser = idUser;
        this.name = name;
        this.password = password;
        this.availableBalance = availableBalance;
        this.numberAccount = numberAccount;
        this.transactions = new LinkedList<>();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(LinkedList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public LinkedList<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(LinkedList<Budget> budgets) {
        this.budgets = budgets;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", name=" + name + ", password=" + password + ", availableBalance="
                + availableBalance + ", transactions=" + transactions + ", budgets="
                + budgets + "]";
    }
}
