package com.jd.seed;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.annotations.ApiOperation;
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
@SpringBootApplication
@ImportResource(locations = { "classpath:applicationContext.xml" })
public class ApplicationInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ApplicationInitializer.class);
	}

	// public static void main(String[] args) throws Exception {
	// SpringApplication.run(ApplicationInitializer.class, args);
	// }

	/**
	 * <pre>
	 * swagger资源加入spring boot
	 * 
	 * </pre>
	 */
	@Configuration
	@EnableSwagger2
	static class SwaggerStaticResourcesConfiguration implements WebMvcConfigurer {

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {

			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new PageInterceptor()).addPathPatterns("/**/page/**");
		}

	}

	/**
	 * <pre>
	 * REST API doc
	 * 
	 * 描述
	 * API DOC支持的环境，dev-开发，test-测试,pre-预发
	 * 
	 * </pre>
	 */
	@Configuration
	@EnableSwagger2
	@Profile({ "dev", "test", "pre" })
	static class RestAPIDoc {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
					.apis(RequestHandlerSelectors.basePackage("com.jd.seed"))
					.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
					.build();

		}

		private ApiInfo apiInfo() {
			return new ApiInfoBuilder().title("REST API").description("种子项目").version("1.0")
					.termsOfServiceUrl("http://seed.jd.com").license("LICENSE")
					.contact(new Contact("wangfenghua3", "http://seed.jd.com", "wangfenghua3@jd.com")).build();
		}
	}

}
