package com.habsida.morago.config;

import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.entity.Deposits;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Add custom mappings here
        modelMapper.addMappings(new PropertyMap<Deposits, DepositsDTO>() {
            @Override
            protected void configure() {
                // Explicitly map the PaymentStatus enum
                map().setStatus(source.getStatus());
            }
        });

        return modelMapper;
    }
}
