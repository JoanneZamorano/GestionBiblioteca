package biblioteca.simple.app;

import biblioteca.simple.contratos.Prestable;
import biblioteca.simple.modelo.*;
import biblioteca.simple.servicios.Catalogo;
import biblioteca.simple.servicios.PersistenciaUsuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    private static final Catalogo catalogo = new Catalogo();
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {


            cargarDatos();
            menu();

    }

    //CARGA DE DATOS INICIALES PARA PRUEBAS:
    private static void cargarDatos(){
        //Libro
        catalogo.alta(new Libro(1, "Harry Potter y la priedra filosofal", "1998", Formato.FISICO, "11122233344555", "J.K.Rowling"));
        catalogo.alta(new Libro(2, "El nombre del viento", "2007", Formato.FISICO, "113654555", "Patrick Rothfuss"));
        //Pelicula
        catalogo.alta(new Pelicula(3, "Intocable (Intouchables)", "2011", Formato.FISICO, "Olivier Nakache, Éric Toledano", 112));
        catalogo.alta(new Pelicula(4, "Figuras ocultas", "2017", Formato.FISICO, "Theodore Melfi", 127));
        //Videojuego
        catalogo.alta(new Videojuego(5,"Assassins Creed II", "2009", Formato.FISICO, "Xbox", 18));
        catalogo.alta(new Videojuego(6,"LOL: League of Legends", "2009", Formato.DIGITAL, "PC", 12));

        usuarios.add(new Usuario(1,"Joa"));
        usuarios.add(new Usuario(2,"Adrián"));

    }

    // MENÚ
    private static void menu(){
        int opcion;

        do{
            System.out.println("\n===Menú de Biblioteca===");
            System.out.println("Gestión Producto:");
            System.out.println("\t1. Listar Productos disponibles");
            System.out.println("\t2. Buscar por titulo");
            System.out.println("\t3. Buscar por año");
            System.out.println("\t4. Prestar Producto");
            System.out.println("\t5. Devolver Producto");
            System.out.println("Gestión Usuarios:");
            System.out.println("\t6. Listar usuarios");
            System.out.println("\t7. Crear nuevo usuario");
            System.out.println("\t8. Exportar usuarios");
            System.out.println("\t9. Importar usuarios");
            System.out.println("0. Salir");

            //Para si el usuario mete otra cosa que no sea int, vuelva a aparecer el menú:
            while (!sc.hasNextInt()) sc.next();

            //Entrada de Scanner
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> listar();
                case 2 -> buscarPorTitulo();
                case 3 -> buscarPorAnio();
                case 4 -> prestar();
                case 5 -> devolver();
                case 6 -> listarUsuarios();
                case 7 -> crearUsuario();
                case 8 -> exportarUsuarios();
                case 9 -> importarUsuarios();
                case 0 -> System.out.println("...ADIOS!");
                default -> System.out.println("Opción no válida");
            }

        }while(opcion != 0);
    }

    // LISTAR PRODUCTOS
    private static void listar(){
        List<Producto> lista = catalogo.listar();

        if (lista.isEmpty()){
            System.out.println("Catálogo vacío.");
            return;
        }
        System.out.println("===Lista de Productos===");

        for (Producto p:lista){
            //
            if (p instanceof Prestable) {//casting para tratarlo como Prestable y acceder a sus métodos
                if (((Prestable) p).estaPrestado()) {
                    continue; //si está prestado salta al siguiente ciclo y NO imprime
                }
            }
            System.out.println(" - " + p);
        }
    }

    // BUSCAR POR TÍTULO
    private static void buscarPorTitulo(){
        System.out.println("Titulo (escribe parte del titulo): ");
        String t = sc.nextLine();
        catalogo.buscar(t).forEach(p -> System.out.println(" - " + p));
    }

    // BUSCAR POR AÑO
    private static void buscarPorAnio(){
        System.out.println("Año: ");
        int a = sc.nextInt();
        sc.nextLine();
        catalogo.buscar(a).forEach(p -> System.out.println(" - " + p));
    }


    // PRESTAR PRODUCTO
    private static void prestar(){
        //1: Mostrar SOLO productos disponibles
        List<Producto> disponibles = catalogo.listar().stream()
                .filter(p -> p instanceof Prestable pN && !pN.estaPrestado())
                .collect(Collectors.toList());

        if (disponibles.isEmpty()){
            System.out.println("No hay productos para prestar");
            return;
        }
        System.out.println("--Productos disponibles--");
        disponibles.forEach(p-> System.out.println( " \t- ID: " + p.getId() + " | " + p));

        System.out.println("Escribe el ID del producto a prestar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Producto pEncontrado = disponibles.stream()
                .filter(p -> {
                    try {
                        return p.getId() == id;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })

                .findFirst()
                .orElse(null);

        if (pEncontrado == null){
            System.out.println("El id no existe");
            return;
        }

        //2: SELECCIÓN DE USUARIO
        Usuario u1 = null;

        do {
            listarUsuarios();
            System.out.println("Ingresa el ID del usuario (o -1 para cancelar el préstamo):");

            int cUsuario;
            if (sc.hasNextInt()) {
                cUsuario = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Por favor, ingresa un ID");
                continue;
            }

            if (cUsuario == -1) {
                System.out.println("Préstamo cancelado");
                return;
            }

            u1 = getUsuarioPorCodigo(cUsuario); //bUSCA POR id

            if (u1 == null) {
                System.out.println("Usuario con ID " + cUsuario + " no encontrado. " +
                        "\n¿Quieres registrar un nuevo usuario y continuar con el préstamo? (s/n)");
                String respuesta = sc.nextLine().trim().toLowerCase();

                if (respuesta.equals("s")) {
                    u1 = crearUsuario();
                } else{ //Vuelve a pedir id de los usuarios
                    System.out.println("Volviendo a la pantalla anterior... Por favor, ingresa un ID:");
                }
            }
        } while (u1 == null);

        if (u1 != null) {
            Prestable pPrestable = (Prestable) pEncontrado;
            pPrestable.prestar(u1);
            System.out.println("--------------");
            System.out.println("Préstamo REALIZADO:" +
                    "\n\tProducto: " + pEncontrado +
                    "\n\tID: " + u1.getId() + " | Usuario: " + u1.getNombre());
            System.out.println("--------------");
        }
    }

    // DEVOLVER PRODUCTO
    private static void devolver(){
        List<Producto> pPrestados = catalogo.listar().stream()
                .filter(p -> p instanceof Prestable pN && pN.estaPrestado())
                .collect(Collectors.toList());

        if ( pPrestados.isEmpty() ) {
            System.out.println("No hay productos para prestar");
            return;
        }

        System.out.println("-- PRODUCTOS PRESTADOS --");
        pPrestados.forEach( p -> System.out.println("- ID: " + p.getId() + " | " + p));

        System.out.println("Escribe el id del producto: ");
        int id = sc.nextInt();
        sc.nextLine();

        Producto pEncontrado = pPrestados.stream()
                .filter(p -> {
                    try {
                        return p.getId() == id;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);

        if (pEncontrado == null){
            System.out.println("El id no existe");
            return;
        }

        Prestable pE = (Prestable) pEncontrado;
        pE.devolver();
        System.out.println("--------------");
        System.out.println("Devolución REALIZADA:" +
                "\n\tProducto: " + pEncontrado);
        System.out.println("--------------");
    }


    // CREAR USUARIOS
    public static Usuario crearUsuario(){
        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        //Mostrar siguiente ID para asignarselo automaticamente al usuario
        int maxId = 0;
        for (Usuario u : usuarios) {
            if (u.getId() > maxId) {
                maxId = u.getId();
            }
        }
        int id = maxId + 1;
        System.out.println("ID asignado: " + id);

        //Añadir usuario
        Usuario nuevoUsuario = new Usuario(id, nombre);
        usuarios.add(nuevoUsuario);

        System.out.println("--------------");
        System.out.println("Usuario nuevo registrado correctamente.\n\tNombre: " + nombre + " | ID: " + id);
        System.out.println("--------------");

        return nuevoUsuario;
    }

    // LISTAR USUARIOS
    private static void listarUsuarios(){
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
            return;
        }
        System.out.println("Lista usuarios");
        usuarios.forEach( u ->
                System.out.println("- Código : " + u.getId() + "| Nombre: " + u.getNombre() )
        );
    }

    // LISTAR USUARIO POR ID
    private static Usuario getUsuarioPorCodigo(int id){
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // EXPORTAR USUARIOS
    private static void exportarUsuarios(){

        //Gestionar las excepciones:
        try {
            PersistenciaUsuarios.exportar(usuarios);
            System.out.println("...Usuarios exportados correctamente!");
        } catch (Exception e) {
            System.out.println("Error al exportar usuarios: " + e.getMessage());
        }
    }

    // IMPORTAR USUARIOS
    private static void importarUsuarios(){
        //Gestionar las excepciones:
        try {
            List<Usuario> cargados = PersistenciaUsuarios.importar();
            usuarios.clear(); //borra todos los usuarios
            usuarios.addAll(cargados); //mete todos los usuarios nuevos

            System.out.println("...Usuarios importados correctamente!");
        } catch (Exception e) {
            System.out.println("Error al importar usuarios: " + e.getMessage());
        }

    }

}
