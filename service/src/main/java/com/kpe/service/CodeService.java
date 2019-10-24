package com.kpe.service;

import com.kpe.dao.generics.AbstractDAO;
import com.kpe.objects.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CodeService<E> extends AbstractDAO<E> {
    private Class<E> clazz;
    private static EntityManagerFactory emf;
    private static EntityManager em;
    public CodeService(Class<E> entityType){
        super(entityType);
        this.clazz = entityType;

    }


    public E  save(E entityObject){
        {

            try {

                EntityTransaction tx = em.getTransaction();
                tx.begin();

                em.persist(entityObject);

                tx.commit();

            }catch(Exception e){
                System.out.println("保存记录出现异常:");
                e.printStackTrace();

            }

        }

        return entityObject;

    }


    public  void saveList(List<E> entityList){
        openConn();
        entityList.stream().forEach(this::save);
        closeconn();
    }

    private static void openConn(){

        if (emf == null|| !emf.isOpen())
            emf = Persistence.createEntityManagerFactory("k-parcel");

        if(em ==null|| !em.isOpen())
            em = emf.createEntityManager();
    }

    private static void closeconn(){

        if (em!=null)
                em.close();
        if (emf!=null)
                emf.close();
    }


    }
