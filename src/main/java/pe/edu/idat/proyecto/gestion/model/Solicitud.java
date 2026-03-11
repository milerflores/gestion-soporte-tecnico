package pe.edu.idat.proyecto.gestion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Solicitud {
    private Integer idSolicitud;
    private String titulo;
    private String descripcion;
    private String estado;
    private String prioridad;
    private Integer idTecnico;
    private Integer idCliente;
    private Date fechaRegistro;
}
