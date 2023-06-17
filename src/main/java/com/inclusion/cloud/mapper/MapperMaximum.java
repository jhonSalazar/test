package com.inclusion.cloud.mapper;

import com.inclusion.cloud.dto.MaximumRequestDTO;
import com.inclusion.cloud.model.Maximum;
import org.springframework.stereotype.Component;

@Component
public interface MapperMaximum {
    Maximum maximumDtoToMaximumEntity(MaximumRequestDTO maximumRequestDTO, Long result);
}
