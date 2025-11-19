package biblioteca.simple.modelo;
import biblioteca.simple.contratos.Prestable;

public class Libro extends Producto implements Prestable {
    private String ISBN;
    private String autor;
    private boolean prestado;
    private Usuario prestadoA;

    //Constructor para modificaciones
    public Libro(int id, String titulo, String anio, Formato formato, String ISBN, String autor) {
        super(id, titulo, anio, formato);
        this.ISBN = ISBN;
        this.autor = autor;
    }
    //Constructor para creación
    public Libro(String titulo, String anio, Formato formato, String ISBN, String autor) {
        super(titulo, anio, formato);
        this.ISBN = ISBN;
        this.autor = autor;
    }

    //Getters
    public String getISBN() {
        return ISBN;
    }
    public String getAutor() {
        return autor;
    }

    //Implementación Interfaz Prestable
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
        return "Libro{" +
                "ISBN='" + ISBN + '\'' +
                ", autor='" + autor + '\'' +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio='" + anio + '\'' +
                ", formato=" + formato +
                '}';
    }
}
