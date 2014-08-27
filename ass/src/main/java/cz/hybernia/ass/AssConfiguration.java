package cz.hybernia.ass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "cz.hybernia.ass")
@PropertySource({
                 "classpath:queries/clients.xml",
                 "classpath:queries/users.xml"
                })
public class AssConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(AssConfiguration.class, args);
    }
}
