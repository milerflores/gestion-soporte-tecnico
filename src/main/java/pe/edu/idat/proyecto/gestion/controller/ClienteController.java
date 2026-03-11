package pe.edu.idat.proyecto.gestion.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.proyecto.gestion.dto.ClienteDto;
import pe.edu.idat.proyecto.gestion.model.Cliente;
import pe.edu.idat.proyecto.gestion.service.ClienteService;


import java.util.List;

@RequestMapping("/v1/clientes")
@RestController
@RequiredArgsConstructor
public class ClienteController
{
    private final ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Crear cliente",
            description = "Permite registrar clientes desde un entorno externo")
    public ResponseEntity<Cliente> create(@Valid @RequestBody ClienteDto clienteDto)
    {
        Cliente cliente = clienteService.create(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    @Operation(summary = "Listar clientes",
            description = "Retorna la lista de todos los clientes registrados")
    public ResponseEntity<List<Cliente>> list()
    {
        return ResponseEntity.ok(clienteService.list());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID",
            description = "Retorna un cliente específico según su ID")
    public ResponseEntity<Cliente> searchByID(@PathVariable Integer id){
        return clienteService.searchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente",
            description = "Actualiza los datos de un cliente existente según su ID")
    public ResponseEntity<Cliente> update(@PathVariable Integer id,
                                          @Valid @RequestBody ClienteDto clienteDto)
    {
        Cliente cliente = clienteService.update(id, clienteDto);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente",
            description = "Elimina un cliente existente según su ID")
    public ResponseEntity<String> delete(@PathVariable Integer id)
    {
        boolean eliminado = clienteService.delete(id);

        if(eliminado)
        {
            return ResponseEntity.ok("Cliente eliminado");
        }

        return ResponseEntity.notFound().build();
    }
}