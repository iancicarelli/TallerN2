import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Sesion {
    //public String ip;
    //public String idSesion;
    public String user;
    private String pase;

    //public long tiempoSesion;
    private Sesion(String user, String pase) {
        this.user = user;
        this.pase = pase;
        //this.idSesion = String.valueOf((int)(Math.random()*(100000)+Math.random()*(10000)+Math.random()*(1000)+Math.random()*100)+Math.random()*10);
    }

    public static Sesion conectar(String user, String pase) {
        if (verificar()) return new Sesion(user, pase);
        else return null;
    }

    /*public void desconectar(){
    }*/

    public static boolean verificar() {//lee un archivo y ve si los datos existen.
        return true;
    }

    /*public String getIdSesion() {
        return idSesion;
    }*/

    public static short quienEs(Usuario usuario) {
        return usuario.quienEs();
    }
    public String cualUsuario() {
        return this.user;
    }

    public static String quienEsQuien(Usuario usuario) { //tabla sencilla.
        switch (usuario.quienEs()) {
            case -1:
                return "Usuario sancionado";
            case 0:
                return "Usuario";
            case 5:
                return "Súper usuario";
            case 999:
                return "Administrador";
            default:
                return "Desconocido";
        }
    }
}
