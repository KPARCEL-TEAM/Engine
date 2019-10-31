package com.kpe.dao.impl;

import com.kpe.base.BaseDAO;
import com.kpe.po.OrderPO;

import java.util.List;

public class OrderDao extends BaseDAO<OrderPO> {

  @Override
  public OrderPO queryById() {

    return null;
  }

  @Override
  public List<OrderPO> queryByCondition() {
    return null;
  }

  @Override
  public List<OrderPO> queryAll() {
//    List<OrderPO> resultList = em.createQuery("from Order", OrderPO.class).getResultList();
//    return resultList;
    return null;
  }

  @Override
  public void save(OrderPO entity) {

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
