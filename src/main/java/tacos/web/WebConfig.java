package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("Calling from web config");
        registry.addViewController("/").setViewName("home");
        registry.addStatusController("/status", HttpStatus.FORBIDDEN);
        registry.addRedirectViewController("/web","https://www.youtube.com");
    }
}
