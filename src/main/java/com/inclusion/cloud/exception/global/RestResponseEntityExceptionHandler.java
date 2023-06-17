package com.inclusion.cloud.exception.global;
import com.inclusion.cloud.dto.ResponseDTO;
import com.inclusion.cloud.exception.InvalidConstraintException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected ResponseEntity<Object> exception(Exception e) {
        this.logger.error("error ", e);
        return new ResponseEntity<>(ResponseDTO.builder().errors(List.of(e.getMessage())).status(false).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidConstraintException.class)
    @ResponseBody
    protected ResponseEntity<Object> invalidConstraintException(InvalidConstraintException e) {
        this.logger.error("error ", e);
        return new ResponseEntity<>(ResponseDTO.builder().errors(List.of(e.getMessage())).status(false).build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = e.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        logger.info("Validation error list : "+validationList);
        return new ResponseEntity<>(ResponseDTO.builder().errors(validationList).status(false).build(), HttpStatus.BAD_REQUEST);
    }
}
