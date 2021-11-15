package com.intexsoft.library.database.repositories;

import com.intexsoft.library.database.HibernateSessionFactory;
import com.intexsoft.library.database.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public class BookRepository {
    private static final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private static final BookRepository bookRepository = new BookRepository();

    private BookRepository() {
    }

    public static BookRepository getInstance() {
        return bookRepository;
    }

    public Book findById(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, uuid);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (book.getId() != null) {
            session.update(book);
        } else {
            session.save(book);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<Book> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Book> publisherList = session.createQuery("from Book order by name").getResultList();
        session.getTransaction().commit();
        session.close();
        return publisherList;
    }

    public void deleteBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.refresh(book);
        session.delete(book);
        session.getTransaction().commit();
        session.close();
    }
}

