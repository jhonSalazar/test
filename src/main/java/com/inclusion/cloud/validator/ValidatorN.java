package com.inclusion.cloud.validator;

import java.util.List;

import static com.inclusion.cloud.constants.Constants.MAX_X_VALUE;

public class ValidatorN extends ValidatorInput {
    @Override
    void validate(Long x,Long y, Long n, List<String> errors){
        //n
        if (n < y) {
            errors.add("n parameter must be greater than or equal to Y");
        }
        if (n > MAX_X_VALUE) {
            errors.add("x parameter must be less than or equal to 1000000000");
        }
    }
}
