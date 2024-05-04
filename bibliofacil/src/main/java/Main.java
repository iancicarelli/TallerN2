public class Main {
    public static void main(String[] args) {
       //Prueba de usuarios.
        Usuario usuario = new Usuario(Sesion.conectar("user","user"));
        Admin admin = new Admin(Sesion.conectar("admin","admin"));
        //Editar nombre.
        usuario.editarNombre("Ian Cicarelli");
        System.out.print(usuario.queNombre() + " - ");
        System.out.println(Sesion.quienEsQuien(usuario));
        admin.editarNombre("Juan Lagos");
        System.out.print(admin.queNombre() + " - ");
        System.out.println(Sesion.quienEsQuien(admin));
        //Prueba Biblioteca con CSV
        Biblioteca biblioteca = new Biblioteca("uno");
        biblioteca.crearLibro();
        biblioteca.mostrarLibrosDesdeCSV();
        biblioteca.mostrarLibros();
        biblioteca.eliminarLibroPorIndice();
    }
}
