package com.sx.formatters;


import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*************************************************************************************************
 * Factory that creates formatters to format values of fields annotated with a particular Annotation.
******************************************************************************************************/

public final class DateWithoutTimeAnnotationFormatterFactory implements AnnotationFormatterFactory<DateWithoutTime> {

    /*
    * Holds fieldTypes that can be annoptated with DateWithoutTime (Only java.util.Date)
    * */

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(Date.class);
        return set;
    }

    /*
    * Formatter extends both Printer and Parser
    * This method returns the corresponding Printer that works with the annotation @DateWithoutTime
    * */

    @Override
    public Printer<Date> getPrinter(DateWithoutTime annotation, Class<?> fieldType) {
        return new DateWithoutTimeFormatter();
    }

    /*
    * Formatter extends both Printer and Parser
    * This method returns the corresponding Parser that works with the annotation @DateWithoutTime
    * */

    @Override
    public Parser<Date> getParser(DateWithoutTime annotation, Class<?> fieldType) {
        return new DateWithoutTimeFormatter();
    }
}