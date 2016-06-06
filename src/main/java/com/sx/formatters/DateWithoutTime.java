package com.sx.formatters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/************************************************************************
 * Create Annotation for formatting java.util.date WITHOUT time
 * Applies to Subscription.startDate
 *
 * The @Target annotation (ElementType.FIELD) defines this annotation can
 * be used as a modifier for a field declaration (SportSession.dateTime).
 *
 * The @Retention annotation (RetentionPolicy.RUNTIME) specifies this
 * annotation is to be recorded in the class file by the compiler and
 * retained by the VM at run time, so they may be read reflectively.
 *
 ************************************************************************/


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateWithoutTime {

}

