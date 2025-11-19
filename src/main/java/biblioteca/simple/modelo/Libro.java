package biblioteca.simple.modelo;

public class Libro extends Producto{
    private String ISBN;
    private String autor;

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
