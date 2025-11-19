package biblioteca.simple.modelo;

public class Usuario {
    private int id;
    private String nombre;

    //Constructor
    public Usuario(String nombre) { //Crear usuario nuevo
        this.nombre = nombre;
    }

    public Usuario(int id, String nombre) { //Crear usuario desde la bbdd
        this.id = id;
        this.nombre = nombre;
    }


    //Getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }


    //Setters - para modificar/actualizar datos
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Métodos

}
