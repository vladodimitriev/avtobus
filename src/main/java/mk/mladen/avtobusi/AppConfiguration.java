package mk.mladen.avtobusi;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

@Import(DbConfig.class)
@Configuration
@ComponentScan("mk.mladen.avtobusi")
public class AppConfiguration {
	
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mk/mladen/avtobusi/WicketApplication");
        return messageSource;
    }
	
}
