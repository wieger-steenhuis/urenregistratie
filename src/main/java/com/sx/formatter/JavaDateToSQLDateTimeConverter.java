package com.sx.formatter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mrtn on 30-5-2016.
 */
public final class JavaDateToSQLDateTimeConverter implements Converter<Date, String> {

    private final SimpleDateFormat simpleDateFormat;

    public JavaDateToSQLDateTimeConverter(String dateFormat){
        this.simpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    @Override
    public String convert(Date source) {

        return simpleDateFormat.format(source);

    }
}
