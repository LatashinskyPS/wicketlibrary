package com.intexsoft.repositories;

import com.intexsoft.HibernateSessionFactory;
import com.intexsoft.entities.Author;
import com.intexsoft.entities.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorRepository {
    private static final AuthorRepository authorRepository = new AuthorRepository();

    private AuthorRepository() {
    }

    public static AuthorRepository getInstance() {
        return authorRepository;
    }

    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public void save(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (author.getId() != null) {
            session.update(author);
        } else {
            session.save(author);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<Author> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Author> publisherList = session.createQuery("from Author ").getResultList();
        session.getTransaction().commit();
        session.close();
        return publisherList;
    }
}
