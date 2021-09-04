package br.com.claucio.dev.todolist.config;


import br.com.claucio.dev.todolist.domain.task.Task;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.validation.Validator;

@Configuration
@Log4j2
public class AppConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Task.class);
        cors.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        log.info("Repository CORS SETUP... OK");
    }

    @Bean
    public Validator validator(){
     return new LocalValidatorFactoryBean();
    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener vrel) {
        Validator validator = validator();
        vrel.addValidator("beforeCreate", validator);
        vrel.addValidator("beforeSave", validator);
    }
}
