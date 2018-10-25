package my.real.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "my.real.test")
public class AppMVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        //Add JSP to view resolver registry
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        //will enable auto detecting the default servlet for the container
        configurer.enable();
    }
}
