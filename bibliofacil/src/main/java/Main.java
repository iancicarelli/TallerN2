import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    Lector lector = new Lector();
    Biblioteca biblioteca = new Biblioteca("Biblioteca Municipal de Titirilquén");
    public static void main(String[] args) {
        Lector.imprimir("bibliofacil/src/main/resources/Libros.csv");
        /*Debemos implementar menús en main()*/
    }
}
