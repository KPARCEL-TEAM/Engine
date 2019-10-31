package com.kpe.dao.impl;

import com.kpe.base.BaseDAO;
import com.kpe.po.OrderPO;

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
    List<OrderPO> resultList = em.createQuery("from Order", OrderPO.class).getResultList();
    return resultList;
  }

  @Override
  public void save(OrderPO entity) {
    em.persist(entity);
  }

  @Override
  public void save(List<OrderPO> entities) {

  }

  @Override
  public void update(OrderPO entity) {
  }

  @Override
  public void update(List<OrderPO> entities) {

  }

  @Override
  public void delete(OrderPO entity) {

  }

  @Override
  public void delete(List<OrderPO> entities) {

  }
}
