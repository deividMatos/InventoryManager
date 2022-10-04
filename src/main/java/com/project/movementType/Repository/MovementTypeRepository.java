package com.project.movementType.Repository;

import com.project.model.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementTypeRepository extends JpaRepository<MovementType, Long> {
}
