package pe.edu.idat.proyecto.gestion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.proyecto.gestion.dto.ClienteDto;
import pe.edu.idat.proyecto.gestion.dto.SolicitudDto;
import pe.edu.idat.proyecto.gestion.model.Cliente;
import pe.edu.idat.proyecto.gestion.model.Solicitud;
import pe.edu.idat.proyecto.gestion.repository.ClienteRepository;
import pe.edu.idat.proyecto.gestion.repository.SolicitudRepository;
import pe.edu.idat.proyecto.gestion.repository.TecnicoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {
    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;

    public SolicitudService(SolicitudRepository solicitudRepository, ClienteRepository clienteRepository, TecnicoRepository tecnicoRepository){
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    public Solicitud create(SolicitudDto solicitudDto){
        clienteRepository.searchById(solicitudDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        tecnicoRepository.searchById(solicitudDto.getIdTecnico())
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

        Solicitud solicitud = Solicitud.builder()
                .titulo(solicitudDto.getTitulo())
                .descripcion(solicitudDto.getDescripcion())
                .estado(solicitudDto.getEstado())
                .prioridad(solicitudDto.getPrioridad())
                .idTecnico(solicitudDto.getIdTecnico())
                .idCliente(solicitudDto.getIdCliente())
                .fechaRegistro(new Date())
                .build();
        return solicitudRepository.create(solicitud);
    }

    public List<Solicitud> list(){
        return solicitudRepository.list();
    }

    public Optional<Solicitud> searchById(Integer id){
        return solicitudRepository.searchById(id);
    }


    public Solicitud update(Integer idSolicitud, SolicitudDto solicitudDto){
                Solicitud solicitud = Solicitud.builder()
                        .idSolicitud(idSolicitud)
                        .titulo(solicitudDto.getTitulo())
                        .descripcion(solicitudDto.getDescripcion())
                        .estado(solicitudDto.getEstado())
                        .prioridad(solicitudDto.getPrioridad())
                        .idTecnico(solicitudDto.getIdTecnico())
                        .idCliente(solicitudDto.getIdCliente())
                        .fechaRegistro(new Date())
                        .build();

               return  solicitudRepository.update(idSolicitud, solicitud).orElse(null);
    }


    public boolean delete(Integer idSolicitud){
        return solicitudRepository.delete(idSolicitud);
    }


}
