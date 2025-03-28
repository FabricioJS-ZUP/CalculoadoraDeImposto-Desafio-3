package br.com.catalisa.zup.Tax.Calculator.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Calculador de Impostos",
                version = "V2 - em desenvolvimento",
                contact = @Contact(
                        name = "Fabrício",
                        email = "fabricio.sullivan@zup.com.br"
                ),
                description = "Este projeto foi do curso de formação do Catalisa (ZUP) [Imagem da logo da ZUP](https://zup.com.br/wp-content/uploads/2021/03/zup-logo-1-e1626185300293.jpg)" // Adicione o link aqui
        ),
        security = {
                @SecurityRequirement(name = "bearerAuth")
        })
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {

}
