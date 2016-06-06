package com.sx.formatters;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/*******************************************************************************************
 *
 * Formatter for formatting date input from template fields and MySQL queries (String output)
 * to java.util.Date object and back (format() and parse() using SimpleDateFormat instance)
 *
 * This formatter works with dates WITH time.
 *
 * Formatter applies to annotation @DateWithTime (Configured using
 * AnnotationFormatterFactory subclass and Registered to the application using @Override
 * addFormatters(FormatterRegistry)
 *
 * ---------------------------------------------------------------------------------------
 * Spring Documentation on Formatter: Convert from String to support the client postback
 * process, as well as back to String to support the view rendering process
 * TODO: Take care to ensure your Formatter implementation is thread-safe??
 * -------------------------------------------------------------------------------------------
 *********************************************************************************************/


public class DateWithTimeFormatter implements Formatter <Date> {

    @Override
    public Date parse(String source, Locale locale) throws ParseException {
        System.out.println("PARSING STRING WITH TIME -->"+source);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", locale).parse(source);
    }

    @Override
    public String print(Date source, Locale locale) {
        if (source == null){
            return null;
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("Formatting from Java.util.Date WITH TIME->"+source);
        return dateFormatter.format(source);
    }
}
