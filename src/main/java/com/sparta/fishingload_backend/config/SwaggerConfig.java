package com.sparta.fishingload_backend.config;



import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER,
        name = "Authorization", description = "Auth Token"
)
@SecurityScheme(
        type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER,
        name = "Authorization_Refresh", description = "Auth Refresh Token"
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        List<SecurityRequirement> securityRequirementList = new ArrayList<>();
        securityRequirementList.add(new SecurityRequirement().addList("Authorization"));
        securityRequirementList.add(new SecurityRequirement().addList("Authorization_Refresh"));

        return new OpenAPI()
                .info(new Info().title("FishingLoad API")
                        .version("v0.0.1")
                        .description("FishingLoad 프로젝트 API 명세서입니다."))
                        .security(securityRequirementList);
    }
}
