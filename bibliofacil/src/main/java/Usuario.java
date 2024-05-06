import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String nombre;
    String user;
    String pase;
    private List<Libro> historialPrestamos;
    private List<Libro> librosReservados;
    private List<String> calificaciones; //cambiado a String.
    private List<Libro> listaDeDeseados;

    public Usuario(String user, String pase, String nombre) {
        this.nombre = nombre;
        this.user = user;
        this.pase = pase;
        this.historialPrestamos = new ArrayList<>();
        this.librosReservados = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
        this.listaDeDeseados = new ArrayList<>();
        actualizar();
    }

    public void actualizar(){
        String path = "bibliofacil/src/main/resources/Usuarios/";
        this.historialPrestamos.clear();
        this.librosReservados.clear();
        this.calificaciones.clear();
        this.listaDeDeseados.clear();
        for (int i = 0; i < Lector.cantidadDeLineas(path+cualUser()+"prestamos.csv"); i++) {
            if(i!=0){
                String[] columnas =  Lector.leer(path+cualUser()+"prestamos.csv",i).split(",");
                historialPrestamos.add(new Libro(columnas[0],columnas[1],columnas[2],
                        Integer.parseInt(columnas[3]),Float.parseFloat(columnas[4]),Integer.parseInt(columnas[5])));
            }
        }
        for (int i = 0; i < Lector.cantidadDeLineas(path+cualUser()+"reservas.csv"); i++) {
            if(i!=0){
                String[] columnas =  Lector.leer(path+cualUser()+"reservas.csv",i).split(",");
                librosReservados.add(new Libro(columnas[0],columnas[1],columnas[2],
                        Integer.parseInt(columnas[3]),Float.parseFloat(columnas[4]),Integer.parseInt(columnas[5])));
            }
        }
        for (int i = 0; i < Lector.cantidadDeLineas(path+cualUser()+"calificaciones.csv"); i++) {
            if(i!=0){
                calificaciones.add(Lector.leer(path+cualUser()+"calificaciones.csv",i));
            }
        }
        for (int i = 0; i < Lector.cantidadDeLineas(path+cualUser()+"deseados.csv"); i++) {
            if(i!=0){
                String[] columnas =  Lector.leer(path+cualUser()+"deseados.csv",i).split(",");
                listaDeDeseados.add(new Libro(columnas[0],columnas[1],columnas[2],
                        Integer.parseInt(columnas[3]),Float.parseFloat(columnas[4]),Integer.parseInt(columnas[5])));
            }
        }
    }

    public String cualNombre(){
        return this.nombre;
    }

    public String cualUser(){
        return this.user;
    }

    public void agregarPrestamo(Libro libro) throws IOException {
        if(!Lector.sePuedeCrearQ("bibliofacil/src/main/resources/Usuarios/"+cualUser()+"prestamos.csv")){
            Lector.escribir("bibliofacil/src/main/resources/Usuarios/"+cualUser()+"prestamos.csv",libro.toCSV());
            libro.editarDisponible(1);
            historialPrestamos.add(libro);
        }
        actualizar();
    }

    public void realizarReserva(Libro libro) throws IOException {
        if(!Lector.sePuedeCrearQ("bibliofacil/src/main/resources/Usuarios/"+cualUser()+"reservas.csv")){
            Lector.escribir("bibliofacil/src/main/resources/Usuarios/"+cualUser()+"reservas.csv",libro.toCSV());
            libro.editarDisponible(1);
            librosReservados.add(libro);
        }
        actualizar();
    }

    public void agregarCalificacion(int calificacion, Libro libro) throws IOException {
        if(!Lector.sePuedeCrearQ("bibliofacil/src/main/resources/Usuarios/"+cualUser()+"calificaciones.csv")){
            if(calificacion>=0&&calificacion<=5){
                Lector.escribir("bibliofacil/src/main/resources/Usuarios/"+cualUser()+"calificaciones.csv",libro.cualTitulo() + " - " + calificacion);
                libro.agregarCalificacion(calificacion);
                calificaciones.add(libro.cualTitulo() + " - " + calificacion);
            }
        }
        actualizar();
    }

    public void masLibroDeseado(Libro libro) throws IOException {
        if(!Lector.sePuedeCrearQ("bibliofacil/src/main/resources/Usuarios/"+cualUser()+"deseados.csv")){
            Lector.escribir("bibliofacil/src/main/resources/Usuarios/"+cualUser()+"deseados.csv",libro.toCSV());
            librosReservados.add(libro);
        }
        actualizar();
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ","+historialPrestamos.toString()+",\n"+librosReservados.toString()+
                ",\n"+calificaciones.toString()+",\n"+listaDeDeseados.toString()+"}";
    }

    public String toCSV() {
        return String.format("%s,%s,%s", nombre, user, pase);
    }
}
