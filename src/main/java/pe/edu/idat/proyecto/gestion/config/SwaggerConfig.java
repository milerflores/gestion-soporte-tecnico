package pe.edu.idat.proyecto.gestion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{
    @Bean
    public OpenAPI soporteApiConfig()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Soporte Técnico")
                        .description("API RESTful para registrar, consultar, actualizar y eliminar solicitudes de soporte técnico, clientes y técnicos")
                        .version("1.0")
                        .contact( new Contact()
                                .name("Grupo 02")
                                .email("alumno2@idat.pe")
                                .url("http://eva.edu.pe/proyectos")
                        )
                        .license( new License()
                                .name("Proyectos IDAT")
                                .url("http://eva.edu.pe/proyectos-idat")
                ));
    }

}
