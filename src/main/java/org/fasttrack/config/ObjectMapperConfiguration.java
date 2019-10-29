package org.fasttrack.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperConfiguration {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }
}
