package com.kpe.dao.generics;

import com.kpe.dao.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDAO<T> implements Dao<T>{
    private EntityManager em;
    private Class<T> persistentClass;
    public AbstractDAO(){
        this.persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public AbstractDAO(Class<T> type) {this.persistentClass = type; }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void flush() {

    }
    public T create(T entity){
        EntityTransaction et = getSession().getTransaction();
        et.begin();
        this.getSession().persist(entity);
        et.commit();
        return entity;
    }

    private EntityManager getSession() {
        return JPAUtil.getSessionFactory().createEntityManager();
    }

    @Override
    public void clear() { }

    public void setSession(EntityManager session){ this.em = session;

    }
}
