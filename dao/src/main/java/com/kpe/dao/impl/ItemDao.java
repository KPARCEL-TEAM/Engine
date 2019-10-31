package com.kpe.dao.impl;

import com.kpe.base.BaseDAO;
import com.kpe.po.ItemPO;
import lombok.NoArgsConstructor;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-31 17:34
 */
@NoArgsConstructor
public class ItemDao extends BaseDAO<ItemPO> {

  @Override
  public ItemPO queryById(Integer id) {
    return null;
  }

  @Override
  public List<ItemPO> queryByCondition(Class<?>... parameters) {
    return null;
  }

  @Override
  public List<ItemPO> queryAll() {
    return null;
  }

  @Override
  public void save(ItemPO entity) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.persist(entity);
    transaction.commit();
  }

  @Override
  public void save(List<ItemPO> entities) {
    entities.forEach(this::save);
  }

  @Override
  public void update(ItemPO entity) {

  }

  @Override
  public void update(List<ItemPO> entities) {

  }

  @Override
  public void delete(ItemPO entity) {

  }

  @Override
  public void delete(List<ItemPO> entities) {

  }
}
