/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fromaremotejavaapplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rasmu
 */
public class Tester {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(new Demo2(1,"Info-1"));
      em.persist(new Demo2(2,"Info-2"));
      em.persist(new Demo2(3,"Info-3"));
      em.getTransaction().commit();
    }finally{
      em.close();
    }
  }
}

