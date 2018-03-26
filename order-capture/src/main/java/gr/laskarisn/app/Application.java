package gr.laskarisn.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@EnableTransactionManagement
@EntityScan(value = "gr.laskarisn.entities")
@EnableJpaRepositories(basePackages = {"gr.laskarisn.entities", "gr.laskarisn.repositories"} )
@SpringBootApplication(scanBasePackages={"gr.laskarisn.entities", "gr.laskarisn.app", "gr.laskarisn.configuration", "gr.laskarisn.repositories", "gr.laskarisn.controllers"})
public class Application extends SpringBootServletInitializer{
	
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

	
	
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    
    }
    
    
    
    
}