import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lector {
    public static void crear(String file){
        try{
            File f = new File (file);
            if(f.createNewFile()){
                System.out.println("Archivo creado éxitosamente.");
            }else{
                System.out.println("Archivo ya existe.");
            }
        }catch (IOException e){
            System.out.println("Ha ocurrido un error. " + e.getMessage());
        }
    }

    public static boolean sePuedeCrearQ(String file) throws IOException {
        File f = new File (file); return f.createNewFile();
    }

    public static String leer(String path, int i){
        String linea;
        List<String> lineasAlmacenadas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((linea = reader.readLine()) != null) {
                lineasAlmacenadas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        if(!(lineasAlmacenadas.size()<i)&&i>=0){
            return lineasAlmacenadas.get(i);
        }
        return lineasAlmacenadas.getLast();
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

    public static void escribir(String path, String content){
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(content + "\n");
            System.out.println("Escrito al archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static int buscar(String path, String palabra){
        for (int i = 0; i < cantidadDeLineas(path); i++) {
            String linea = leer(path, i);
            for (int j = 0; j < linea.length(); j++) {
                if(linea.charAt(j) == ','){
                    linea = linea.substring(0, i);
                }
            }
            if(linea.equals(palabra)){
                return i;
            }
        }
        return 0;
    }

    public static int cantidadDeLineas(String path){
        String linea;
        int cantidad = 0;
        List<String> lineasAlmacenadas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((linea = reader.readLine()) != null) {
                cantidad++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return cantidad;
    }

    public static void borrar(String path, int i){
        List<String> lineasAlmacenadas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasAlmacenadas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
        try (FileWriter writer = new FileWriter(path)) {
            for (int j = 0; j < lineasAlmacenadas.size(); j++) {
                if (j != i){
                    writer.write(lineasAlmacenadas.get(j) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al modificar el archivo: " + e.getMessage());
        }
    }
}

