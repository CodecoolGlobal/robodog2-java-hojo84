package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import com.codecool.robodog2.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public void addSkill(@RequestBody Skill skill) {
        skillService.addSkill(skill);
    }

    @GetMapping
    public List<Skill> listSkills() {
        return skillService.listSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable long id) {
        return skillService.getSkill(id);
    }

    @PutMapping("/{id}")
    public void updateSkill(@RequestBody Skill skill, @PathVariable long id) {
        skillService.updateSkill(skill, id);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable long id) {
        skillService.deleteSkill(id);
    }

    @GetMapping("/dog/trick/{trickId}")
    public List<Dog> listDogsByTrickId(@PathVariable long trickId) {
        return skillService.listDogsByTrickId(trickId);
    }

    @GetMapping("/dog/{dogId}/trick/{trickId}")
    public Skill getSkillByDogIdAndTrickId(@PathVariable long dogId, @PathVariable long trickId) {
        return skillService.getSkillByDogIdAndTrickId(dogId, trickId);
    }
}
