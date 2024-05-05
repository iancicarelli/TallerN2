import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Biblioteca {
    public String nombre;
    public List<Usuario> usuarios;
    public List<Libro> libros;

    private  Scanner teclado = new Scanner(System.in);

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
    }

    public void masUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void masLibro(Libro libro) {
        this.libros.add(libro);
    }

    public void eliminarUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
    }

    public void eliminarLibro(Libro libro) {
        this.libros.remove(libro);
    }

    public void crearUsuario(String user, String pase) { //Por defecto, privilegios = 0.
        crearUsuario(user, pase, (short) 0);
    }

    public void crearUsuario(String user, String pase, short cual) {
        String nombreArchivo = "bibliofacil/src/main/resources/Usuarios.csv";
        try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
            writer.write(String.format("%s,%s,%d,%s,%s,%s,%s", user, pase, cual, "[]", "[]", "[]", "[]")+"\n");
            System.out.println("Usuario creado éxitosamente.");
        } catch (IOException e) {
            System.out.println("Error al acceder el archivo CSV: " + e.getMessage());
        } try {
            Usuario usuario = new Usuario(Sesion.conectar(user, pase));
            if(usuario.cualSesion()!=null) {
                masUsuario(usuario);
            }
        } catch (Exception e){
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }
    public static void actualizar(){

    }

    public boolean escanearUsuarioExistente(String user) {
        for (Usuario usuario : usuarios) {
            if (usuario.cualSesion().cualUsuario().equals(user)) return true; else return false;
        }return false;
    }

    /*
    public void prestarLibroA(Usuario usuario, Libro libro) {
    }*/

    public String verLibro(Libro libro) {
        return libro.toString();
    }
    public void mostrarLibros(){
        for (Libro libro : libros) {
            System.out.println(verLibro(libro));
        }
    }
    public void crearLibro() {
        System.out.println("Ingrese el título del libro");
        String titulo = teclado.nextLine();
        System.out.println("Ingrese el autor del libro");
        String autor = teclado.nextLine();
        System.out.println("Ingrese la categoria del libro");
        String categoria = teclado.nextLine();
        int cantidad = validarEntero("Ingrese los ejemplares disponibles");
        masLibro(new Libro(titulo,autor,categoria,cantidad));
        agregarLibroACSV(new Libro(titulo,autor,categoria,cantidad));
    }

    private int validarEntero(String mensaje) {
        int numero;
        do {
            System.out.println(mensaje);
            if (teclado.hasNextInt()) {
                numero = teclado.nextInt();
                break;
            } else {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                teclado.nextLine();
            }
        } while (true);
        return numero;
    }

    public void eliminarLibroPorIndice() {
        mostrarLibros();
        int indice;
        do {
            indice = validarEntero("Ingrese el índice del libro que desea eliminar:");
            if (indice >= 0 && indice < libros.size()) {
                Libro libro = libros.get(indice);
                eliminarLibro(libro);
                eliminarLibroDelCSV(indice);
                System.out.println("Libro eliminado correctamente.");
                break;
            } else {
                System.out.println("Índice inválido. Por favor, ingrese un índice válido.");
            }
        } while (true);
    }
    public void agregarLibroACSV(Libro libro) {
        String nombreArchivo = "bibliofacil/src/main/resources/Libros.csv";
        try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
            writer.write(libro.toCSV() + "\n");
            System.out.println("Libro agregado al archivo CSV correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    public void mostrarLibrosDesdeCSV() {
        String nombreArchivo = "bibliofacil/src/main/resources/Libros.csv";
        String linea;
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    } /*!!!HACER UNA CLASE "ACTUALIZAR" PARA QUITAR LA REDUNDANCIA DE LEER Y ESCRIBIR!!!*/

    private void eliminarLibroDelCSV(int indice) {
        String nombreArchivo = "bibliofacil/src/main/resources/Libros.csv";
        List<String> lineas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
            return;
        }

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < lineas.size(); i++) {
                if (i != indice) {
                    writer.write(lineas.get(i) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }
}
