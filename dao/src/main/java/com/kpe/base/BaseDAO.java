package com.kpe.base;

import com.kpe.config.DataSourceInit;
import com.kpe.dao.Dao;

import javax.persistence.EntityManager;

public abstract class BaseDAO<T> implements Dao<T> {

  public static EntityManager em = DataSourceInit.getSessionFactory().createEntityManager();

}
