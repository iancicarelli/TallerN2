import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Usuario {
    private final Sesion sesion;
    String nombre;
    short cual;
    // private String tipo; // Estudiante, Profesor, Personal de la Biblioteca
    private List<Libro> historialPrestamos;
    private List<Libro> librosReservados;
    private List<Integer> calificaciones;
    private List<Libro> listaDeDeseados;
    private List<String> boletas;

    public Usuario(Sesion sesion) {
        this.sesion = sesion;
        this.nombre = "";
        // this.tipo = tipo; //cambiado por "cual".
        this.cual = 0; //la acción más básica, cual = 0. PRIVILEGIO.
        this.historialPrestamos = new ArrayList<>();
        this.librosReservados = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
        this.listaDeDeseados = new ArrayList<>();
        this.boletas = new ArrayList<>();
    }

    public short quienEs(){
        return this.cual;
    }

    public void quienSera(short cual){
        this.cual = cual;
    }

    public Sesion cualSesion(){
        return this.sesion;
    }

    public void editarNombre(String nombre){
        this.nombre = nombre;
    }

    public String cualNombre(){
        return this.nombre;
    }

    public void agregarPrestamo(Libro libro) {
        Date fecha = new Date();
        boletas.add(libro.toString() + "\n" + "Fecha emitida: " + fecha.toString()
        + "\n" + "Debe devolverlo en siete días. \n");
        historialPrestamos.add(libro);
    }

    public void realizarReserva(Libro libro) {
        librosReservados.add(libro);
    }

    public void agregarCalificacion(int calificacion, Libro libro) {
        libro.agregarCalificacion(calificacion);
        calificaciones.add(calificacion);
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", privilegio=" + cual + "}";
    }
}
