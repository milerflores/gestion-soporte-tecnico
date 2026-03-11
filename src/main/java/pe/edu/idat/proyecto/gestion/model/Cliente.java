package pe.edu.idat.proyecto.gestion.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente
{
    private Integer idCliente;
    private String dni;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaRegistro;
    private  int edad;

}
