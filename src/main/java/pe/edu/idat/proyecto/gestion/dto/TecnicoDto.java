package pe.edu.idat.proyecto.gestion.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TecnicoDto
{

    @NotBlank(message = "El código del técnico es obligatorio")
    @Pattern(regexp = "TEC-\\d{3}",
            message = "El código debe tener el formato TEC-001")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 50, message = "Los apellidos deben tener entre 2 y 50 caracteres")
    private String apellidos;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(min = 2, max = 50, message = "La especialidad debe tener entre 2 y 50 caracteres")
    private String especialidad;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 65, message = "La edad máxima es 65 años")
    private int edad;
}