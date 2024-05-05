import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String nombre;
    String user;
    String pase;
    // private String tipo; // Estudiante, Profesor, Personal de la Biblioteca
    private List<Libro> historialPrestamos;
    private List<Libro> librosReservados;
    private List<Integer> calificaciones;
    private List<Libro> listaDeDeseados;

    public Usuario(String user, String pase) {
        this.nombre = "";
        this.user = user;
        this.pase = pase;
        this.historialPrestamos = new ArrayList<>();
        this.librosReservados = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
        this.listaDeDeseados = new ArrayList<>();
    }

    public String cualNombre(){
        return this.nombre;
    }

    public void editarNombre(String nombre){
        this.nombre = nombre;
    }

    public void agregarPrestamo(Libro libro) {
        historialPrestamos.add(libro);
    }

    public void realizarReserva(Libro libro) {
        librosReservados.add(libro);
    }

    public void agregarCalificacion(int calificacion) {
        calificaciones.add(calificacion);
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + "}";
    }
    public String toCSV() {
        return String.format("%s,%s,%s", nombre, user, pase);
    }
}
