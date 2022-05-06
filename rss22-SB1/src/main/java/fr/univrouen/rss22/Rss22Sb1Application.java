package fr.univrouen.rss22;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EntityScan(basePackages = {"fr.univrouen.rss22.entities"})  // scan JPA entities
@SpringBootApplication
public class Rss22Sb1Application implements WebMvcConfigurer {
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/"};

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations(
					"classpath:/META-INF/resources/webjars/");
		}
		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(
					CLASSPATH_RESOURCE_LOCATIONS);
		}
	}

	public static void main(String[] args) {
		//SpringApplication.run(Rss22Sb1Application.class, args);
		try {
			SpringApplication.run(Rss22Sb1Application.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("springshop-public")
				.pathsToMatch("/public/**")
				.packagesToScan("fr.univrouen.rss22.controllers")
				.build();
	}


}
