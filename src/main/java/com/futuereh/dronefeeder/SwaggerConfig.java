package com.futuereh.dronefeeder;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * postApi.
   */
  @Bean
  public Docket postsApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo())
        .select().apis(RequestHandlerSelectors.basePackage("com.futuereh.dronefeeder.controller"))
        .paths(PathSelectors.any()).build().useDefaultResponseMessages(false);
  }

  /**
   * apiInfo.
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Drone Feeder API")
        .description(
            "Drone Feeder API - exposes endpoints to manage a delivery system using drones")
        .contact(new Contact("Carlos Araújo", "https://medium.com/@stonefull.stm",
            "stonefull.stm@gmail.com"))
        .version("1.0").build();
  }

}
