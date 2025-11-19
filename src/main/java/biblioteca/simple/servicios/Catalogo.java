package biblioteca.simple.servicios;
import biblioteca.simple.modelo.Libro;
import biblioteca.simple.modelo.Pelicula;
import biblioteca.simple.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private final List<Producto> productos = new ArrayList<>();//Declararlo final protege el espacio de memoria del Array

    public void alta(Producto p){
        productos.add(p);
    }

    public List<Producto> listar(){
        return new ArrayList<>(productos);
    }

    public List<Producto> buscar(String titulo){ //Buscar por titulo
        List<Producto> res = new ArrayList<>();
        for (Producto p : productos){

            if (p instanceof Pelicula peli){
                System.out.println("Películas disponibles: ");
                p.getPrestado();
            }
            if (p.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                res.add(p);
            }
        }
        return res;
    }
    public List<Producto> buscar(int anio){//Buscar por año
        List<Producto> res = new ArrayList<>();
        for (Producto p : productos){
            if (Integer.parseInt(p.getAnio()) == anio){ // o también -> (p.getAnio() == String.valueOf(anio))
                res.add(p);
            }
        }
        return res;
    }
}

