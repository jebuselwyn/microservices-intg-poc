package com.microservices.intg.poc.messageproducer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is a util configuration that exposes all endpoints in a nice manner. The
 * operations allowed, expected format of model are all exposed.
 * <p>
 * The end point is :
 * <code>http://&lt;hostname&gt;:&lt;port&gt;/&lt;context&gt;/swagger-ui.html</code>
 * <p>
 * This is more of a Util for development and may be disabled for Prod and
 * higher envs.
 *
 * @author Jebuselwyn.Martin
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ConditionalOnProperty(value = "enable-swagger", havingValue = "true")
public class SwaggerConfiguration extends WebMvcConfigurerAdapter implements EnvironmentAware {

    /**
     * The property resolver.
     */
    private Environment environment;

    /**
     * Instantiates a new swagger configuration.
     */
    public SwaggerConfiguration() {
        super();
    }

    /**
     * Include Environment properties to set into this. mapped with swagger as
     * the starting key.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * The api builder. This scans all RestControllers to generate the api
     * docket
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*")).build().apiInfo(apiInfo());
    }

    /**
     * API Info as it appears on the swagger-ui page. Customize this to include
     * more information about the application.
     *
     * @return the api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(environment.getProperty("title"))
                .description(environment.getProperty("description")).version("0.0.2-SNAPSHOT")
                .termsOfServiceUrl(environment.getProperty("termsOfServiceUrl"))
                .license(environment.getProperty("license")).licenseUrl(environment.getProperty("licenseUrl"))
                .build();
    }
    /**
     * Adding Resource Handler for mapping swagger endpoints. IMPORTANT: This
     * also enables the login.html page for individual modules to login
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/login.html*").addResourceLocations("classpath:/web/");
    }

}
