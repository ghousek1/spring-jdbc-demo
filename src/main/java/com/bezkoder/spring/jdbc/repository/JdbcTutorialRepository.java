package com.bezkoder.spring.jdbc.repository;

import java.util.Arrays;
import java.util.List;

import com.bezkoder.spring.jdbc.model.TutorialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jdbc.model.Tutorial;

@Repository
public class JdbcTutorialRepository implements TutorialRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public int save(Tutorial tutorial) {
    return jdbcTemplate.update("INSERT INTO tutorials (title, description, published) VALUES(?,?,?)",
        new Object[] { tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished() });
  }

  @Override
  public int update(Tutorial tutorial) {
    return jdbcTemplate.update("UPDATE tutorials SET title=?, description=?, published=? WHERE id=?",
        new Object[] { tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished(), tutorial.getId() });
  }

  @Override
  public Tutorial findById(Long id) {
    try {
      Tutorial tutorial = jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE id=?",
          BeanPropertyRowMapper.newInstance(Tutorial.class), id);

      return tutorial;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM tutorials WHERE id=?", id);
  }

  @Override
  public List<Tutorial> findAll() {
    return jdbcTemplate.query("SELECT * from tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
  }

  @Override
  public List<TutorialDto> findAll2() {
    BeanPropertyRowMapper<TutorialDto> rowMapper = BeanPropertyRowMapper.newInstance(TutorialDto.class);
    SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
            .addValue("gParam", "developer")
            .addValue("gList", Arrays.asList(1,3));

//    RowMapper<TutorialDto> tutorialDtoRowMapper = (rs, rowNum) -> new TutorialDto(
//            rs.getInt("id"), rs.getString("titleOfTut"),
//            rs.getString("desc"), rs.getBoolean("published"));

    return namedParameterJdbcTemplate.query("SELECT tut.id as id ,tut.title as titleOfTut," +
            " tut.description as des ," +
            "  tut.published  as published  from tutorials tut" +
            " where tut.description= :gDesc",mapSqlParameterSource, rowMapper);
  }

  @Override
  public List<Tutorial> findByPublished(boolean published) {
    return jdbcTemplate.query("SELECT * from tutorials WHERE published=?",
        BeanPropertyRowMapper.newInstance(Tutorial.class), published);
  }

  @Override
  public List<Tutorial> findByTitleContaining(String title) {
    String q = "SELECT * from tutorials WHERE title ILIKE '%" + title + "%'";

    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Tutorial.class));
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from tutorials");
  }
}
