package com.kpe.dao.impl;

import com.kpe.bo.ItemBO;
import com.kpe.po.OrderPO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

  private EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;
  private EntityTransaction transaction;

  @BeforeEach
  void setUp() {
    entityManagerFactory = Persistence.createEntityManagerFactory("test");
    entityManager = entityManagerFactory.createEntityManager();
    transaction = entityManager.getTransaction();
    transaction.begin();
  }

  @AfterEach
  void tearDown() {
    transaction.commit();
    entityManager.close();
    entityManagerFactory.close();
  }

  @Test
  void test(){
    List<OrderPO> resultList = entityManager.createQuery("from " + OrderPO.class.getName()).getResultList();
    System.out.println(resultList.toString());
  }
}
