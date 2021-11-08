package com.intexsoft.repositories;

import com.intexsoft.HibernateSessionFactory;
import com.intexsoft.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BookRepository {
    private static final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private static final BookRepository bookRepository = new BookRepository();

    private BookRepository() {
    }

    public static BookRepository getInstance() {
        return bookRepository;
    }

    public void save(Book book){
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
}

