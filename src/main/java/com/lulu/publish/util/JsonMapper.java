package com.lulu.publish.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.getSerializationConfig().set(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);
        objectMapper.getDeserializationConfig().set(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, true);
        objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    public static <T> T fromJson(String jsonAsString, Class<T> pojoClass) throws JsonMapperException {
        try {
            return objectMapper.readValue(jsonAsString, pojoClass);
        } catch (IOException e) {
            throw new JsonMapperException(e.getMessage(), e);
        }
    }

    public static String toJson(Object pojo) throws JsonMapperException {
        try {
            return objectMapper.writeValueAsString(pojo);
        } catch (IOException e) {
            throw new JsonMapperException(e.getMessage(), e);
        }
    }
}