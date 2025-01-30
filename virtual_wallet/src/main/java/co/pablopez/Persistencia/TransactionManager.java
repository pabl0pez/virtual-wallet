package co.pablopez.Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

import co.pablopez.Model.Transaction;
import co.pablopez.Model.User;
import co.pablopez.Model.Wallet;

public class TransactionManager {

    String pathTransactionsFile = "";
    String pathUsersFile = "";
    
    public static String getPropertiesPath(String path){
        Properties properties= new Properties();
		try {
			properties.load(new FileInputStream(new File("virtual_wallet\\src\\main\\resources\\co\\pablopez\\td\\properties.properties")));
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

    public void saveTransaction(Transaction transaction) throws IOException{
        pathTransactionsFile = getPropertiesPath("transactionPath");
        StringBuilder transactionText = new StringBuilder();

        transactionText.append(transaction.getOriginUser() + "--");
        transactionText.append(transaction.getDestinationUser() + "--");
        transactionText.append(transaction.getAmount() + "--");
        transactionText.append(transaction.getDescription() + "\n");

        UtilFile.guardarArchivo(pathTransactionsFile, transactionText.toString(), true);
    }

    public LinkedList<Transaction> loadTransaction(User user) throws IOException {
		pathTransactionsFile = getPropertiesPath("transactionPath");

		ArrayList<String> content = UtilFile.leerArchivo(pathTransactionsFile);

		for (String transactionText: content) {
			String[] split = transactionText.split("--");

			if (split.length >= 2) {
				Transaction transaction = new Transaction(split[0], split[1], Double.valueOf(split[2]), split[3]);
				user.getTransactions().add(transaction);
			} else {
				System.err.println("Línea con datos incompletos: " + transactionText);
			}
		}
		return user.getTransactions();
	} 


}
