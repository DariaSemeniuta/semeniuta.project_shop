package com.semeniuta.dao.impl;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.semeniuta.dao.ClientDao;
import com.semeniuta.domain.Client;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
@Service
@Primary
public class ClientEMDao  implements ClientDao{
    private EntityManager entityManager;

    @Autowired
    public ClientEMDao(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public long addClient(Client client) {
        long id = -1l;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.flush();
            entityManager.getTransaction().commit();
            id = client.getId();
        }
        catch(HibernateException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();

        }
        return id;
    }

    @Override
    public Client findClient(long id) {
        try{
            Client client = entityManager.find(Client.class,id);
            return client;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean editClient(long id, String name, String surname, int age, String email, String phone) {
        try{
            entityManager.getTransaction().begin();
            Client client = entityManager.find(Client.class, id);
            client.setName(name);
            client.setSurname(surname);
            client.setAge(age);
            client.setEmail(email);
            client.setPhone(phone);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteClient(long id) {
        try{
            entityManager.getTransaction().begin();
            Client client = entityManager.find(Client.class, id);
            entityManager.remove(client);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> clients = null;
        clients = entityManager.createQuery("from Client", Client.class).getResultList();
        return clients;
    }
}

