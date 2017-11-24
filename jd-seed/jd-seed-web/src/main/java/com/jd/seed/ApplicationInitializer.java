package com.jd.seed;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <pre>
 * 应用初始化加载
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月21日 下午8:57:26
 */
@EnableAutoConfiguration
@SpringBootApplication
@ImportResource(locations = { "classpath:applicationContext.xml" })
public class ApplicationInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ApplicationInitializer.class);
	}

	/**
	 * <pre>
	 * swagger资源加入spring boot
	 * 
	 * </pre>
	 */
	@Configuration
	static class SwaggerStaticResourcesConfiguration extends WebMvcConfigurerAdapter {

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			// super.addResourceHandlers(registry);
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		}
	}

	/**
	 * <pre>
	 * REST API doc
	 * 
	 * </pre>
	 */
	@Configuration
	@EnableSwagger2
	@Profile("dev")
	static class RestAPIDoc {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
					.apis(RequestHandlerSelectors.basePackage("com.jd.seed.dictionary.city.web"))
					.paths(PathSelectors.any()).build();

		}

		private ApiInfo apiInfo() {
			return new ApiInfoBuilder().title("REST API").description("种子项目").version("1.0")
					.termsOfServiceUrl("http://seed.jd.com").license("LICENSE")
					.contact(new Contact("wangfenghua3", "http://seed.jd.com", "wangfenghua3@jd.com")).build();
		}
	}

}