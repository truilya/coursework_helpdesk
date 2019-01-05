package coursework_helpdesk.config;

import coursework_helpdesk.config.converters.DateConverter;
import coursework_helpdesk.config.converters.PriorityConverter;
import coursework_helpdesk.config.converters.StatusConverter;
import coursework_helpdesk.config.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan({"coursework_helpdesk.config","coursework_helpdesk.controllers"})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("language/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.getDefault()); // change this
        return localeResolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter((PriorityConverter)appContext.getBean("priorityConverter"));
        registry.addConverter((StatusConverter)appContext.getBean("statusConverter"));
        registry.addConverter((UserConverter)appContext.getBean("userConverter"));
        registry.addConverter((DateConverter)appContext.getBean("dateConverter"));
    }
}
