package co.pablopez.Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UtilFile {

    static String fechaSistema = "";

    public static void showAlert(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void guardarArchivo(String ruta,String contenido, Boolean flagAnexarContenido) throws IOException {
        File archivo = new File(ruta);
        File directorio = archivo.getParentFile();

        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();  // Crea los directorios si no existen
        }

        FileWriter fw = new FileWriter(ruta,flagAnexarContenido);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

    public static ArrayList<String> leerArchivo(String ruta) throws IOException {
        ArrayList<String>  contenido = new ArrayList<String>();
        //abrir conexion
        FileReader fr=new FileReader(ruta);
        BufferedReader bfr=new BufferedReader(fr);
        // leer
        String linea="";
        while((linea = bfr.readLine())!=null)
        {
            contenido.add(linea);
        }
        //cerrar
        bfr.close();
        fr.close();
        return contenido;
    }

    public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo){
        String log = "";
        Logger LOGGER = Logger.getLogger(UtilFile.class.getName());
        FileHandler fileHandler = null;

        cargarFechaSistema();

        try{
            fileHandler = new FileHandler(rutaArchivo,true);
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);

			switch (nivel) {
			case 1:
				LOGGER.log(Level.INFO,accion+", " + mensajeLog + "\n") ;
				break;

			case 2:
				LOGGER.log(Level.WARNING,accion+", "+ mensajeLog + "\n") ;
				break;

			case 3:
				LOGGER.log(Level.SEVERE,accion+", "+ mensajeLog + "\n") ;
				break;

			default:
				break;
			}
        }catch(SecurityException e){
            LOGGER.log(Level.SEVERE,e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE,e.getMessage());
			e.printStackTrace();
		}
		finally {
			fileHandler.close();
		}
    }

    private static void cargarFechaSistema() {
        String diaN= "";
        String mesN= "";
        String anio = "";
        Calendar cal = Calendar.getInstance();

        int dia = cal.get(Calendar.DATE);
        int mes = cal.get(Calendar.MONTH) + 1;
        int anioSistema = cal.get(Calendar.YEAR);
        int hora = cal.get(Calendar.HOUR);

        if(dia < 10){
			diaN+="0"+dia;
		}
		else{
			diaN+=""+dia;
		}
		if(mes < 10){
			mesN+="0"+mes;
		}
		else{
			mesN+=""+mes;
		}
        fechaSistema = anio + "-" + mesN + "-" + diaN;
    }
}
