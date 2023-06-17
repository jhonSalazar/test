package com.inclusion.cloud.validator;
import com.inclusion.cloud.exception.InvalidConstraintException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class ValidatorConstraint {
    private final List<ValidatorInput> validators;

    public ValidatorConstraint(){
        this.validators = List.of(new ValidatorN(), new ValidatorY(), new ValidatorX());
    }
    public List<String> ValidateInputNAndYParameters(Long x, Long y, Long n) throws InvalidConstraintException {
        List<String> errors = new ArrayList<>();
        validators.forEach(validatorConstraint -> validatorConstraint.validate(x,y,n, errors));
        if (!errors.isEmpty()) throw new InvalidConstraintException(errors.toString());
        return errors;
    }
}
