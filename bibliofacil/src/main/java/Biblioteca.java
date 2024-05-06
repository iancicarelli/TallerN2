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
        actualizar();
    }

    public void actualizar(){
        String path = "bibliofacil/src/main/resources/";
        usuarios.clear();
        libros.clear();
        for (int i = 0; i < Lector.cantidadDeLineas(path+"Usuarios.csv"); i++) {
            if(i!=0){
                String[] columnas =  Lector.leer(path+"Usuarios.csv",i).split(",");
                usuarios.add(new Usuario(columnas[0],columnas[1],columnas[2]));
            }
        }
        for (int i = 0; i < Lector.cantidadDeLineas(path+"Libros.csv"); i++) {
            if(i!=0){
                String[] columnas =  Lector.leer(path+"Libros.csv",i).split(",");
                libros.add(new Libro(columnas[0],columnas[1],columnas[2],
                        Integer.parseInt(columnas[3]),Float.parseFloat(columnas[4]),
                        Integer.parseInt(columnas[5])));
            }
        }
    }

    public Libro seleccionarLibro(String busqueda){
        String[] seleccion = Lector.leer("bibliofacil/src/main/resources/Libros.csv",
                Lector.buscar("bibliofacil/src/main/resources/Libros.csv",busqueda)).split(",");
        if(Lector.buscar("bibliofacil/src/main/resources/Libros.csv",busqueda)!=0){
            return new Libro(seleccion[0],seleccion[1],seleccion[2],Integer.parseInt(seleccion[3]),Float.parseFloat(seleccion[4]),Integer.parseInt(seleccion[5]));
        }
        return null;
    }

    public void masUsuario(Usuario usuario) {
        String path = "bibliofacil/src/main/resources/Usuarios.csv";
        String pathFolder = "bibliofacil/src/main/resources/Usuarios/";
        Lector.escribir(path, usuario.toCSV());
        Lector.crear(pathFolder+usuario.cualUser()+"prestamos.csv");
        Lector.crear(pathFolder+usuario.cualUser()+"reservas.csv");
        Lector.crear(pathFolder+usuario.cualUser()+"calificaciones.csv");
        Lector.crear(pathFolder+usuario.cualUser()+"deseados.csv");
        Lector.escribir(pathFolder+usuario.cualUser()+"prestamos.csv",
                "Titulo,Autor,Categoria,Ejemplares Disponibles,Calificacion,Numero De Calificaciones");
        Lector.escribir(pathFolder+usuario.cualUser()+"reservas.csv",
                "Titulo,Autor,Categoria,Ejemplares Disponibles,Calificacion,Numero De Calificaciones");
        Lector.escribir(pathFolder+usuario.cualUser()+"calificaciones.csv",
                "Calificacion");
        Lector.escribir(pathFolder+usuario.cualUser()+"deseados.csv",
                "Titulo,Autor,Categoria,Ejemplares Disponibles,Calificacion,Numero De Calificaciones");
        actualizar();
    }

    public void masLibro(Libro libro) {
        String path = "bibliofacil/src/main/resources/Libros.csv";
        Lector.escribir(path, libro.toCSV());
        Lector.crear("bibliofacil/src/main/resources/Libros/"+libro.cualTitulo()+"comentarios.csv");
        Lector.escribir("bibliofacil/src/main/resources/Libros/"+libro.cualTitulo()+"comentarios.csv","Comentario");
        actualizar();
    }

    public void eliminarUsuario(String user) {
        String path = "bibliofacil/src/main/resources/Libros.csv"; //directorio de libros.
        Lector.borrar(path, Lector.buscar(path, user));
        actualizar();
    }

    public void eliminarLibro(String titulo) {
        String path = "bibliofacil/src/main/resources/Libros.csv"; //directorio de libros.
        Lector.borrar(path, Lector.buscar(path, titulo));
        actualizar();
    }

    public String verLibro(Libro libro) {
        return libro.toString();
    }

    public void mostrarLibros(){
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }

    public void mostrarUsuarios(){
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString());
        }
    }
/*
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

 */
}
