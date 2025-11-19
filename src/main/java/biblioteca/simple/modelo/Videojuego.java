package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;

public class Videojuego  extends Producto implements Prestable {
    private String plataforma; //PS5, PC, Switch..
    private int edadMinima;
    private boolean prestado;
    private Usuario prestadoA;

    //Constructor para modificaciones
    public Videojuego(int id, String titulo, String anio, Formato formato, String plataforma, int edadMinima) {
        super(id, titulo, anio, formato);
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
    }

    //Constructor para creación
    public Videojuego(String titulo, String anio, Formato formato, String plataforma, int edadMinima) {
        super(titulo, anio, formato);
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
    }

    //Getters
    public String getPlataforma() {
        return plataforma;
    }
    public int getEdadMinima() {
        return edadMinima;
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

    @Override
    public String toString() {
        return "Videojuego{" +
                "plataforma='" + plataforma + '\'' +
                ", edadMinima=" + edadMinima +
                ", prestado=" + prestado +
                ", prestadoA=" + prestadoA +
                ", formato=" + formato +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio='" + anio + '\'' +
                '}';
    }
}
