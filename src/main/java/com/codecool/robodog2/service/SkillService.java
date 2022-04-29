package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.SkillDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillDAO skillDAO;

    @Autowired
    public SkillService(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    public void addSkill(Skill skill) {
        skillDAO.addSkill(skill);
    }

    public List<Skill> listSkills() {
        return skillDAO.listSkills();
    }

    public Skill getSkill(long id) {
        return skillDAO.getSkill(id).orElseThrow();
    }

    public void updateSkill(Skill skill, long id) {
        skillDAO.updateSkill(skill, id);
    }

    public void deleteSkill(long id) {
        skillDAO.deleteSkill(id);
    }

    public List<Dog> listDogsByTrickId(long trickId) {
        return skillDAO.listDogsByTrickId(trickId);
    }

    public Skill getSkillByDogIdAndTrickId(long dogId, long trickId) {
        return skillDAO.getSkillByDogIdAndTrickId(dogId, trickId).orElseThrow();
    }

    public Skill getSkillByDogIdAndTrickName(long dogId, String trickName) {
        return skillDAO.getSkillByDogIdAndTrickName(dogId, trickName).orElseThrow();
    }

    public void updateSkillByDogIdAndTrickName(long dogId, String trickName) {
        skillDAO.updateSkillByDogIdAndTrickName(dogId, trickName);
    }
}
