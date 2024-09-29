package be.kdg.prog6.adapter.in;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
