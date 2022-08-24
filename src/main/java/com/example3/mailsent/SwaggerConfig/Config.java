package com.example3.mailsent.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class Config {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example"))
                    .paths(PathSelectors.any())
                    .build().apiInfo(apiInfo());
        }

        public ApiInfo apiInfo() {
            return new ApiInfoBuilder().title("Email Box")
                    .description("Restfull web documentation using swagger")
                    .termsOfServiceUrl("https://www.facebook.com")
                    .license("java license")
                    .licenseUrl("https://www.facebook.com")
                    .contact(new Contact("Ayasa","www.ayasa.com","ayasa@gmail.com"))
                    .version("3.0")
                    .build();


        }

    }

