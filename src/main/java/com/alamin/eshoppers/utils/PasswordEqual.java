package com.alamin.eshoppers.utils;
/*
 *  CREATED:  10/12/2024
 *  TIME   :  3:54 PM
 *  PROJECT:  eShoppers
 *  @AUTHOR:  Al Amin
 */

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordEqualValidator.class)
public @interface PasswordEqual {
    String message() default "The fields must match";
    String first();
    String second();
    Class<?>[] groups() default {} ;
    Class<? extends Payload> [] payload() default {} ;
}
