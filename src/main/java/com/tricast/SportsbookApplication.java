package com.tricast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestAttribute;

import com.tricast.controllers.filters.AuthenticationFilter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SportsbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsbookApplication.class, args);
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).ignoredParameterTypes(RequestAttribute.class).select()
                .apis(RequestHandlerSelectors.basePackage("com.tricast.controllers"))
                .paths(PathSelectors.any()).build();
    }

    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, null,
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJha29zIiwiaXNzIjoiVHJpY2FzdFRhbmYyMDE4IiwidHlwIjoiUExBWUVSIiwiZXhwIjoxNTQyMTE1NDI0LCJhaWQiOjEwMH0.2OOF3brpeJ0SCO-WeKRVJWYPoq4TU0w6CPlQdTD8DBM",
                ApiKeyVehicle.HEADER, "Authorization",
                ",");
    }

    @Bean
    public FilterRegistrationBean authenticationFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
}
