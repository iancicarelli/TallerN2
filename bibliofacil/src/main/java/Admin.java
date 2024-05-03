public class Admin extends Usuario {//administrador es una palabra muy larga.
    private final Sesion sesion;

    public Admin(Sesion sesion) {
        super(sesion);
        this.sesion = sesion;
        this.cual = 999; //la acción más privilegiada, cual = 999. PRIVILEGIO.
    }

    public void modificarPrivilegiosDe(Usuario usuario, short este) {
        if (usuario.quienEs()!=999&&!usuario.cualSesion().equals(this.sesion)) {
            //si no es otro admin, ni sí mismo
            usuario.quienSera(este);
        }
    }

    /*public void sancionarUsuario(Usuario usuario) {
    }*/

    public String verUsuario(Usuario usuario) {
        return usuario.toString();
    }

    /*Mejorar abstracción con relación a las clases Administrador y Biblioteca*/
}
