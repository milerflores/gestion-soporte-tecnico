package pe.edu.idat.proyecto.gestion.repository;

import org.springframework.stereotype.Repository;
import pe.edu.idat.proyecto.gestion.model.Cliente;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ClienteRepository
{
    private final Map<Integer, Cliente> clientes = new HashMap<>();

    private final AtomicInteger secuencialCliente = new AtomicInteger();

    public Cliente create(Cliente cliente)
    {
        if(cliente.getIdCliente() == null)
        {
            cliente.setIdCliente(secuencialCliente.getAndIncrement());
        }

        clientes.put(cliente.getIdCliente(), cliente);
        return cliente;
    }

    public List<Cliente> list()
    {
        return new ArrayList<>(clientes.values());
    }

    public Optional<Cliente> searchById(Integer idCliente)
    {
        return Optional.ofNullable(clientes.get(idCliente));
    }
    public Optional<Cliente> update(Integer idCliente, Cliente cliente)
    {
        if(clientes.containsKey(idCliente))
        {
            cliente.setIdCliente(idCliente);
            clientes.put(idCliente, cliente);
            return Optional.of(cliente);
        }
        return Optional.empty();
    }

    public boolean delete(Integer idCliente)
    {
        return clientes.remove(idCliente) != null;
    }

}
