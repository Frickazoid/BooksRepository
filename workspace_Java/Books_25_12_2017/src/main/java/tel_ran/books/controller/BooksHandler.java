package tel_ran.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tel_ran.books.entity.Author;
import tel_ran.books.entity.Book;
import tel_ran.books.entity.Publisher;
import tel_ran.books.interfases.Const;
import tel_ran.books.interfases.IBooks;

@RestController
public class BooksHandler {

    @Autowired
    IBooks books;

    @PostMapping(Const.BOOK)
    public boolean addBook(@RequestBody Book book) {
        return books.addBook(book);
    }

    @GetMapping({Const.BOOK + "/{isbn}"})
    public Book getBook(@PathVariable long isbn) {
        return books.getBookByISBN(isbn);
    }

    @DeleteMapping({Const.BOOK + "/{isbn}"})
    public boolean removeBook(long isbn) {
        return books.removeBook(isbn);
    }

    @GetMapping({Const.AUTHOR + "/{name}"})
    public Iterable<Book> booksByAuthor(@PathVariable String name) {
        return books.getBooksByAuthor(name);
    }

    @GetMapping({Const.PUBLISHER + "/{name}"})
    public Iterable<Book> booksByPublisher(@PathVariable String name) {
        return books.getBooksByPublisher(name);
    }

    @GetMapping({Const.AUTHORS + "/{isbn}"})
    public Iterable<Author> getAuthors(@PathVariable long isbn) {
        return books.getAuthorsByBook(isbn);
    }

    @GetMapping({Const.PUBLISHERS + "/{name}"})
    public Iterable<Publisher> getPublishers(@PathVariable String name) {
        return books.getPublishersByAuthor(name);
    }

}
