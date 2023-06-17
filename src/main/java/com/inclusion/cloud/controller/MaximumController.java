package com.inclusion.cloud.controller;

import com.inclusion.cloud.dto.MaximumRequestDTO;
import com.inclusion.cloud.dto.ResponseDTO;
import com.inclusion.cloud.exception.InvalidConstraintException;
import com.inclusion.cloud.service.MaximumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(MaximumController.BASE_URL)
public class MaximumController {
    public static final String BASE_URL = "/api/v1/maximum";
    public static final String ENDPOINT_GET_RESULT = "/{x}/{y}/{n}";
    private final MaximumService maximumService;

    public MaximumController(MaximumService maximumService) {
        this.maximumService = maximumService;
    }

    @GetMapping(ENDPOINT_GET_RESULT)
    public ResponseEntity<ResponseDTO> getByUserId(
            @PathVariable(name = "x") Long x,
            @PathVariable(name = "y") Long y,
            @PathVariable(name = "n") Long n
    ) throws InvalidConstraintException {
        return new ResponseEntity<>(ResponseDTO.builder().status(true).data(maximumService.getResultByXAndYAndN(x, y, n)).build(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> getByUserId(@Valid @RequestBody MaximumRequestDTO maximumRequestDTO) throws InvalidConstraintException {
        maximumService.createMaximumResult(maximumRequestDTO);
        return new ResponseEntity<>(ResponseDTO.builder().status(true).build(), HttpStatus.OK);
    }
}
