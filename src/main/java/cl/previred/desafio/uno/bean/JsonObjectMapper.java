package cl.previred.desafio.uno.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

public class JsonObjectMapper {
	
	@SuppressWarnings("deprecation")
	@Bean(name = "OBJECT_MAPPER_BEAN")
	public ObjectMapper jsonObjectMapper() {
	    return Jackson2ObjectMapperBuilder.json()
	            //.serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
	            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
	            .modules(new JSR310Module())
	            .build();
	}

}
