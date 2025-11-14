package com.example.portfolio.repository;

import com.example.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {
    List<Project> findTop3ByOrderByCreatedDateDesc();

    List<Project> findByTechnologiesContainingIgnoreCase(String technology);

    @Query("SELECT COUNT(p) FROM Project p WHERE LOWER(p.technologies) LIKE LOWER(CONCAT('%', :technology, '%'))")
    Long countByTechnology(String technology);

    List<Project> findAllByOrderByCreatedDateDesc();
}
