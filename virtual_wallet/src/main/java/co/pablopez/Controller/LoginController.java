package co.pablopez.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.pablopez.App;
import co.pablopez.Model.Wallet;
import co.pablopez.Persistencia.UtilFile;
import co.pablopez.View.LoginViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    Wallet pabloWallet = Wallet.getSingleton();

    @FXML
    void btn_ingresar(ActionEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        LoginViewUtil loginView = new LoginViewUtil();

        if(username == "" || password == ""){
            UtilFile.showAlert("ERROR!", "Rellene todos los espacios");
        }
        else if(username == "" && password == ""){
            UtilFile.showAlert("ERROR!", "Rellene todos los espacios");
        }
        else if(loginView.verifyUserExist(username, password) == true){
            UtilFile.guardarRegistroLog("Ingresó el usuario " + username, 1, "inicioSesion", "src\\main\\resources\\co\\pablopez\\td\\LOG\\logFile.txt");
            App.setRoot("userView", "Pablo Wallet -Página principal-");
        }
        else{
            UtilFile.showAlert("ERROR!", "Las credenciales son incorrectas");
            UtilFile.guardarRegistroLog("Se intentó iniciar sesión ", 3, "inicioSesionFallido", "src\\main\\resources\\co\\pablopez\\td\\LOG\\logFile.txt");

        }
    }

    @FXML
    void btn_registrarse(MouseEvent event) throws IOException {
        App.setRoot("registerView", "Pablo Wallet -Registro de datos-");
    }

    @FXML
    void initialize() {
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'loginView.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'loginView.fxml'.";

    }

}

