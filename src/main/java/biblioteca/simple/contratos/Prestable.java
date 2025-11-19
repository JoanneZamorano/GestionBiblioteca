package biblioteca.simple.contratos;
import biblioteca.simple.modelo.Usuario;

public interface Prestable {
    //Métodos
    void prestar(Usuario u);
    void devolver();
    boolean estaPrestado();
}
