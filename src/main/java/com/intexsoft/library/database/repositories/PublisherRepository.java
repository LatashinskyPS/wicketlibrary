package com.intexsoft.library.database.repositories;

import com.intexsoft.library.database.entities.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Component
public class PublisherRepository implements Serializable {
    private final SessionFactory sessionFactory;

    public PublisherRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
