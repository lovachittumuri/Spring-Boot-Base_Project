package com.sh.web.template.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JSONUtil<T, R> {
	public String JSONSerialize(T object, File filePath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(filePath, object);
		System.out.println("Serialization completed");
		String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		return response;
	}

	public R JSONDesrialize(File filePath, Class<R> responseType)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		R r = mapper.readValue(filePath, responseType);
		return r;
	}

	public String convertObjectToJsonString(T object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(object);
		return jsonString;
	}

	public R convertJsonStringObject(String jsonString, Class<R> responseType)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		R object = mapper.readValue(jsonString, responseType);
		return object;
	}
}
