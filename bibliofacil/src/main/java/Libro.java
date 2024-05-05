import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private int ejemplaresDisponibles;
    private List<Integer> calificaciones;
    private List<String> comentarios;

    public Libro(String titulo, String autor, String categoria, int ejemplaresDisponibles) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.calificaciones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public void agregarCalificacion(int calificacion) {
        calificaciones.add(calificacion);
    }

    public void agregarComentario(String comentario) {
        comentarios.add(comentario);
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%d", titulo, autor, categoria, ejemplaresDisponibles);
    }
    public float mediaCalificacion(){
        float media = 0;
        for (Integer calificacion : calificaciones) {
            media += calificacion;
        }
        return media/(float)calificaciones.size();
    }
    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ejemplaresDisponibles=" + ejemplaresDisponibles +
                ", calificaciones=" + mediaCalificacion() +
                ", comentarios=" + comentarios +
                '}';
    }
}
