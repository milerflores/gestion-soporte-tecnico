package pe.edu.idat.proyecto.gestion.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tecnico
{
    private Integer idTecnico;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private String email;
    private int edad;
    private Date fechaRegistro;

}