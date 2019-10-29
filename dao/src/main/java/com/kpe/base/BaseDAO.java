package com.kpe.base;

import com.kpe.dao.DaoInterface;
import com.kpe.config.DataSourceInit;

import javax.persistence.EntityManager;

public abstract class BaseDAO<T> implements DaoInterface<T> {

  public static EntityManager em = DataSourceInit.getSessionFactory().createEntityManager();




}
