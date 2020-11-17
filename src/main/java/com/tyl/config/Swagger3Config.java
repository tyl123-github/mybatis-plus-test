package com.tyl.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Config {

    @Bean

    public Docket createRestApi() {

        return new Docket(DocumentationType.OAS_30)

                .apiInfo(apiInfo())

                .select()

                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))

                .paths(PathSelectors.any())

                .build();

    }


    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()

                .title("Swagger3接口文档测试")

                .description("共同探讨技术")

                .contact(new Contact("乱世中的安详", "杭州市", "1933382096@qq.com"))

                .version("1.0")

                .build();

    }

}