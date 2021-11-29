package com.play.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface FieldMatch {
    String ref();
    String message() default "Referenced field values do not match";

}
