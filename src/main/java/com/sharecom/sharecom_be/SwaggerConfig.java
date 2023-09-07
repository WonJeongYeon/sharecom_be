package com.sharecom.sharecom_be;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@OpenAPIDefinition(
        info = @Info(title = "Sinor_ASAC API 명세서",
                description = "시놀 랜덤채팅 앱 API 명세서입니다.",
                version = "v1"),
        servers = {
                @Server(url = "/", description = "Server URL")
        })
@RequiredArgsConstructor
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("api-v1")
                .pathsToMatch(paths)
                .build();
    }

    private static final String REFERENCE = "Authorization 헤더 값";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(securityScheme()));
    }

    private SecurityContext securityContext() {
        return springfox.documentation.spi.service.contexts.SecurityContext.builder().securityReferences(defaultAuth()).operationSelector(operationContext -> true).build();
    }

    private List defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
        return List.of(new SecurityReference(REFERENCE, authorizationScopes));
    }

    private ApiKey securityScheme() {
        String targetHeader = "Authorization"; // 어떠한 헤더에 값을 대입할 것인가: Authorization 헤더
        return new ApiKey(REFERENCE, targetHeader, "header");
    }


    }

