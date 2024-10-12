package com.alamin.eshoppers.utils;
/*
 *  CREATED:  10/12/2024
 *  TIME   :  4:03 PM
 *  PROJECT:  eShoppers
 *  @AUTHOR:  Al Amin
 */

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.Objects;

public class PasswordEqualValidator implements ConstraintValidator<PasswordEqual, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true ;
        try {

                final Object firstObj = getValue(value, firstFieldName);
                final  Object secondObj = getValue(value, secondFieldName);
                valid = Objects.equals(firstObj, secondObj);

        } catch (final  Exception ignore) {
            //ignore
        }


        if (! valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid ;
    }

    private Object getValue(Object object, String firstFieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(firstFieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
