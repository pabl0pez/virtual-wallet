package co.pablopez.Persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

import co.pablopez.Model.User;
import co.pablopez.Model.Wallet;

public class FileManager {
    String pathUsersFile = "";
    
    public static String getPropertiesPath(String path){
        Properties properties= new Properties();
		try {
			properties.load(new FileInputStream(new File("virtual_wallet/src/main/resources/co/pablopez/td/properties.properties")));
            return properties.get(path).toString();
		} 
        catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

    public void saveUser(User user) throws IOException{
        pathUsersFile = getPropertiesPath("userPath");
        StringBuilder userText = new StringBuilder();

        userText.append(user.getName() + "--");
        userText.append(user.getIdUser() + "--");
        userText.append(user.getPassword() + "--");
        userText.append(user.getAvailableBalance() + "--");
		userText.append(user.getNumberAccount() + "\n");
		//userText.append("\n");

        UtilFile.guardarArchivo(pathUsersFile, userText.toString(), true);
    }

    public LinkedList<User> loadUser(Wallet pabloWallet)throws IOException {
		pathUsersFile = getPropertiesPath("userPath");

		ArrayList<String> contenido = UtilFile.leerArchivo(pathUsersFile);

		for (String usuarioTexto: contenido) {
			String[] split = usuarioTexto.split("--");

			if (split.length >= 2) {
				User user = new User(split[1], split[0], split[2], Double.valueOf(split[3]), split[4]);
				pabloWallet.getUsers().add(user);
			} else {
				System.err.println("Línea con datos incompletos: " + usuarioTexto);
			}
		}
		return pabloWallet.getUsers();
	}

    public void actualizarSaldoUsuario(User usuarioActualizado, Double saldo) throws IOException {
		pathUsersFile = getPropertiesPath("userPath");
	
		// Cargar el contenido completo del archivo
		ArrayList<String> contenidoArchivo = UtilFile.leerArchivo(pathUsersFile);
		ArrayList<String> contenidoActualizado = new ArrayList<>();
	
		for (String linea : contenidoArchivo) {
			String[] datos = linea.split("--");
	
			// Verificar si esta línea corresponde al usuario que queremos actualizar
			if (datos[1].equals(usuarioActualizado.getIdUser())) { // Compare por idUser (or an especific identificator)
				// Update only the specific date
				String saldoStr = String.valueOf(saldo);
				datos[3] = saldoStr; // Update the balance
	
				// Update the line of data
				String lineaActualizada = String.join("--", datos);
				contenidoActualizado.add(lineaActualizada);
			} else {
				// The line dont changes
				contenidoActualizado.add(linea);
			}
		}
		// Ovewrite the file with the update content
		UtilFile.guardarArchivo(pathUsersFile, String.join("\n", contenidoActualizado), false);
	}

	public void actualizarContraseniaUsuario(User usuarioActualizado, String saldo) throws IOException {
		pathUsersFile = getPropertiesPath("userPath");
	
		// Cargar el contenido completo del archivo
		ArrayList<String> contenidoArchivo = UtilFile.leerArchivo(pathUsersFile);
		ArrayList<String> contenidoActualizado = new ArrayList<>();
	
		for (String linea : contenidoArchivo) {
			String[] datos = linea.split("--");
	
			// Verificar si esta línea corresponde al usuario que queremos actualizar
			if (datos[1].equals(usuarioActualizado.getIdUser())) { // Compare por idUser (or an especific identificator)
				// Update only the specific date
				datos[2] = saldo; // Update the balance
	
				// Update the line of data
				String lineaActualizada = String.join("--", datos);
				contenidoActualizado.add(lineaActualizada);
			} else {
				// The line dont changes
				contenidoActualizado.add(linea);
			}
		}
		// Ovewrite the file with the update content
		UtilFile.guardarArchivo(pathUsersFile, String.join("\n", contenidoActualizado), false);
	}
}
