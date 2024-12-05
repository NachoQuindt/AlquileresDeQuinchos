package egg.proyectofinal.model.enums;

/**
 *
 * @author 54113
 */
public enum Servicio {
    COCHERA("Cochera"),
    PISCINA("Piscina"),
    PARRILLA("Parrilla"),
    INTERNET("Internet");

    private final String nombre;

    Servicio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
