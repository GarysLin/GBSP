package tw.com.core.spring;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("tw.com.gbshop")
public class MVCConfig implements WebMvcConfigurer {
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/public/")
				.addResourceLocations("classpath:/resources/");
		
		registry.addResourceHandler("/images/**")
				.addResourceLocations("/images/");
		
		registry.addResourceHandler("/js/**")
				.addResourceLocations("/js/");
		
		registry.addResourceHandler("/css/**")
				.addResourceLocations("/css/");
		
		registry.addResourceHandler("/fontawesome/**")
				.addResourceLocations("/fontawesome/");
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index.jsp");
		registry.addViewController("/login.html").setViewName("login");
	}

	@Bean
	public LocaleResolver localeResolver(){
		return new NativeLocaleResolver();
	}

	protected static class NativeLocaleResolver implements LocaleResolver{

		public Locale resolveLocale(HttpServletRequest request) {
			String language = request.getParameter("language");
			Locale locale = Locale.getDefault();
			if(!StringUtils.isEmpty(language)){
				String[] split = language.split("_");
				locale = new Locale(split[0],split[1]);
			}
			return locale;
		}

		public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
			
		}
	}
}
