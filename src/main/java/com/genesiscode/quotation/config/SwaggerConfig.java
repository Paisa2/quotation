package com.genesiscode.quotation.config;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.*;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final String PATH = "com.genesiscode.quotation";

    private static final String TITLE = "Genesis Code S.R.L.";
    private static final String DESCRIPTION = "System Quotation";
    private static final String VERSION = "1.0";
    private static final String TERM_SERVICE = "Term of Service";
    private static final String EMAIL = "genesiscode91@gmail.com";
    private static final String LICENSE = "Apache License Version 2.0";
    private static final String LICENSE_URL = "https://www.apache.org";

    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PATH))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ListVendorExtension<String> listVendorExtension = new ListVendorExtension<>("collaborators",
                List.of());

        return new ApiInfo(TITLE, DESCRIPTION, VERSION, TERM_SERVICE,
                new Contact("Genesis Code", "none", EMAIL),
                LICENSE, LICENSE_URL, Collections.singleton(listVendorExtension));
    }

}
