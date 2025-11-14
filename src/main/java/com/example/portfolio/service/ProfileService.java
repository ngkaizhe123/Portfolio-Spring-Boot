package com.example.portfolio.service;

import com.example.portfolio.model.Profile;
import com.example.portfolio.model.Skill;
import com.example.portfolio.repository.ProfileRepository;
import com.example.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillRepository skillRepository;

    public Profile getProfile() {
        // try to get ID 1 for the profile，if no will create a default id
        Optional<Profile> existingProfile = profileRepository.findById(1L);

        if (existingProfile.isPresent()) {
            return existingProfile.get();
        } else {
            return createDefaultProfile();
        }
    }

    public Profile updateProfile(Profile profile) {
        // Make sure id is 1，because only have 1 profile
        profile.setId(1L);
        return profileRepository.save(profile);
    }

    private Profile createDefaultProfile() {
        Profile profile = new Profile();
        profile.setName("NG KAI ZHE");
        profile.setTitle("Java/Spring Boot Developer");
        profile.setEmail("kaizhe8229@gmail.com");
        profile.setPhone("019-6128190");
        profile.setLocation("Setapak, Kuala Lumpur");
        profile.setSummary("A Diploma in Computer Science student in TAR UMT");
        profile.setGithubUrl("https://github.com/ngkaizhe123");
        profile.setLinkedinUrl("https://linkedin.com/in/kai-zhe-ng-0bb4b5355");

        return profileRepository.save(profile);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public List<Skill> getSkillsByCategory(String category) {
        return skillRepository.findByCategoryOrderByProficiencyDesc(category);
    }

    public List<String> getSkillCategories() {
        return List.of("Backend", "Frontend", "Database", "DevOps", "Tools");
    }

    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    // get skill statistic
    public String getSkillsSummary() {
        List<Skill> skills = getAllSkills();
        long backendCount = skills.stream()
                .filter(skill -> "Backend".equals(skill.getCategory()))
                .count();
        long frontendCount = skills.stream()
                .filter(skill -> "Frontend".equals(skill.getCategory()))
                .count();

        return String.format("Efficient %d backend technology and %d frontend technology", backendCount, frontendCount);
    }
}
