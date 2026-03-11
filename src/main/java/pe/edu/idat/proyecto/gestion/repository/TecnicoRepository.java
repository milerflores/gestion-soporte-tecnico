package pe.edu.idat.proyecto.gestion.repository;

import org.springframework.stereotype.Repository;
import pe.edu.idat.proyecto.gestion.model.Tecnico;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TecnicoRepository
{
    private final Map<Integer, Tecnico> tecnicos = new HashMap<>();
    private final AtomicInteger secuencialTecnico = new AtomicInteger();

    public Tecnico create(Tecnico tecnico)
    {
        if(tecnico.getIdTecnico() == null)
        {
            tecnico.setIdTecnico(secuencialTecnico.getAndIncrement());
        }
        tecnicos.put(tecnico.getIdTecnico(), tecnico);
        return tecnico;
    }

    public List<Tecnico> list()
    {
        return new ArrayList<>(tecnicos.values());
    }

    public Optional<Tecnico> searchById(Integer idTecnico)
    {
        return Optional.ofNullable(tecnicos.get(idTecnico));
    }

    public Optional<Tecnico> update(Integer idTecnico, Tecnico tecnico)
    {
        if(tecnicos.containsKey(idTecnico))
        {
            tecnico.setIdTecnico(idTecnico);
            tecnicos.put(idTecnico, tecnico);
            return Optional.of(tecnico);
        }
        return Optional.empty();
    }

    public boolean delete(Integer idTecnico)
    {
        return tecnicos.remove(idTecnico) != null;
    }

}