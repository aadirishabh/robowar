package org.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.task.entity.Survivor;

@Repository
public interface SurvivorRepo extends JpaRepository<Survivor, Integer> {
}
