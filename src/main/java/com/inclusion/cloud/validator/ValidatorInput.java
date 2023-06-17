package com.inclusion.cloud.validator;

import java.util.List;

public abstract class  ValidatorInput {
    abstract void validate(Long x,Long y, Long n, List<String> errors);
}
