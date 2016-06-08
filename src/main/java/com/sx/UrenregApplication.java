package com.sx;

import com.sx.formatters.DateWithTimeAnnotationFormatterFactory;
import com.sx.formatters.DateWithoutTimeAnnotationFormatterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class UrenregApplication extends WebMvcConfigurerAdapter {

    /*
    * add annotated formatters to the application registry:
    * */

	@Override
	public void addFormatters(FormatterRegistry registry){
		super.addFormatters(registry);
		registry.addFormatterForFieldAnnotation(new DateWithoutTimeAnnotationFormatterFactory());
		registry.addFormatterForFieldAnnotation(new DateWithTimeAnnotationFormatterFactory());
	}


	public static void main(String[] args) {
		SpringApplication.run(UrenregApplication.class, args);
	}
}
