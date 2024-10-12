package com.alamin.eshoppers.utils;
/*
 *  CREATED:  10/12/2024
 *  TIME   :  3:13 PM
 *  PROJECT:  eShoppers
 *  @AUTHOR:  Al Amin
 */

import com.alamin.eshoppers.dto.UserDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationUtil {
    private static final ValidationUtil INSTANCE = new ValidationUtil();

    private final Validator validator ;

    public ValidationUtil() {
        var validationFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validationFactory.getValidator() ;
    }

    public static ValidationUtil getInstance() {
        return INSTANCE ;
    }

    public <T> Map<String, String> validate(T object) {
        var violations = validator.validate(object);
        return violations.stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,
                        (e1, e2) -> e1 + "<br/>" + e2
                ));
    }
}
