package coursework_helpdesk.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {

        Class<?>[] configClasses = new Class[2];
        configClasses[0]=WebConfig.class;
        configClasses[1]=SecurityConfig.class;
        return configClasses;// new Class<?>[]{SecurityConfig.class};//WebConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;//new Class<?>[0];
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
