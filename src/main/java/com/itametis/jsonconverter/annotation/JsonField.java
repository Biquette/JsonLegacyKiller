package com.itametis.jsonconverter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a field must be used for the Json conversion.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonField {

    public enum UseType {
        ALL, READ, WRITE;
    }


    /**
     * The new name of the field. By default, the name of the field.
     *
     * @return the name of the field.
     */
    public String name() default "";


    /**
     * The name in the Json.
     *
     * @return the name in the json.
     */
    public String nameInJson() default "";


    /**
     * The type of use.
     * WRITE to Json.
     * READ to object.
     * By default, ALL. ALL = READ+WRITE.
     *
     * @return the type of use.
     */
    public UseType useType() default JsonField.UseType.ALL;

}
