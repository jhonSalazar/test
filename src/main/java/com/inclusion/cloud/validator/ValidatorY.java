package com.inclusion.cloud.validator;
import java.util.List;
public class ValidatorY extends ValidatorInput  {
    @Override
    void validate(Long x,Long y, Long n, List<String> errors){
        if (y >= x) {
            errors.add("y parameter must be less than X");
        }
        if (y < 0) {
            errors.add("y parameter must be greater than or equal to 0");
        }
        if (y > n) {
            errors.add("y parameter must be less than or equal to N");
        }
    }
}
