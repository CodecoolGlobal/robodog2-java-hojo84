package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.model.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class DogJdbcDao implements DogDAO {

    private static final Logger log = LoggerFactory.getLogger(DogMemDao.class);
    private final JdbcTemplate jdbcTemplate;
    private final DogMapper dogMapper;

    public DogJdbcDao(JdbcTemplate jdbcTemplate, DogMapper dogMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.dogMapper = dogMapper;
    }

    @Override
    public void addDog(Dog dog) {
        String sql = "INSERT INTO dog (breed, age, name) VALUES (?,?,?)";
        final int insert = jdbcTemplate.update(sql, dog.getBreed(), dog.getAge(), dog.getName());
        if (insert == 1)
            log.info("New dog added: " + dog.getName());
    }

    @Override
    public List<Dog> listDogs() {
        String sql = " SELECT id, breed, age, name FROM dog";
        return jdbcTemplate.query(sql, dogMapper);
    }

    @Override
    public Optional<Dog> getDog(long id) {
        String sql = " SELECT id, breed, age, name FROM dog WHERE id =?";
        Dog dog = null;
        try {
            dog = jdbcTemplate.queryForObject(sql, dogMapper, id);
        } catch (DataAccessException e) {
            log.info("Dog not found: " + id);
        }
        return Optional.ofNullable(dog);
    }

    @Override
    public void updateDog(Dog dog, long id) {
        String sql = "UPDATE dog SET breed=?, age=?, name=? where id=?";
        final int update = jdbcTemplate.update(sql, dog.getBreed(), dog.getAge(), dog.getName(), id);
        if (update == 1)
            log.info("Dog updated: " + dog.getName());
    }

    @Override
    public void deleteDog(long id) {
        String sql = "DELETE FROM dog WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        String sql = "INSERT INTO dog (breed, age, name) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(dog.getBreed()));
            ps.setInt(2, dog.getAge());
            ps.setString(3, dog.getName());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
