package pe.edu.idat.proyecto.gestion.service;

import org.springframework.stereotype.Service;
import pe.edu.idat.proyecto.gestion.dto.TecnicoDto;
import pe.edu.idat.proyecto.gestion.model.Tecnico;
import pe.edu.idat.proyecto.gestion.repository.TecnicoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class TecnicoService
{
    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository)
    {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico create(TecnicoDto tecnicoDto)
    {
        Tecnico tecnico = Tecnico.builder()
                .codigo(tecnicoDto.getCodigo())
                .nombres(tecnicoDto.getNombres())
                .apellidos(tecnicoDto.getApellidos())
                .especialidad(tecnicoDto.getEspecialidad())
                .email(tecnicoDto.getEmail())
                .edad(tecnicoDto.getEdad())
                .fechaRegistro(new Date())
                .build();

        return tecnicoRepository.create(tecnico);
    }

    public List<Tecnico> list()
    {
        return tecnicoRepository.list();
    }

    public Optional<Tecnico> searchById(Integer id){
        return tecnicoRepository.searchById(id);
    }

    public Tecnico update(Integer idTecnico, TecnicoDto tecnicoDto)
    {
        Tecnico tecnico = Tecnico.builder()
                .idTecnico(idTecnico)
                .codigo(tecnicoDto.getCodigo())
                .nombres(tecnicoDto.getNombres())
                .apellidos(tecnicoDto.getApellidos())
                .especialidad(tecnicoDto.getEspecialidad())
                .email(tecnicoDto.getEmail())
                .edad(tecnicoDto.getEdad())
                .fechaRegistro(new Date())
                .build();

        return tecnicoRepository.update(idTecnico, tecnico).orElse(null);
    }

    public boolean delete(Integer idTecnico)
    {
        return tecnicoRepository.delete(idTecnico);
    }


}