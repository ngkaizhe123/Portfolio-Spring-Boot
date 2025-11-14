package com.example.portfolio.config;

import com.example.portfolio.model.Profile;
import com.example.portfolio.model.Project;
import com.example.portfolio.model.Skill;
import com.example.portfolio.repository.ProfileRepository;
import com.example.portfolio.repository.ProjectRepository;
import com.example.portfolio.repository.SkillRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SkillRepository skillRepository;

    @PostConstruct
    public void loadData() {
        System.out.println("=== Start initial data ===");

        loadSkillData();
        loadProjectData();
        loadProfileData();

        System.out.println("=== Data load completed ===");
        System.out.println("Skills: " + skillRepository.count());
        System.out.println("Projects: " + projectRepository.count());
        System.out.println("Personal info: " + profileRepository.count());
    }

    private void loadProfileData() {
        if (profileRepository.count() > 0) {
            System.out.println("The personal info already have, skip initial process");
            return;
        }

        try {
            Profile profile = new Profile();
            profile.setName("NG KAI ZHE");
            profile.setTitle("Diploma in Computer Science");
            profile.setEmail("kaizhe8229@gmail.com");
            profile.setPhone("019-612 8190");
            profile.setLocation("Setapak, Kuala Lumpur");
            profile.setSummary("A Diploma student in TAR UMT ");
            profile.setGithubUrl("https://github.com/ngkaizhe123");
            profile.setLinkedinUrl("https://linkedin.com/in/kai-zhe-ng-0bb4b5355");

            Profile savedProfile = profileRepository.save(profile);
            System.out.println("Personal info already loaded，ID: " + savedProfile.getId());
        } catch (Exception e) {
            System.out.println("Personal info load fail: " + e.getMessage());
        }
    }

    private void loadSkillData() {
        if (skillRepository.count() > 0) {
            System.out.println("Skills data already have, skip initial process");
            return;
        }

        try {
            List<Skill> skills = Arrays.asList(
                    new Skill("Java", "Backend", 85),
                    new Skill("Spring Boot", "Backend", 70),
                    new Skill("C++", "Backend", 90),
                    new Skill("Kotlin", "Backend", 80),
                    new Skill("MySQL", "Database", 85),
                    new Skill("HTML/CSS", "Frontend", 60),
                    new Skill("JavaScript", "Frontend", 60),
                    new Skill("Git", "Tools", 85),
                    new Skill("Maven", "Tools", 80),
                    new Skill("Linux", "DevOps", 75)
            );

            skillRepository.saveAll(skills);
            System.out.println("Skill already loaded, count: " + skills.size());
        } catch (Exception e) {
            System.out.println("Skill load fail: " + e.getMessage());
        }
    }

    private void loadProjectData() {
        if (projectRepository.count() > 0) {
            System.out.println("Projects already have, skip initial process");
            return;
        }

        try {
            List<Project> projects = Arrays.asList(
                    createProject(
                            "My Todo List",
                            "The manage tasks system by Spring Boot",
                            "The user can add the task and delete the task ",
                            "Spring Boot, MySQL, Java , H2, Thymeleaf",
                            "https://github.com/ngkaizhe123/Todo-list-Spring-Boot-Project",
                            "http://localhost:8081/todos",
                            "/images/project1.jpg",
                            LocalDate.of(2025, 11, 10)
                    ),
                    createProject(
                            "OEM Event Management System",
                            "The phone product launch event management system",
                            "The OEM manufacturer can create the product launch event",
                            "C++, C",
                            "https://github.com/ss3-3/PhoneEMS_cplusplus_DCS2S1",
                            "",
                            "/images/project2.jpg",
                            LocalDate.of(2025, 9, 10)
                    )
            );

            List<Project> savedProjects = projectRepository.saveAll(projects);
            System.out.println("Project already loaded, count: " + savedProjects.size());

            // 打印所有保存的项目
            for (Project project : savedProjects) {
                System.out.println("Saved project: " + project.getName() + ", ID: " + project.getId());
            }
        } catch (Exception e) {
            System.out.println("Project load fail: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Project createProject(String name, String description, String details,
                                  String technologies, String githubUrl, String demoUrl,
                                  String imageUrl, LocalDate createdDate) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setDetails(details);
        project.setTechnologies(technologies);
        project.setGithubUrl(githubUrl);
        project.setDemoUrl(demoUrl);
        project.setImageUrl(imageUrl);
        project.setCreatedDate(createdDate);
        return project;
    }
}
