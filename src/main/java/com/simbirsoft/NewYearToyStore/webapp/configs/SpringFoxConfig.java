package com.simbirsoft.NewYearToyStore.webapp.configs;

import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



//@Configuration
//@EnableSwagger2
//public class SpringFoxConfig {
//    @Value("${swagger.host.url}")
//    private String hostUrl;
//
////    @Bean
////    public Docket postApi() {
////        return  new Docket(DocumentationType.SWAGGER_2)
////                .groupName("custom-api")
////                .apiInfo(ApiInfo.DEFAULT)
////                .select()
////                .paths()
////    }
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("custom-api")
//                .host(hostUrl)
//                .select()
//                //.apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.simbirsoft.NewYearToyStore.controllers"))
//                .paths(PathSelectors.any())
//                //.paths(regex("/api/customers.*"))
//                .build();
//    }

//    private ApiInfo metaData() {
//        ApiInfo apiInfo = new ApiInfo(
//                "Spring Boot REST API",
//                "Spring Boot REST API for Online New Year Toy Store",
//                "1.0",
//                "Terms of service",
//                ("Dmitry Solomakhin, dmitrii.solomakhin@simbirsoft.com"),
//                "Apache License Version 2.0",
//                "https://www.apache.org/licenses/LICENSE-2.0");
//        return apiInfo;
//
//    }
//}


