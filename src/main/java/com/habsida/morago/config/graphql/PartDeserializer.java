package com.habsida.morago.config.graphql;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.multipart.Part;

@JsonComponent
public class PartDeserializer extends JsonDeserializer<Part> {
    @Override
    public Part deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return null;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Part.class, new PartDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}


