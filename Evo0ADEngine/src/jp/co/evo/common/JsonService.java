package jp.co.evo.common;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonService {
	private static JsonService instance = null;

	private JsonNode latestResponse = null;

	private JsonService() {
	}

	public static JsonService factory() {
		if (Objects.isNull(instance))
			instance = new JsonService();
		return instance;
	}
	
	public void read(String jsonString) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		this.latestResponse = mapper.readTree(jsonString);
	}

	public JsonNode getLatestResponse() {
		return latestResponse;
	}

	public void setLatestResponse(JsonNode latestResponse) {
		this.latestResponse = latestResponse;
	}

}
