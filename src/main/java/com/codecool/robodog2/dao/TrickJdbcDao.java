package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.TrickMapper;
import com.codecool.robodog2.model.Trick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrickJdbcDao implements TrickDAO {

    private static final Logger log = LoggerFactory.getLogger(TrickJdbcDao.class);

    private final JdbcTemplate jdbcTemplate;
    private final TrickMapper trickMapper;

    public TrickJdbcDao(JdbcTemplate jdbcTemplate, TrickMapper trickMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.trickMapper = trickMapper;
    }

    @Override
    public void addTrick(Trick trick) {
        String sql = "INSERT INTO trick(name) VALUES (?)";
        jdbcTemplate.update(sql, trick.getName());
    }

    @Override
    public List<Trick> listTricks() {
        String sql = "SELECT id, name FROM trick";
        return jdbcTemplate.query(sql, trickMapper);
    }

    @Override
    public Optional<Trick> getTrick(long id) {
        String sql = "SELECT id, name FROM trick WHERE id=?";
        Trick trick = null;
        try {
            trick = jdbcTemplate.queryForObject(sql, trickMapper, id);
        } catch (DataAccessException e) {
            log.info("Trick not found: " + id);
        }
        return Optional.ofNullable(trick);
    }

    @Override
    public void updateTrick(Trick trick, long id) {
        String sql = "UPDATE trick SET name=? WHERE id=?";
        jdbcTemplate.update(sql, trick.getName(), id);
    }

    @Override
    public void deleteTrick(long id) {
        String sql = "DELETE FROM trick WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
