package com.kpe.base;

import com.kpe.config.DataSourceInit;
import com.kpe.dao.DaoInterface;

import javax.persistence.EntityManager;

public abstract class BaseDAO<T> implements DaoInterface<T> {

  public static EntityManager em = DataSourceInit.getSessionFactory().createEntityManager();
}
