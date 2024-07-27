package com.habsida.morago.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {
    private final ModelMapper modelMapper;

    public ModelMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public <D, T> void map(final T source, D destination) {
        modelMapper.map(source, destination);
    }
}
