package com.github.tomplum.activity.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

//@EnableWebMvc
@Configuration
@EnableSwagger2
//@Import(SpringDataRestConfiguration::class)
open class WebConfig : WebMvcConfigurer {
    /**
     * Enables CORS across all the API endpoints, allowing the UI to make fetch()
     * requests to the API. Without, Cross Origin errors are thrown in the browser.
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins("*")
        registry.addMapping("/")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    @Bean
    open fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInfo())
    }

    private fun getApiInfo() = ApiInfoBuilder()
        .title("Activity Trends API")
        .description("ETL data translation later for health and sleep data.")
        .contact(Contact("Thomas Plumpton", "https://tomplumpton.me", "Thomas.Plumpton@Hotmail.co.uk"))
        .license("Apache 2.0")
        .version("1.0.0")
        .build()
}