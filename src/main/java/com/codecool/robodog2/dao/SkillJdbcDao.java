package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.dao.mapper.SkillMapper;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SkillJdbcDao implements SkillDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SkillMapper skillMapper;
    private final DogMapper dogMapper;

    public SkillJdbcDao(JdbcTemplate jdbcTemplate, SkillMapper skillMapper, DogMapper dogMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.skillMapper = skillMapper;
        this.dogMapper = dogMapper;
    }

    @Override
    public void addSkill(Skill skill) {
        String sql = "INSERT INTO skill(dog_id, trick_id, level) VALUES (?,?,?);";
        jdbcTemplate.update(sql, skill.getDogId(), skill.getTrickId(), skill.getLevel());
    }

    @Override
    public List<Skill> listSkills() {
        String sql = "SELECT id, dog_id, trick_id, level FROM skill;";
        return jdbcTemplate.query(sql, skillMapper);
    }

    @Override
    public Optional<Skill> getSkill(long id) {
        String sql = "SELECT id, dog_id, trick_id, level FROM skill WHERE id=?;";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, skillMapper, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(skill);
    }

    @Override
    public void updateSkill(Skill skill, long id) {
        String sql = "UPDATE skill SET dog_id=?, trick_id=?, level=? WHERE id=?;";
        jdbcTemplate.update(sql, skill.getDogId(), skill.getTrickId(), skill.getLevel(), id);
    }

    @Override
    public void deleteSkill(long id) {
        String sql = "DELETE FROM skill WHERE id=?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Dog> listDogsByTrickId(long trickId) {
        String sql = "SELECT dog.id, breed, age, name \n" +
                "FROM dog \n" +
                "JOIN skill ON dog.id=skill.dog_id\n" +
                "WHERE trick_id=?;\n";
        return jdbcTemplate.query(sql, dogMapper, trickId);
    }

    @Override
    public Optional<Skill> getSkillByDogIdAndTrickId(long dogId, long trickId) {
        String sql = "SELECT id, dog_id, trick_id, level FROM skill WHERE dog_id=? AND trick_id=?;";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, skillMapper, dogId, trickId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(skill);
    }

    @Override
    public Optional<Skill> getSkillByDogIdAndTrickName(long dogId, String trickName) {
        String sql = "SELECT id, dog_id, trick_id, level FROM skill WHERE dog_id=? AND trick_id=(SELECT id FROM trick WHERE name=?);";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, skillMapper, dogId, trickName);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(skill);
    }

    @Override
    public void updateSkillByDogIdAndTrickName(long dogId, String trickName) {
        Skill skillToBeUpdated = getSkillByDogIdAndTrickName(dogId, trickName).orElseThrow();
        int currentLevel = skillToBeUpdated.getLevel();
        if (currentLevel < 3) {
            String sql = "UPDATE skill SET level=? WHERE dog_id=? AND trick_id=(SELECT id FROM trick WHERE name=?);";
            jdbcTemplate.update(sql, currentLevel + 1, dogId, trickName);
        }
    }
}
