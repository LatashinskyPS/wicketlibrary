package com.intexsoft.library.database.repositories;

import com.intexsoft.library.database.HibernateSessionFactory;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.entities.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

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

    public Publisher findById(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Publisher publisher = session.get(Publisher.class, uuid);
        session.getTransaction().commit();
        session.close();
        return publisher;
    }

    public void delete(Publisher publisher) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.refresh(publisher);
        session.delete(publisher);
        session.getTransaction().commit();
        session.close();
    }
}
