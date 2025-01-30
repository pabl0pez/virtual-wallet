package co.pablopez.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.pablopez.App;
import co.pablopez.Model.User;
import co.pablopez.Model.Wallet;
import co.pablopez.Persistencia.FileManager;
import co.pablopez.Persistencia.UtilFile;
import co.pablopez.View.ProfileViewUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txt_UPmodified;

    @FXML
    private TextField txt_currentPassword;

    @FXML
    private Label txt_nameUnmofied;

    @FXML
    private TextField txt_newPassword;

    @FXML
    private TextField txt_passwordconfirmed;

    Wallet wallet = Wallet.getSingleton();
    User currentUser = wallet.getCurrentUser().get(0);
    FileManager userManager = new FileManager();

    public void initializeInformation(){
        txt_UPmodified.setText("Modifica tus datos " + currentUser.getIdUser());
        txt_nameUnmofied.setText(currentUser.getName());
    }

    @FXML
    void btn_back(MouseEvent event) throws IOException {
        App.setRoot("userView", "Pablo Wallet -Página principal-");
    }

    @FXML
    void btn_saveChanges(MouseEvent event) throws IOException {
        ProfileViewUtil profileView = new ProfileViewUtil();
        String currentPassword = txt_currentPassword.getText();
        String newPassword = txt_newPassword.getText();
        String passwordConfirmed = txt_passwordconfirmed.getText();

        if(currentPassword == "" || newPassword == "" || passwordConfirmed == ""){
            UtilFile.showAlert("ERROR!", "Por favor llene todos los campos");
        }
        else if(currentPassword == "" && newPassword == "" && passwordConfirmed == ""){
            UtilFile.showAlert("ERROR!", "Por favor llene todos los campos");
        }
        else if(profileView.verifyPassword(currentPassword, currentUser)){
            if(newPassword.equals(passwordConfirmed)){
                currentUser.setPassword(newPassword);
                userManager.actualizarContraseniaUsuario(currentUser, newPassword);
                UtilFile.guardarRegistroLog("El usuario " + currentUser.idUser + " cambió su contraseña", 3, "cambioContraseña", "virtual_wallet\\src\\main\\resources\\co\\pablopez\\td\\LOG\\logFIle.txt");
                UtilFile.showAlert("ÉXITO!", "Las contraseña se cambió correctamente");
                App.setRoot("userView", "Pablo Wallet -Página principal-");
            }
            else{
                UtilFile.showAlert("ERROR!", "Las contraseñas no coinciden");
            }
        }
        else{
            UtilFile.showAlert("ERROR!", "La contraseña actual es incorrecta");
        }
    }

    @FXML
    void initialize() {
        initializeInformation();

        assert txt_UPmodified != null : "fx:id=\"txt_UPmodified\" was not injected: check your FXML file 'profileView.fxml'.";
        assert txt_currentPassword != null : "fx:id=\"txt_currentPassword\" was not injected: check your FXML file 'profileView.fxml'.";
        assert txt_newPassword != null : "fx:id=\"txt_newPassword\" was not injected: check your FXML file 'profileView.fxml'.";
        assert txt_passwordconfirmed != null : "fx:id=\"txt_passwordconfirmed\" was not injected: check your FXML file 'profileView.fxml'.";
    }
}
