import java.io.IOException;
import java.util.Objects;
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
        dibujarMenuISesion();
    }
    public static void dibujarMenuISesion() throws IOException {
        System.out.println("Bienvenido a la biblioteca municipal:\n"
        +"[1] Iniciar sesión.\n"+
                "[2] Registrarse.");
        switch(scanner.nextLine()){
            case "1":
                System.out.println("Introduzca su usuario: ");
                String usuario = scanner.nextLine();
                if(Lector.buscar("bibliofacil/src/main/resources/Administradores.csv", usuario)!=0){
                    System.out.println("Inserte su contraseña: ");
                    String pass = scanner.nextLine();
                    if(Objects.equals(pass, Lector.leer("bibliofacil/src/main/resources/Administradores.csv",
                            Lector.buscar("bibliofacil/src/main/resources/Administradores.csv", usuario)).split(",")[1])){
                        dibujarMenuInteraccion(administrador);
                    }
                }
                if(Lector.buscar("bibliofacil/src/main/resources/Usuarios.csv", usuario)!=0){
                    System.out.println("Inserte su contraseña: ");
                    String pass = scanner.nextLine();
                    String[] dataAlmacenada = Lector.leer("bibliofacil/src/main/resources/Usuarios.csv",
                            Lector.buscar("bibliofacil/src/main/resources/Usuarios.csv", usuario)).split(",");
                    System.out.println(dataAlmacenada[1]);
                    if(Objects.equals(pass, dataAlmacenada[1])){
                        dibujarMenuInteraccion(new Usuario(dataAlmacenada[0],dataAlmacenada[1],dataAlmacenada[2]));
                    }
                }
                break;
            case "2":
                System.out.println("Introduzca su nuevo usuario: ");
                String nuevoUsuario = scanner.nextLine();
                if(Lector.buscar("bibliofacil/src/main/resources/Usuarios.csv", nuevoUsuario)==0){
                    System.out.println("Inserte su contraseña: ");
                    String pass = scanner.nextLine();
                    System.out.println("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    Usuario nuevaCuenta = new Usuario(nuevoUsuario, pass, nombre);
                    biblioteca.masUsuario(nuevaCuenta);
                        dibujarMenuInteraccion(nuevaCuenta);
                    }
                break;
                }
        }

    public static void dibujarMenuInteraccion(Usuario usuario) throws IOException {
        int seleccion;
        System.out.println("Seleccione una de las opciones");
        System.out.println("[1] Mostrar libros\n[2] Buscar libro" +
                "\n[3] Pedir libro\n[4] Reservar libro\n[5] Ver mi perfil");
        seleccion = validarEntero("Ingrese su opción: ");
        scanner.nextLine();
        switch (seleccion){
            case 1:
                biblioteca.mostrarLibros();
                dibujarMenuInteraccion(usuario);
                break;
            case 2:
                System.out.println("Introduzca el título del libro que desea buscar " +
                        "(Sensible a mayúsculas): ");
                String campo = scanner.nextLine();
                if(Lector.buscar("bibliofacil/src/main/resources/Libros.csv",campo)!=0){
                    System.out.println("Libro disponible: ");
                    System.out.println(Lector.leer("bibliofacil/src/main/resources/Libros.csv"
                            ,Lector.buscar("bibliofacil/src/main/resources/Libros.csv",campo)));
                }
                dibujarMenuInteraccion(usuario);
                break;
            case 3:
                System.out.println("Introduzca el libro que quiere pedir: ");
                String aux = scanner.nextLine();
                if(biblioteca.seleccionarLibro(aux)!=null) usuario.agregarPrestamo(biblioteca.seleccionarLibro(aux));
                dibujarMenuInteraccion(usuario);
                break;
            case 4:
                System.out.println("Introduzca el libro que quiere reservar: ");
                String auxr = scanner.nextLine();
                if(biblioteca.seleccionarLibro(auxr)!=null) usuario.realizarReserva(biblioteca.seleccionarLibro(auxr));
                dibujarMenuInteraccion(usuario);
                break;
            case 5:
                System.out.println("Su perfil: ");
                System.out.println(usuario.toString());
                System.out.print("Préstamos: ");Lector.imprimir("bibliofacil/src/main/resources/Usuarios/"+usuario.cualUser()+"prestamos.csv");
                System.out.print("Reservas: ");Lector.imprimir("bibliofacil/src/main/resources/Usuarios/"+usuario.cualUser()+"reservas.csv");
                System.out.print("Calificaciones: ");Lector.imprimir("bibliofacil/src/main/resources/Usuarios/"+usuario.cualUser()+"calificaciones.csv");
                System.out.print("Deseados: ");Lector.imprimir("bibliofacil/src/main/resources/Usuarios/"+usuario.cualUser()+"deseados.csv");
                dibujarMenuInteraccion(usuario);
                break;
            default:
                System.out.println("Opción inválida");
                dibujarMenuInteraccion(usuario);
                break;
        }
    }

    public static void dibujarMenuInteraccion(Administrador administrador){
        int seleccion;
        System.out.println("Seleccione una de las opciones");
        System.out.println("[1] Mostrar usuarios\n[2] Mostrar libros" +
                "\n[3]Añadir usuario\n[4]Eliminar usuario\n[5]Añadir libro\n[6]Eliminar libro");
        seleccion = validarEntero("Ingrese su opción: ");
        scanner.nextLine();
        switch (seleccion){
            case 1:
                biblioteca.mostrarUsuarios();
                dibujarMenuInteraccion(administrador);
                break;
            case 2:
                biblioteca.mostrarLibros();
                dibujarMenuInteraccion(administrador);
                break;
            case 3:
                System.out.println("Elija el nombre de usuario nuevo: ");
                String usuario = scanner.nextLine();
                System.out.println("Elija la contraseña: ");
                String pass = scanner.nextLine();
                System.out.println("Escoja el nombre: ");
                String nombre = scanner.nextLine();
                biblioteca.masUsuario(new Usuario(usuario, pass, nombre));
                dibujarMenuInteraccion(administrador);
                break;
            case 4:
                System.out.println("Elija el nombre de usuario a eliminar: ");
                String usuarioAEliminar = scanner.nextLine();
                biblioteca.eliminarUsuario(usuarioAEliminar);
                dibujarMenuInteraccion(administrador);
                break;
            case 5:
                System.out.println("Elija el titulo del libro: ");
                String titulo = scanner.nextLine();
                System.out.println("Elija el autor del libro: ");
                String autor = scanner.nextLine();
                System.out.println("Elija la categoria del libro: ");
                String categoria = scanner.nextLine();
                System.out.println("¿Cuántos libros nuevos habrán?: ");
                int cantidad = scanner.nextInt();
                biblioteca.masLibro(new Libro(titulo, autor, categoria, cantidad, 0, 0));
                dibujarMenuInteraccion(administrador);
                break;
            case 6:
                System.out.println("Elija el nombre de libro a eliminar: ");
                String tituloAEliminar = scanner.nextLine();
                biblioteca.eliminarLibro(tituloAEliminar);
                dibujarMenuInteraccion(administrador);
                break;
        }
    }
    public static int validarEntero(String mensaje) {
        int numero;
        do {
            System.out.println(mensaje);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                break;
            } else {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                scanner.nextLine();
            }
        } while (true);
        return numero;
    }
}

