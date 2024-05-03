import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    public String nombre;
    public List<Usuario> usuarios;
    public List<Libro> libros;

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

    /*
    public void prestarLibroA(Usuario usuario, Libro libro) {
    }*/

    public String verLibro(Libro libro) {
        return libro.toString();
    }
}
