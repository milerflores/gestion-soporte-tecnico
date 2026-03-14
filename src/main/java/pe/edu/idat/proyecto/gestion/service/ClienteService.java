package pe.edu.idat.proyecto.gestion.service;

import org.springframework.stereotype.Service;
import pe.edu.idat.proyecto.gestion.dto.ClienteDto;
import pe.edu.idat.proyecto.gestion.model.Cliente;
import pe.edu.idat.proyecto.gestion.repository.ClienteRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService
{

    private final ClienteRepository clienteRepository;

    private ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente create(ClienteDto clienteDto)
    {
        Cliente cliente = Cliente.builder()
                .dni(clienteDto.getDni())
                .nombres(clienteDto.getNombres())
                .apellidoPaterno(clienteDto.getApellidoPaterno())
                .apellidoMaterno(clienteDto.getApellidoMaterno())
                .edad(clienteDto.getEdad())
                .fechaRegistro(new Date())
                .build();
        return clienteRepository.create(cliente);
    }

    public List<Cliente> list()
    {
        return clienteRepository.list();
    }

    public Optional<Cliente> searchById(Integer id){
        return clienteRepository.searchById(id);
    }

    public Cliente update(Integer idCliente, ClienteDto clienteDto)
    {
        Cliente cliente = Cliente.builder()
                .idCliente(idCliente)
                .dni(clienteDto.getDni())
                .nombres(clienteDto.getNombres())
                .apellidoPaterno(clienteDto.getApellidoPaterno())
                .apellidoMaterno(clienteDto.getApellidoMaterno())
                .edad(clienteDto.getEdad())
                .fechaRegistro(new Date())
                .build();

        return clienteRepository.update(idCliente, cliente).orElse(null);
    }

    public boolean delete(Integer idCliente)
    {
        return clienteRepository.delete(idCliente);
    }


}
