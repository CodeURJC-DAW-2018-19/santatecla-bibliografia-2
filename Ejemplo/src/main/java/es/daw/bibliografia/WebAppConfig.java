package es.daw.bibliografia;

import org.springframework.context.annotation.Configuration;

@EnableWebMvc
@Configuration
@ComponentScan("carmelo.spring.controller")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize((1024 * 1024) * 10);
        return multipartResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
}