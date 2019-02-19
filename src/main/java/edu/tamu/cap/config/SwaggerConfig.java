package edu.tamu.cap.config;

import com.google.common.base.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
  
  private static final String USER = "/user/**";
  private static final String REPOSITORY_VIEW_TYPE_VERIFY = "/repository-view/{type}/verify/**";
  private static final String REPOSITORY_VIEW = "/repository-view/**";
  private static final String SCHEMA = "/schema/**";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SPRING_WEB).select().apis(RequestHandlerSelectors.any()).paths(paths()).build();

  }

  private Predicate<String> paths() {
    return or(
        PathSelectors.ant(REPOSITORY_VIEW), 
        PathSelectors.ant(SCHEMA),
        PathSelectors.ant(REPOSITORY_VIEW_TYPE_VERIFY), 
        PathSelectors.ant(USER)
      );
    }

}