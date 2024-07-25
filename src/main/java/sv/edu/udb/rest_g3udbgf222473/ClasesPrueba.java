package sv.edu.udb.rest_g3udbgf222473;

public class ClasesPrueba {
    private String nombreClase;
    private String descripcionClase;

    public ClasesPrueba(String nombreClase, String descripcionClase) {
        this.nombreClase = nombreClase;
        this.descripcionClase = descripcionClase;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getDescripcionClase() {
        return descripcionClase;
    }

    public void setDescripcionClase(String descripcionClase) {
        this.descripcionClase = descripcionClase;
    }
}
