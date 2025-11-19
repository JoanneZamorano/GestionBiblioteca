package biblioteca.simple.modelo;

public abstract class Producto {
    protected int id;
    protected String titulo;
    protected String anio;
    protected Formato formato;

    //Constructor para recibir objetos de la bbdd
    protected Producto(int id, String titulo, String anio, Formato formato) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.formato = formato;
    }
    //Constructor para crear nuevos productos desde la app
    public Producto(String titulo, String anio, Formato formato) {
        this.titulo = titulo;
        this.anio = anio;
        this.formato = formato;
    }


    //Getters
    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAnio() {
        return anio;
    }
    protected Formato getFormato() {
        return formato;
    }

    //Setters
    //Métodos
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio='" + anio + '\'' +
                ", formato=" + formato +
                '}';
    }

}
