public class Admin extends Usuario {//administrador es una palabra muy larga.
    public Admin(Sesion sesion){
        super(sesion);
        this.cual = 999; //la acción más privilegiada, cual = 9. PRIVILEGIO.
    }
}
