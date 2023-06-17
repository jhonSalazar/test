package com.inclusion.cloud.repository;

import com.inclusion.cloud.model.Maximum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  MaximumRepository extends JpaRepository<Maximum, Long> {
    Optional<Maximum> findByXAndYAndN(Long x, Long y, Long n);
}
