package com.inclusion.cloud.validator;

import java.util.List;

import static com.inclusion.cloud.constants.Constants.MAX_X_VALUE;

public class ValidatorX extends ValidatorInput  {
    @Override
    void validate(Long x,Long y, Long n, List<String> errors){
        // x
        if (x < 2) {
            errors.add("x parameter must be greater than or equal to 2");
        }

        if (x > MAX_X_VALUE) {
            errors.add("x parameter must be less than or equal to 1000000000");
        }
    }
}
