package com.sx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class UrenregApplication extends WebMvcConfigurerAdapter{

//	@Override
//	public void addFormatters(FormatterRegistry registry){
//		registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(UrenregApplication.class, args);
	}
}
