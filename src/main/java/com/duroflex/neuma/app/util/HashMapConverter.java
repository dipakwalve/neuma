package com.duroflex.neuma.app.util;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@javax.persistence.Converter
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    Logger log = LoggerFactory.getLogger(HashMapConverter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> mapperInfo) {
        String mattresInfoJson = null;
        try {
            mattresInfoJson = objectMapper.writeValueAsString(mapperInfo);

        } catch (final JsonProcessingException e) {
            log.error("jSON WRITTING ERROR " + e);

        }
        return mattresInfoJson;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> convertToEntityAttribute(String mattressData) {

        Map<String, Object> mattressInfo = null;
        try {
            mattressInfo = objectMapper.readValue(mattressData, Map.class);
        } catch (IOException e) {
            log.error("Json Reading Error " + e);
        }
        return mattressInfo;
    }

    public void serializeFirmnessData() throws JsonProcessingException {

    }
}
