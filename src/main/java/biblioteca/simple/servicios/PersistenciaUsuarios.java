package biblioteca.simple.servicios;

import biblioteca.simple.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class PersistenciaUsuarios {

    private static final String ARCHIVO = "usuarios.json"; //Nombre del archivo para guardar y recuperar usuarios
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create(); //Para formato correcto, que no esté to-do en una linea

    //Constructor
    private PersistenciaUsuarios() {
    }

    public static void exportar (List<Usuario> usuarios) throws IOException {
        try(Writer w = new OutputStreamWriter(new FileOutputStream(ARCHIVO), StandardCharsets.UTF_8)){
            gson.toJson(usuarios,w);
        }
    }

    public static List<Usuario> importar()throws IOException{
        File f = new File(ARCHIVO);

        if(!f.exists())return new ArrayList<>();

        try(Reader r = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8)){
            Type tipoLista = new TypeToken<ArrayList<Usuario>>(){}.getType();
            List<Usuario> lista = gson.fromJson(r, tipoLista);
            return (lista != null ? lista : new ArrayList<>());
        }

    }

}
