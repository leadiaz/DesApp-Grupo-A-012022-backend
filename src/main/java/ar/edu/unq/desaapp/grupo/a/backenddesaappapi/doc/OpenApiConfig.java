package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info =
        @Info(
                title = "Crypto P2P",
                description = "Proyecto Backend del grupo A-012020 de la materia Desarrollo de Aplicaciones",
                version = "v0.2.0"))
@SecurityScheme(name = "bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}

