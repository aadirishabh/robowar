package org.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.task.entity.Resource;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Integer> {
}
