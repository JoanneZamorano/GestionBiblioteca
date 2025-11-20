package biblioteca.simple.servicios;

import biblioteca.simple.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
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

}
