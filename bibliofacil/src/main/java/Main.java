import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca("Biblioteca Municipal de Titirilquén");
    public static void main(String[] args) {
        dibujarMenuISesion();
    }
    public static void dibujarMenuISesion(){
        System.out.println("Bienvenido a la "+biblioteca.cualNombre()+"\n" +
                "Por favor escoga: iniciar sesión (0) o registrarse (1).");
        while(scanner.hasNextInt()){
            switch(scanner.nextInt()){
                case 0:
                    scanner.nextLine();
                    System.out.print("Introduzca su usuario: ");
                    String user = scanner.nextLine();
                    if(biblioteca.escanearUsuarioExistente(user)){
                        System.out.print("Introduzca su contraseña: ");
                        String pase = scanner.nextLine();
                        biblioteca.crearUsuario(user, pase);
                        Usuario usuario = new Usuario(Sesion.conectar(user, pase));
                        dibujarMenuInteraccion(usuario);
                        break;
                    }else{
                        System.out.println("Usuario incorrecto o contraseña incorrecta.");
                        dibujarMenuISesion();
                        break;
                    }
                case 1:
                    scanner.nextLine();
                    System.out.println("Introduzca su nuevo usuario: ");
                    String newUser = scanner.nextLine();
                    if(!biblioteca.escanearUsuarioExistente(newUser)){
                        System.out.println("Introduzca su nueva contraseña: ");
                        String newPase = scanner.nextLine();
                        biblioteca.crearUsuario(newUser, newPase);
                        Usuario usuarioNuevo = new Usuario(Sesion.conectar(newUser, newPase));
                        dibujarMenuInteraccion(usuarioNuevo);
                        break;
                    }else{
                        System.out.println("Usuario ya existe.");
                        dibujarMenuISesion();
                        break;
                    }
            }
        }
    }
    public static void dibujarMenuInteraccion(Usuario usuario){
        switch (usuario.quienEs()){
            case -1:
                System.out.println("Usted está sancionado y no puede acceder a la biblioteca.");
                dibujarMenuISesion();
                break;
            case 0:
                System.out.println("Menú de usuario:\n" +
                        "0. Ver libros disponibles.\n"+
                        "1. Reservar un libro.\n"+
                        "2. Añadir un libro a la lista de deseados.\n"+
                        "3. Calificar libro.\n");
                break;
            case 5:
                System.out.println("Menú de súper usuario:\n"
                        + "0. Ver libros disponibles.\n"
                        + "1. Proponer libro.\n"
                        + "2. Proponer cambios en un libro.\n"
                        + "3. Proponer nuevo recurso.");
                break;
        }
    }
    public static void dibujarMenuInteraccion(Admin admin){
        System.out.println(
                "Menú de administrador:\n"+
                        "0. Ver usuarios disponibles.\n"+
                        "1. Ver libros disponibles.\n");
        while(scanner.hasNextInt()){
            switch (scanner.nextInt()){
                case 0:
                    scanner.nextLine();
                    System.out.println("Menú de administrador de usuarios:\n"+
                            "0. Enlistar usuarios.\n"+
                            "1. Sancionar usuario.\n"+
                            "2. Aprobar solicitud de préstamo de usuario.\n");
                    while(scanner.hasNextInt()){
                        switch (scanner.nextInt()){
                            case 0:
                                biblioteca.mostrarUsuarios();
                                dibujarMenuInteraccion(admin);
                                break;
                            case 1:
                                scanner.nextLine();
                                System.out.println("Introduzca el nombre de usuario a sancionar:");
                                String user = scanner.nextLine();
                                admin.sancionarUsuario(user, biblioteca.escanearUsuarioExistente(user));
                                dibujarMenuInteraccion(admin);
                                break;
                        }
                    }
                case 1:
                    scanner.nextLine();
                    System.out.println("Menú de administrador de libros:\n"+
                            "0. Enlistar libros.\n"+
                            "1. Añadir libro.\n"+
                            "2. Eliminar libro.\n"+
                            "3. Modificar libro.\n");;
                    while(scanner.hasNextInt()){
                        switch (scanner.nextInt()){
                            case 0:
                                biblioteca.mostrarLibrosDesdeCSV();
                                dibujarMenuInteraccion(admin);
                                break;
                            case 1:
                                scanner.nextLine();
                                System.out.println("Introduzca el título del libro: ");
                                String titulo = scanner.nextLine();
                                System.out.println("Introduzca el autor del libro: ");
                                String autor = scanner.nextLine();
                                System.out.println("Introduzca la categoría del libro: ");
                                String categoria = scanner.nextLine();
                                System.out.println("Introduzca la cantidad de(los) libro(s)");
                                int cantidad = scanner.nextInt();
                                biblioteca.masLibro(new Libro(titulo, autor, categoria, cantidad));
                                dibujarMenuInteraccion(admin);
                                break;
                            case 2:
                                scanner.nextLine();
                                System.out.println("Introduzca el índice del libro " +
                                        "a eliminar: ");
                                int tituloAEliminar = scanner.nextInt();
                                dibujarMenuInteraccion(admin);
                                break;
                            case 3:
                                scanner.nextLine();
                                System.out.println("Introduzca el índice del libro " +
                                        "a modificar: ");
                                int tituloAModificar = scanner.nextInt();
                                dibujarMenuInteraccion(admin);
                                break;
                        }
                    }
            }
        }
    }
}

