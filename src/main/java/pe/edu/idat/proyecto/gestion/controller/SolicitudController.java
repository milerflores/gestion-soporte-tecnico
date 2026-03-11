package pe.edu.idat.proyecto.gestion.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.proyecto.gestion.dto.SolicitudDto;
import pe.edu.idat.proyecto.gestion.model.Solicitud;
import pe.edu.idat.proyecto.gestion.service.SolicitudService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/v1/solicitudes")
@RestController
@RequiredArgsConstructor
public class SolicitudController {

    private  final SolicitudService solicitudService;

    @PostMapping
    @Operation(summary = "Crear solicitud", description = "Permite registrar solicitudes desde un entorno externo")
    public ResponseEntity<Solicitud> create(@Valid @RequestBody SolicitudDto solicitudDto){
        Solicitud solicitud = solicitudService.create(solicitudDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(solicitud);
    }

    @GetMapping
    @Operation(summary = "Listar solicitudes",
            description = "Retorna la lista de todas las solicitudes registradas")
    public ResponseEntity<List<Solicitud>> list(){
        return ResponseEntity.ok(solicitudService.list());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar solicitud por ID",
            description = "Retorna una solicitud específica según su ID")
    public ResponseEntity<Solicitud> searchById(@PathVariable Integer id){
        return solicitudService.searchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar solicitud",
            description = "Actualiza los datos de una solicitud existente según su ID")
    public ResponseEntity<Solicitud> update(@PathVariable Integer id, @Valid @RequestBody SolicitudDto solicitudDto){
        Solicitud solicitud = solicitudService.update(id, solicitudDto);

        if(solicitud == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(solicitud);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud",
            description = "Elimina una solicitud existente según su ID")
    public ResponseEntity<?>  delete(@PathVariable Integer id){
        boolean eliminado = solicitudService.delete(id);

        if(eliminado){
            return ResponseEntity.ok("Solicitud eliminada");
        }

        return ResponseEntity.notFound().build();
    }
}