package pe.edu.idat.proyecto.gestion.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.proyecto.gestion.dto.TecnicoDto;
import pe.edu.idat.proyecto.gestion.model.Tecnico;
import pe.edu.idat.proyecto.gestion.service.TecnicoService;

import java.util.List;

@RequestMapping("/v1/tecnicos")
@RestController
@RequiredArgsConstructor
public class TecnicoController
{
    private final TecnicoService tecnicoService;

    @PostMapping
    @Operation(summary = "Crear tecnico",
            description = "Permite registrar tecnicos desde un entorno externo")
    public ResponseEntity<Tecnico> create(@Valid @RequestBody TecnicoDto tecnicoDto)
    {
        Tecnico tecnico = tecnicoService.create(tecnicoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnico);
    }

    @GetMapping
    @Operation(summary = "Listar tecnicos",
            description = "Retorna la lista de todos los tecnicos registrados")
    public ResponseEntity<List<Tecnico>> list()
    {
        return ResponseEntity.ok(tecnicoService.list());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tecnico por ID",
            description = "Retorna un tecnico específico según su ID")
    public ResponseEntity<Tecnico> searchById(@PathVariable Integer id){
        return tecnicoService.searchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tecnico",
            description = "Actualiza los datos de un tecnico existente según su ID")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @Valid @RequestBody TecnicoDto tecnicoDto)
    {
        Tecnico tecnico = tecnicoService.update(id, tecnicoDto);

        if(tecnico == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tecnico);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tecnico",
            description = "Elimina un tecnico existente según su ID")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        boolean eliminado = tecnicoService.delete(id);

        if(eliminado)
        {
            return ResponseEntity.ok("Tecnico eliminado");
        }

        return ResponseEntity.notFound().build();
    }
}