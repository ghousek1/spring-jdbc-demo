package com.bezkoder.spring.jdbc.repository;

import java.util.List;

import com.bezkoder.spring.jdbc.model.Tutorial;
import com.bezkoder.spring.jdbc.model.TutorialDto;

public interface TutorialRepository {
  int save(Tutorial book);

  int update(Tutorial book);

  Tutorial findById(Long id);

  int deleteById(Long id);

  List<Tutorial> findAll();
  List<TutorialDto> findAll2();

  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);

  int deleteAll();
}
