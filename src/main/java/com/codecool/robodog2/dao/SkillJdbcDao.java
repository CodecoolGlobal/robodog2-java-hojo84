package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.SkillMapper;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SkillJdbcDao implements SkillDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SkillMapper skillMapper;

    public SkillJdbcDao(JdbcTemplate jdbcTemplate, SkillMapper skillMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.skillMapper = skillMapper;
    }

    @Override
    public void addSkill(Skill skill) {

    }

    @Override
    public List<Skill> listSkills() {
        return null;
    }

    @Override
    public Optional<Skill> getSkill(long id) {
        return Optional.empty();
    }

    @Override
    public void updateSkill(Skill skill, long id) {

    }

    @Override
    public void deleteSkill(long id) {

    }

    @Override
    public List<Dog> listDogsByTrickId(long trickId) {
        return null;
    }

    @Override
    public Optional<Skill> getSkillByDogIdAndTrickId(long dogId, long trickId) {
        return Optional.empty();
    }
}
