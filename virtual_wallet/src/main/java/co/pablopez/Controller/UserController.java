package co.pablopez.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import co.pablopez.App;
import co.pablopez.Model.Transaction;
import co.pablopez.Model.User;
import co.pablopez.Model.Wallet;
import co.pablopez.Persistencia.FileManager;
import co.pablopez.Persistencia.TransactionManager;
import co.pablopez.Persistencia.UtilFile;
import co.pablopez.View.TransactionViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txt_UP;

    @FXML
    private Label txt_balance;

    @FXML
    private Label txt_censure;

    @FXML
    private TextField txt_deposit;

    @FXML
    private Label txt_numberAccount;

    @FXML Label txt_transactions;

    Wallet pabloWallet = Wallet.getSingleton();
    User currentUser = pabloWallet.getCurrentUser().get(0);
    boolean balanceVisibility = false;
    FileManager userManager = new FileManager();
    TransactionManager transactionManager = new TransactionManager();

    public void initializeUpperInformation(){
        txt_UP.setText("Hola, " + currentUser.getIdUser());
        txt_numberAccount.setText("Número de cuenta: " + currentUser.getNumberAccount());
    }

    @FXML
    void btn_Budget(MouseEvent event) {

    }

    @FXML
    void btn_PDF(MouseEvent event) {

    }

    @FXML
    void btn_createTransaction(MouseEvent event) throws IOException {
        App.setRoot("transactionView", "Pablo Wallet -Envía dinero-");
    }

    @FXML
    void btn_deposit(ActionEvent event) throws IOException {
        String amount_txt = txt_deposit.getText();
        Double amount = Double.parseDouble(amount_txt);
        Double currentAmount = currentUser.getAvailableBalance();
        Double newAmount = amount + currentAmount;
        currentUser.setAvailableBalance(newAmount);
        userManager.actualizarSaldoUsuario(currentUser, newAmount);
        txt_deposit.setText("");
    }

    @FXML
    void btn_graphics(MouseEvent event) {

    }

    @FXML
    void btn_logOut(MouseEvent event) throws IOException {
        pabloWallet.currentUser.clear();
        UtilFile.guardarRegistroLog("El usuario " + currentUser.getIdUser() + " cerró sesión", 1, "cerrarSesionUsuario", "virtual_wallet\\src\\main\\resources\\co\\pablopez\\td\\LOG\\logFIle.txt");
        App.setRoot("loginView", "Pablo Wallet -Inicia sesión-");
    }

    @FXML
    void btn_profile(MouseEvent event) throws IOException {
        App.setRoot("profileView", "Pablo Wallet -Modifica tu perfil-");
    }

    @FXML
    void btn_showBalance(MouseEvent event) {
        String balance = String.valueOf(currentUser.getAvailableBalance());
        txt_balance.setText("$" + balance);

        balanceVisibility = !balanceVisibility;

        txt_censure.setVisible(!balanceVisibility);
        txt_balance.setVisible(balanceVisibility);

        if(txt_balance.isVisible()){
            UtilFile.guardarRegistroLog("El usuario " + currentUser.getIdUser() + " consultó su saldo", 1, "consultaSaldo", "virtual_wallet\\src\\main\\resources\\co\\pablopez\\td\\LOG\\logFIle.txt");
        }
    }

    public void initializeTransactions() throws IOException{
        TransactionViewUtil transactionViewUtil = new TransactionViewUtil();
        LinkedList<Transaction> transactionsUser = transactionManager.loadTransaction(currentUser);
        
        LinkedList<Transaction> transactionsFiletered = transactionsUser.stream()
            .filter(transaction -> transaction.getOriginUser().equals(currentUser.getNumberAccount()))
            .collect(Collectors.toCollection(LinkedList::new));
        
            
        String transactions = transactionViewUtil.getTransactions(transactionsFiletered);
        txt_transactions.setText(transactions);
        transactionsUser.clear();
    }

    @FXML
    void initialize() throws IOException {
        initializeUpperInformation();
        initializeTransactions();
        txt_censure.setVisible(true);
        txt_balance.setVisible(false);



        assert txt_UP != null : "fx:id=\"txt_UP\" was not injected: check your FXML file 'userView.fxml'.";
        assert txt_balance != null : "fx:id=\"txt_balance\" was not injected: check your FXML file 'userView.fxml'.";
        assert txt_censure != null : "fx:id=\"txt_censure\" was not injected: check your FXML file 'userView.fxml'.";
        assert txt_deposit != null : "fx:id=\"txt_deposit\" was not injected: check your FXML file 'userView.fxml'.";
        assert txt_transactions != null : "fx:id=\"txt_transactions\" was not injected: check your FXML file 'userView.fxml'.";
    }
}
