package dm2e.juanalberto.puzzle.models;

public class UsuarioPuzzle {
    private int id;
    private String nombreUsuario;
    private int puntuacion;

    public UsuarioPuzzle(int id, String nombreUsuario, int puntuacion) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
