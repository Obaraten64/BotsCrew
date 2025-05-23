package com.bots.crew.botscrew.repository;

import com.bots.crew.botscrew.model.Department;
import com.bots.crew.botscrew.model.Lector;

import java.util.List;

import com.bots.crew.botscrew.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> searchLectorsByFullNameContaining(String name);
    List<Lector> searchLectorsByDepartment(Department department);
    int countLectorsByPositionAndDepartment(Position position, Department department);
    int countLectorsByDepartment(Department department);
}
