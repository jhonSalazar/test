package com.inclusion.cloud.service;

import com.inclusion.cloud.validator.ValidatorConstraint;
import com.inclusion.cloud.exception.InvalidConstraintException;
import com.inclusion.cloud.model.Maximum;
import com.inclusion.cloud.repository.MaximumRepository;
import com.inclusion.cloud.dto.MaximumRequestDTO;
import com.inclusion.cloud.mapper.MapperMaximum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static com.inclusion.cloud.config.CacheConfig.CACHE_KEY_RESULT_MAXIMUM;

@Service
public class MaximumServiceImpl implements MaximumService {
    private final MapperMaximum mapperMaximum;
    private final MaximumRepository maximumRepository;
    private final ValidatorConstraint validator;

    @Autowired
    public MaximumServiceImpl(MapperMaximum mapperMaximum, MaximumRepository maximumRepository, ValidatorConstraint validator) {
        this.mapperMaximum = mapperMaximum;
        this.maximumRepository = maximumRepository;
        this.validator = validator;
    }
    @Override
    public long getMaximum(Long x, Long y, Long n) {
        long c = (n - y) / x;
        //Remainder Formula
        return  ((x * c) + y);
    }

    @Override
    public void createMaximumResult(MaximumRequestDTO maximumRequestDTO) throws InvalidConstraintException {
       validator.ValidateInputNAndYParameters(maximumRequestDTO.getX(),
                maximumRequestDTO.getY(),
                maximumRequestDTO.getN());
        maximumRepository.save(mapperMaximum.maximumDtoToMaximumEntity(maximumRequestDTO,
                getMaximum(maximumRequestDTO.getX(),
                        maximumRequestDTO.getY(),
                        maximumRequestDTO.getN())));
    }

    @Override
    @Cacheable(CACHE_KEY_RESULT_MAXIMUM)
    public Long getResultByXAndYAndN(Long x, Long y, Long n) throws InvalidConstraintException {
        validator.ValidateInputNAndYParameters(x, y, n);
        Optional<Maximum> maximumOptional = this.maximumRepository.findByXAndYAndN(x, y, n);
        return maximumOptional.isPresent() ? maximumOptional.get().getResult() : getMaximum(x, y, n);
    }
}
