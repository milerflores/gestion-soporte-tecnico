package pe.edu.idat.proyecto.gestion.repository;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Repository;
import pe.edu.idat.proyecto.gestion.model.Solicitud;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SolicitudRepository {
    private final Map<Integer, Solicitud> solicitudes = new HashMap<>();
    private AtomicInteger secuencialSolicitud = new AtomicInteger();

    public Solicitud create(Solicitud solicitud){
        if(solicitud.getIdSolicitud() == null){
            solicitud.setIdSolicitud(secuencialSolicitud.getAndIncrement());
        }
        solicitudes.put(solicitud.getIdSolicitud(),solicitud);
        return solicitud;
    }

    public List<Solicitud> list(){
        return new ArrayList<>(solicitudes.values());
    }

    public Optional<Solicitud> searchById(Integer idSolicitud){
        return Optional.ofNullable(solicitudes.get(idSolicitud));
    }

    public Optional<Solicitud> update(Integer idSolicitud, Solicitud solicitud){

        if(solicitudes.containsKey(idSolicitud)){
            solicitudes.put(idSolicitud, solicitud);
            return Optional.of(solicitud);
        }
        return  Optional.empty();
    }

    public boolean delete(Integer idSolicitud){
        return solicitudes.remove(idSolicitud) != null;
    }
}
