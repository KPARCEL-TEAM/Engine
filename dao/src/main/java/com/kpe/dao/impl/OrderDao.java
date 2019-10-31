package com.kpe.dao.impl;

import com.kpe.base.BaseDAO;
import com.kpe.po.OrderPO;

import javax.persistence.EntityTransaction;
import java.util.List;

public class OrderDao extends BaseDAO<OrderPO> {

  @Override
  public OrderPO queryById(Integer id) {
    OrderPO orderPO = em.find(OrderPO.class, id);
    return orderPO;
  }

  @Override
  public List<OrderPO> queryByCondition(Class<?>... parameters) {

    return null;
  }

  @Override
  public List<OrderPO> queryAll() {
    List<OrderPO> resultList = em.createQuery("from " + OrderPO.class.getName()).getResultList();
    return resultList;
  }

  @Override
  public void save(OrderPO entity) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.persist(entity);
    transaction.commit();
  }

  @Override
  public void save(List<OrderPO> entities) {
    entities.forEach(this::save);
  }

  @Override
  public void update(OrderPO entity) {
    em.merge(entity);
  }

  @Override
  public void update(List<OrderPO> entities) {
    entities.forEach(this::update);
  }

  @Override
  public void delete(OrderPO entity) {
    em.remove(queryById(entity.getId()));
  }

  @Override
  public void delete(List<OrderPO> entities) {
    entities.forEach(this::delete);
  }
}
