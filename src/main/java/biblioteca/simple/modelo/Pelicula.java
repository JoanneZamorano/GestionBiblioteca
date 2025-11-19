package biblioteca.simple.modelo;

public class Pelicula extends Producto{
    private String director;
    private int minDuracion;

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
