package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogJdbcDao implements DogDAO {

    private static final Logger log = LoggerFactory.getLogger(DogMemDao.class);
    private final JdbcTemplate jdbcTemplate;

    public DogJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addDog(Dog dog) {

    }

    @Override
    public List<Dog> listDogs() {
        return null;
    }

    @Override
    public Dog getDog(long id) {
        return null;
    }

    @Override
    public void updateDog(Dog dog, long id) {

    }

    @Override
    public void deleteDog(long id) {

    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        return 0;
    }
}
