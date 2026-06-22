package com.lcsdl.ead.enrollment.configs;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class DateConfig {

	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

	    builder.serializers(
	        new LocalDateTimeSerializer(
	            DateTimeFormatter.ofPattern(DATETIME_FORMAT)
	        )
	    );

	    return builder;
	}
}
