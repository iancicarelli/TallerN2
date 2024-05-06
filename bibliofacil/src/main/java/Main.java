import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca("Biblioteca Municipal de Titirilquén");
    static Administrador administrador = new Administrador("admin","admin","admin");//administrador por defecto.
    public static void main(String[] args) throws IOException {
        if(Lector.sePuedeCrearQ("bibliofacil/src/main/resources/Administradores.csv")){
            Lector.crear("bibliofacil/src/main/resources/Administradores.csv");
            Lector.escribir("bibliofacil/src/main/resources/Administradores.csv","User,Pase,Nombre\n"+"admin,admin,admin");
        }
    }
    public static void dibujarMenuISesion(){

    }
    public static void dibujarMenuInteraccion(Usuario usuario){

    }
    public static void dibujarMenuInteraccion(Administrador administrador){

    }
}
