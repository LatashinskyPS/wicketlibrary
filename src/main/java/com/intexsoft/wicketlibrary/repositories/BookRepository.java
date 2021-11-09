package com.intexsoft.wicketlibrary.repositories;

import com.intexsoft.wicketlibrary.HibernateSessionFactory;
import com.intexsoft.wicketlibrary.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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

    public List<Book> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Book> publisherList = session.createQuery("from Book ").getResultList();
        session.getTransaction().commit();
        session.close();
        return publisherList;
    }
}

