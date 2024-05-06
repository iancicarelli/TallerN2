import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private int ejemplaresDisponibles;
    private List<Integer> calificaciones;
    private float calificacion;
    private int numCalificacion;
    private List<String> comentarios;

    public Libro(String titulo, String autor, String categoria, int ejemplaresDisponibles, float calificacion, int numCalificacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.calificacion = calificacion;
        this.calificaciones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.numCalificacion = numCalificacion;
    }

    private void actualizar() {
        comentarios.clear();
        String path = "/bibliofacil/src/main/resources/Libros/";
        for (int i = 0; i < Lector.cantidadDeLineas(path+cualTitulo()+"comentarios.csv"); i++) {
            if(i!=0){
                comentarios.add(Lector.leer(path+cualTitulo()+"comentarios.csv",i));
            }
        }
    }

    public float mediaCalificaciones(){
        int suma = 0;
        if(!calificaciones.isEmpty()) {
            for (int calificacion : calificaciones) {
                suma += calificacion;
            }
        }
        return (float) (suma+(calificacion*numCalificacion) / (calificaciones.size()+numCalificacion));
    }

    public void agregarCalificacion(int calificacion) {
        numCalificacion++;
        calificaciones.add(calificacion);
    }

    public void agregarComentario(String comentario) throws IOException {
        if(!Lector.sePuedeCrearQ("bibliofacil/src/main/resources/Libros/"+cualTitulo()+"comentarios.csv")){
            Lector.escribir("bibliofacil/src/main/resources/Libros/"+cualTitulo()+"calificaciones.csv",comentario);
        }
        actualizar();
    }

    public String cualTitulo(){
        return titulo;
    }

    public void editarDisponible(int pedido){
        this.ejemplaresDisponibles-=pedido;
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%d,%f,%d", titulo, autor, categoria, ejemplaresDisponibles, mediaCalificaciones(), numCalificacion);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ejemplaresDisponibles=" + ejemplaresDisponibles +
                ", calificacion=" + mediaCalificaciones() +
                ", comentarios=" + comentarios +
                '}';
    }
}
