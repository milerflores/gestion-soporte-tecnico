package pe.edu.idat.proyecto.gestion.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SolicitudDto {

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "PENDIENTE|EN_PROCESO|RESUELTO",
            message = "El estado debe ser: PENDIENTE, EN_PROCESO o RESUELTO")
    private String estado;

    @NotBlank(message = "La prioridad es obligatoria")
    @Pattern(regexp = "BAJA|MEDIA|ALTA",
            message = "La prioridad debe ser: BAJA, MEDIA o ALTA")
    private String prioridad;

    @NotNull(message = "El ID del técnico es obligatorio")
    @PositiveOrZero(message = "El ID del técnico debe ser un número positivo")
    private Integer idTecnico;

    @NotNull(message = "El ID del cliente es obligatorio")
    @PositiveOrZero(message = "El ID del cliente debe ser un número positivo")
    private Integer idCliente;
}
