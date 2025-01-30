package co.pablopez.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TransactionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_amount;

    @FXML
    private TextField txt_numberAccount;

    @FXML
    private TextField txt_reason;

    Wallet wallet = Wallet.getSingleton();
    User currentUser = wallet.getCurrentUser().get(0);
    FileManager userManager = new FileManager();
    TransactionManager transactionManager = new TransactionManager();
    TransactionViewUtil transactionViewUtil = new TransactionViewUtil();

    @FXML
    void btn_back(MouseEvent event) throws IOException {
        App.setRoot("userView", "Pablo Wallet -Página principal-");
    }

    @FXML
    void btn_send(ActionEvent event) throws IOException {
        String amountText = txt_amount.getText();
        String numberAccount = txt_numberAccount.getText();
        String reason = txt_reason.getText();
        double balance = currentUser.getAvailableBalance();

        if(amountText == "" || numberAccount == "" || reason == ""){
            UtilFile.showAlert("ERROR!", "Por favor llene todos los campos");
        }
        else if(amountText == "" && numberAccount == "" && reason == ""){
            UtilFile.showAlert("ERROR!", "Por favor llene todos los campos");
        }
        else if(numberAccount.equals(currentUser.getNumberAccount())){
            UtilFile.showAlert("ERROR!", "No puedes enviarte dinero a ti mismo");
        }
        else if(transactionViewUtil.verifyDestinationUserExist(numberAccount) == true){
            double amount = Double.parseDouble(amountText);
            User destinationUser = wallet.searchUserByNumberAccount(numberAccount);
            if(transactionViewUtil.verifyAmount(amount, currentUser) == true){

                Transaction transaction = new Transaction(currentUser.getNumberAccount(), numberAccount, amount, reason);
                transactionManager.saveTransaction(transaction);
                wallet.addTransaction(transaction);
                currentUser.getTransactions().add(transaction);
                destinationUser.getTransactions().add(transaction);

                //update destination user
                Double newAmountDestination = destinationUser.getAvailableBalance() + amount;
                destinationUser.setAvailableBalance(newAmountDestination);
                userManager.actualizarSaldoUsuario(destinationUser, newAmountDestination);

                //update current user
                Double newAmountOrigin = balance - amount;
                currentUser.setAvailableBalance(newAmountOrigin);
                userManager.actualizarSaldoUsuario(currentUser, newAmountOrigin);

                UtilFile.showAlert("Transacción exitosa", "Se ha enviado $" + amount + " a la cuenta " + numberAccount);
                App.setRoot("userView", "Pablo Wallet -Página principal-");
            }
            else{
                UtilFile.showAlert("ERROR!", "No tienes suficiente saldo");
            }
        }
        else{
            UtilFile.showAlert("ERROR!", "El usuario no existe");
        }
    }

    @FXML
    void initialize() {
        assert txt_amount != null : "fx:id=\"txt_amount\" was not injected: check your FXML file 'transactionView.fxml'.";
        assert txt_numberAccount != null : "fx:id=\"txt_numberAccount\" was not injected: check your FXML file 'transactionView.fxml'.";
        assert txt_reason != null : "fx:id=\"txt_reason\" was not injected: check your FXML file 'transactionView.fxml'.";
    }
}
