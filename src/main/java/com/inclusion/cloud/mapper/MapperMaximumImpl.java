package com.inclusion.cloud.mapper;

import com.inclusion.cloud.dto.MaximumRequestDTO;
import com.inclusion.cloud.model.Maximum;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperMaximumImpl implements MapperMaximum {
    private final DozerBeanMapper dozerBeanMapper;
    public MapperMaximumImpl(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }
    @Override
    public Maximum maximumDtoToMaximumEntity(MaximumRequestDTO maximumRequestDTO, Long result) {
       Maximum maximum = dozerBeanMapper.map(maximumRequestDTO, Maximum.class);
       maximum.setResult(result);
       return maximum;
    }
}
