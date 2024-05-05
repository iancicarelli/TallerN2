import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    Biblioteca biblioteca = new Biblioteca("Biblioteca Municipal de Titirilquén");
    public static void main(String[] args) {

        /*Debemos implementar menús en main()*/
    }
    public void dibujarMenuISesion(){
        System.out.println("Bienvenido a la biblioteca municipal de Titirilquén.\n" +
                "Por favor escoga: iniciar sesión (0) o registrarse (1).");
        while(scanner.hasNextInt()){
            switch(scanner.nextInt()){
                case 0:
                    System.out.println("Introduzca su usuario: ");
                    String user = scanner.nextLine();
                    System.out.println("Introduzca su contraseña: ");
                    String pase = scanner.nextLine();
                    if(biblioteca.escanearUsuarioExistente(user)){
                        Usuario usuario = new Usuario(Sesion.conectar(user, pase));
                        dibujarMenuInteraccion();
                    }else{
                        System.out.println("Usuario incorrecto o contraseña incorrecta.");
                    }
                case 1:
                    System.out.println("Introduzca su nuevo usuario: ");
                    String newUser = scanner.nextLine();
                    System.out.println("Introduzca su nueva contraseña:");
                    String newPase = scanner.nextLine();
                    if(!biblioteca.escanearUsuarioExistente(newUser)){
                        Usuario usuarioNuevo = new Usuario(Sesion.conectar(newUser, newPase));
                        biblioteca.masUsuario(usuarioNuevo);
                        dibujarMenuInteraccion();
                    }else{
                        System.out.println("Usuario ya existe.");
                        dibujarMenuISesion();
                    }
            }
        }
    }
    public void dibujarMenuInteraccion(){

    }
}
