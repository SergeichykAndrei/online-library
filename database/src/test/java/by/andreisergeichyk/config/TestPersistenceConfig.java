package by.andreisergeichyk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.andreisergeichyk.util")
@Import(PersistenceConfig.class)
public class TestPersistenceConfig {
}
