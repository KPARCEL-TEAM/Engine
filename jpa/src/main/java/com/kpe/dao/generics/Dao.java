package com.kpe.dao.generics;

import javax.persistence.EntityManager;
import java.util.List;

public interface Dao<T>{
    public List<T> findAll();
    public T save(T entity);
    public void delete(T entity);
    public void flush();
    public void clear();
    public void setSession(EntityManager session);
}
