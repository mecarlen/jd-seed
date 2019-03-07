package com.jd.seed;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
	 * springMVC
	 * 
	 * </pre>
	 */
	@Configuration
	public class SpringMvcConfiguration implements WebMvcConfigurer {
		/**
		 * <pre>
		 * 添加拦截器
		 * 
		 * </pre>
		 */
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new AuthenticationVerifyInterceptor()).addPathPatterns("/*")
					.excludePathPatterns(new String[] { "/adminlte/*","/bootstrap/*","/font-awesome/*","/jquery/*","/login" });
		}

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("forward:/index.html");
			registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		}

	}
}
