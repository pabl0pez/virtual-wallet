package co.pablopez.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.pablopez.App;
import co.pablopez.Model.User;
import co.pablopez.Model.Wallet;
import co.pablopez.Persistencia.FileManager;
import co.pablopez.Persistencia.UtilFile;
import co.pablopez.View.RegisterViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_idUser;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_passwordconfirmed;

    Wallet pabloWallet = Wallet.getSingleton();

    @FXML
    void btn_register(ActionEvent event) throws IOException {
        String name = txt_name.getText();
        String idUser = txt_idUser.getText();
        String password = txt_password.getText();
        String passwordConfirmed = txt_passwordconfirmed.getText();
        String numberAccount = RegisterViewUtil.generateNumberAccount();

        if(password == "" || passwordConfirmed == "" || name == "" || idUser == ""){
            UtilFile.showAlert("ERROR!", "Por favor llene todos los campos");
        }
        else if(password == "" && passwordConfirmed == "" && name == "" && idUser == ""){
            UtilFile.showAlert("ERROR!", "Por favor llene todos los campos");
        }
        else if (password.equals(passwordConfirmed)){
            User newUser = new User(idUser, name, password, 0.0, numberAccount);
            //Save the user
            FileManager filemanager = new FileManager();
            filemanager.saveUser(newUser);
            pabloWallet.addNewUser(newUser);

            //Create the log
            UtilFile.guardarRegistroLog("Se registró el usuario " + idUser, 1, "registroUsuario", "src\\main\\resources\\co\\pablopez\\td\\LOG\\logFile.txt");

            App.setRoot("loginView", "Pablo Wallet -Inicio de sesión-");
        }
        else{
            UtilFile.showAlert("ERROR!", "Las contraseñas no coinciden");
        }
    }
    
    @FXML
    void btn_back(MouseEvent event) throws IOException {
        App.setRoot("loginView", "Pablo Wallet -Inicia sesión-");
    }

    @FXML
    void initialize() {
        assert txt_idUser != null : "fx:id=\"txt_idUser\" was not injected: check your FXML file 'registerView.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'registerView.fxml'.";
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'registerView.fxml'.";
        assert txt_passwordconfirmed != null : "fx:id=\"txt_passwordconfirmed\" was not injected: check your FXML file 'registerView.fxml'.";

    }
}
