package com.intexsoft.repositories;

import com.intexsoft.HibernateSessionFactory;
import com.intexsoft.entities.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PublisherRepository {
    private final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private static final PublisherRepository publisherRepository = new PublisherRepository();

    private PublisherRepository() {
    }

    public static PublisherRepository getInstance() {
        return publisherRepository;
    }

    public void save(Publisher publisher) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (publisher.getId() != null) {
            session.update(publisher);
        } else {
            session.save(publisher);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<Publisher> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Publisher> publisherList = session.createQuery("from Publisher ").getResultList();
        session.getTransaction().commit();
        session.close();
        return publisherList;
    }
}
