package org.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.task.entity.LastLocation;

@Repository
public interface LastLocationRepo extends JpaRepository<LastLocation, Integer> {
}
