package com.renthouse.renthouse;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.json.Jackson2CodecSupport;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

@SpringBootApplication
public class RenthouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenthouseApplication.class, args);
	}

	// todas as mensagens que chegarem no padrão json
	// converter para objeto.
//	@Bean
//	public MessageConverter jsonMessageConverter() {
//		final ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		mapper.registerModule(new JavaTimeModule());
//		return new Jackson2JsonMessageConverter(mapper);
//	}

}
