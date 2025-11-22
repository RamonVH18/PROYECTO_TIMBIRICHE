package directorio;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;

public class Serializador {

    private final static Gson gson = new Gson();

    public static PaqueteDTO deserializar(String mensaje) {
        PaqueteDTO paquete = gson.fromJson(mensaje, PaqueteDTO.class);
        return paquete;
    }

    public static DireccionDTO deserializarDireccion(JsonObject direccion) {
        DireccionDTO direccionDTO = gson.fromJson(direccion, DireccionDTO.class);
        return direccionDTO;
    }

    public static String serializarDirecciones(List<DireccionDTO> direcciones) {
        JsonObject json = new JsonObject();
        json.addProperty("direcciones", gson.toJson(direcciones));
        String mensaje = gson.toJson(new PaqueteDTO("listaDirecciones", json));
        return mensaje;
    }

}
