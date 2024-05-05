import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Lector {
    public static List<String> lineasAlmacenadas;
    public static String leer(String path, int i){
        String linea;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((linea = reader.readLine()) != null) {
                lineasAlmacenadas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineasAlmacenadas.get(i);
    }
    public static void imprimir(String path){
        String linea;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((linea = reader.readLine()) != null) {
                for (int i = 0; i < linea.length(); i++) {
                    if(linea.charAt(i) == ','){
                        linea = linea.substring(0, i) +" | "+ linea.substring(i + 1);
                    }
                }
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void escribir(String path){

    }

    public static void borrar(String path){

    }
}

