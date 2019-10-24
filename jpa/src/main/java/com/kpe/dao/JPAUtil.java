package com.kpe.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "kpe";
    private static EntityManagerFactory emf;

    private static EntityManagerFactory buildSessionFactory() {
        try{
            if (emf == null){
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            }
            return emf;
    }catch (Exception e){
        e.printStackTrace();
        throw new RuntimeException("There is an exception while building  the factory");
        }
    }


    public static EntityManagerFactory getSessionFactory(){
        if(emf ==null)
            buildSessionFactory();
        return emf;
    }
}