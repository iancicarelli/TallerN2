public class Sesion {
    //public String ip;
    public String user;
    private String pase;

    //public long tiempoSesion;
    private Sesion(String user, String pase) {
        this.user = user;
        this.pase = pase;
    }

    public static Sesion conectar(String user, String pase) {
        if (verificar()) return new Sesion(user, pase);
        else return null;
    }

    /*public void desconectar(){
    }*/
    public static boolean verificar() { //lee un archivo y ve si los datos existen.
        return true;
    }

    public static short quienEs(Usuario usuario) {
        return usuario.quienEs();
    }

    public static String quienEsQuien(Usuario usuario) { //tabla sencilla.
        switch (usuario.quienEs()) {
            case 0:
                return "Usuario";
            case 999:
                return "Administrador";
            default:
                return "Desconocido";
        }
    }
}
