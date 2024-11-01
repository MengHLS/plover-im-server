package com.ruoyi.common.swagger.config;

import java.util.Arrays;
import java.util.List;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
@Import({ SwaggerWebConfiguration.class})
public class SwaggerAutoConfiguration
{

    @Autowired
    SwaggerProperties swaggerProperties;
    /**
     * 默认的排除路径，排除Spring Boot默认的错误处理路径和端点
     */
    private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error", "/actuator/**");

    private static final String BASE_PATH = "/**";

    @Bean
    public OpenAPI apiInfo() {

        OpenAPI openAPI = new OpenAPI().info(new Info().title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .version(swaggerProperties.getVersion())
                        .license(new License().url(swaggerProperties.getLicenseUrl()).name(swaggerProperties.getLicense()))
                        .contact(new Contact().name(swaggerProperties.getContact().getName())
                                .url(swaggerProperties.getContact().getUrl())
                                .email(swaggerProperties.getContact().getEmail())))
                .components(new Components().addSecuritySchemes("Authorization",
                        new SecurityScheme().name("Authorization").in(SecurityScheme.In.HEADER).type(SecurityScheme.Type.OAUTH2)
                ))
                .externalDocs(new ExternalDocumentation().description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));

        return openAPI;
    }

}
