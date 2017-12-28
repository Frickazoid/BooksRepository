package tel_ran.books.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tel_ran.books.entity.*;
import tel_ran.books.interfases.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;

@Repository
public class BooksRepository implements IBooks {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public boolean addBook(Book book) {
        if (getBookByISBN(book.getIsbn()) != null) {
            return false;
        }
        Publisher publisher = book.getPublisher();
        if (entityManager.find(Publisher.class, publisher.getPublisherName()) == null) {
            entityManager.persist(publisher);
        }
        Set<Author> authors = book.getAuthors();
        for (Author author : authors) {
            if (entityManager.find(Author.class, author.getName()) == null)
                entityManager.persist(author);
        }
        entityManager.persist(book);
        return true;
    }

    @Override
    @Transactional
    public boolean removeBook(long isbn) {
        Book book = getBookByISBN(isbn);
        if (book == null) {
            return false;
        }
        entityManager.remove(book);
        return true;
    }

    @Override
    public Book getBookByISBN(long isbn) {
        return entityManager.find(Book.class, isbn);
    }

    @Override
    public Iterable<Book> getBooksByAuthor(String nameAuthor) {
        Query query = entityManager.createQuery("select b from Book b join b.authors a where a.name=?1");
        query.setParameter(1, nameAuthor);
        return query.getResultList();
    }

    @Override
    public Iterable<Book> getBooksByPublisher(String namePublisher) {
        Query query = entityManager.createQuery("select b from Book b join b.publisher p where p.publisherName=?1");
        query.setParameter(1, namePublisher);
        return query.getResultList();
    }

    @Override
    public Iterable<Author> getAuthorsByBook(long isbn) {
//        Query query = entityManager.createQuery("select b.authors from Book b where b.isbn=?1");
//        query.setParameter(1, isbn);
//        return query.getResultList();
        return getBookByISBN(isbn).getAuthors();
    }

    @Override
    public Iterable<Publisher> getPublishersByAuthor(String nameAuthor) {
        Query query = entityManager.createQuery("select distinct p from Book b join b.publisher p join b.authors a where a.name=?1");
        query.setParameter(1, nameAuthor);
        return query.getResultList();
    }
}
