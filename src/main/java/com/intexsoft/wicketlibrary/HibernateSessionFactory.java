package com.intexsoft.wicketlibrary;

import com.intexsoft.wicketlibrary.entities.Author;
import com.intexsoft.wicketlibrary.entities.Book;
import com.intexsoft.wicketlibrary.entities.Publisher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = getNewSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory getNewSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(Author.class).addAnnotatedClass(Publisher.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
    }
}
