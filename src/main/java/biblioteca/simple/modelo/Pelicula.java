package biblioteca.simple.modelo;
import biblioteca.simple.contratos.Prestable;

public class Pelicula extends Producto implements Prestable {
    private String director;
    private int minDuracion;
    private boolean prestado;
    private Usuario prestadoA;

    //Constructor para modificaciones
    public Pelicula(int id, String titulo, String anio, Formato formato, String director, int minDuracion) {
        super(id, titulo, anio, formato);
        this.director = director;
        this.minDuracion = minDuracion;
    }
    //Constructor para creación
    public Pelicula(String titulo, String anio, Formato formato, String director, int minDuracion) {
        super(titulo, anio, formato);
        this.director = director;
        this.minDuracion = minDuracion;
    }

    //Getters
    public String getDirector() {
        return director;
    }
    public int getMinDuracion() {
        return minDuracion;
    }


    @Override public void prestar(Usuario u) {
        if(prestado) throw new IllegalStateException("Ya está prestado.");
        this.prestado = true;
        this.prestadoA = u;
    }

    @Override public void devolver() {
        this.prestado = false;
        this.prestadoA = null;
    }

    @Override public boolean estaPrestado() {
        return this.prestado;
    }

    //Métodos
    @Override
    public String toString() {
        return "Pelicula{" +
                "director='" + director + '\'' +
                ", minDuracion=" + minDuracion +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio='" + anio + '\'' +
                ", formato=" + formato +
                '}';
    }
}
