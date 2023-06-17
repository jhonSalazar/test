package com.inclusion.cloud.service;
import com.inclusion.cloud.dto.MaximumRequestDTO;
import com.inclusion.cloud.exception.InvalidConstraintException;

public interface MaximumService {
    long getMaximum(Long x, Long y, Long n);
    void createMaximumResult(MaximumRequestDTO maximumRequestDTO) throws InvalidConstraintException;
    Long getResultByXAndYAndN(Long x, Long y, Long n) throws InvalidConstraintException;
}
