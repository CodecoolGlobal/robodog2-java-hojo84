package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.TrickMapper;
import com.codecool.robodog2.model.Trick;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrickJdbcDao implements TrickDAO {

    private final JdbcTemplate jdbcTemplate;
    private final TrickMapper trickMapper;

    public TrickJdbcDao(JdbcTemplate jdbcTemplate, TrickMapper trickMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.trickMapper = trickMapper;
    }

    @Override
    public void addTrick(Trick trick) {

    }

    @Override
    public List<Trick> listTricks() {
        return null;
    }

    @Override
    public Optional<Trick> getTrick(long id) {
        return Optional.empty();
    }

    @Override
    public void updateTrick(Trick trick, long id) {

    }

    @Override
    public void deleteTrick(long id) {

    }
}
