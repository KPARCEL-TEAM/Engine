package com.kpe.config;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataSourceInit {

  private static final String PERSISTENCE_UNIT_NAME = "test";

  private static EntityManagerFactory emf;

  static {
    emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }

  public static EntityManagerFactory getSessionFactory(){
    return emf;
  }

}
