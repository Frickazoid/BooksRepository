package tel_ran.books.interfases;

import tel_ran.books.entity.Author;
import tel_ran.books.entity.Book;
import tel_ran.books.entity.Publisher;

public interface IBooks {

    boolean addBook(Book book);
    boolean removeBook(long isbn);
    Book getBookByISBN(long isbn);
    Iterable<Book> getBooksByAuthor(String nameAuthor);
    Iterable<Book> getBooksByPublisher(String namePublisher);
    Iterable<Author> getAuthorsByBook(long isbn);
    Iterable<Publisher> getPublishersByAuthor(String nameAuthor);

}
