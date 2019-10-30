package com.kpe.dao;

import java.util.List;

public interface Dao<T> {

  /**
   * CRUD
   */
  T queryById();

  List<T> queryByCondition();

  List<T> queryAll();

  void save(T entity);

  void save(List<T> entities);

  void update(T entity);

  void update(List<T> entities);

  void delete(T entity);

  void delete(List<T> entities);
}
