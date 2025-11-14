package com.example.portfolio.repository;

import com.example.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByCategoryOrderByProficiencyDesc(String category);

    List<Skill> findByProficiencyGreaterThanEqual(Integer proficiency);

    @Query("SELECT s.category, COUNT(s) FROM Skill s GROUP BY s.category")
    List<Object[]> countSkillsByCategory();

    List<Skill> findAllByOrderByProficiencyDesc();
}
