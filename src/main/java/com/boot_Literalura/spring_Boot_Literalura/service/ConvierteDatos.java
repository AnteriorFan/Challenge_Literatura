package com.boot_Literalura.spring_Boot_Literalura.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ConvierteDatos implements IConvierteDatos{

    private ObjectMapper objectMaper = new ObjectMapper();


    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMaper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
    }
}
