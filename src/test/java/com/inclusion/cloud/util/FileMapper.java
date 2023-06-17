package com.inclusion.cloud.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FileMapper<T> {

    public T loadTestJson(String fileName, Class<T> model) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        return new ObjectMapper().readValue(inputStream, model);
    }

    public String loadTestJsonToString(String fileName) throws IOException {
        return new String(getClass().getClassLoader().getResourceAsStream(fileName).readAllBytes());
    }
    public T getObjectFromString(String body, Class<T> model) throws IOException {
        return new ObjectMapper().readValue(body, model);
    }
}
