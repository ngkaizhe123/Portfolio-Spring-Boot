package com.example.portfolio.service;

import com.example.portfolio.model.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByCreatedDateDesc();
    }

    public List<Project> getRecentProjects(int count) {
        List<Project> allProjects = getAllProjects();
        return allProjects.stream()
                .limit(count)
                .toList();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> searchProjectsByTechnology(String technology) {
        return projectRepository.findByTechnologiesContainingIgnoreCase(technology);
    }

    public List<Project> getFeaturedProjects() {
        return getRecentProjects(3);
    }

    public Long countProjectsByTechnology(String technology) {
        return projectRepository.countByTechnology(technology);
    }

    public List<String> getAllTechnologies() {
        List<Project> projects = getAllProjects();
        return projects.stream()
                .flatMap(project -> {
                    String[] techs = project.getTechnologies().split(",");
                    return java.util.Arrays.stream(techs)
                            .map(String::trim)
                            .map(String::toLowerCase);
                })
                .distinct()
                .toList();
    }
}
