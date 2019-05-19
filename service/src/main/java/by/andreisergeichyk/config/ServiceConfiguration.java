package by.andreisergeichyk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.andreisergeichyk")
@Import(PersistenceConfig.class)
@EnableAspectJAutoProxy
public class ServiceConfiguration {
}
