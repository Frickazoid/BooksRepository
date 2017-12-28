package tel_ran.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher implements Serializable {

    @Id
    String publisherName;
    @OneToMany(mappedBy = "publisher")
    @JsonIgnore
    Set<Book> books;

    public Publisher() {
    }

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
        books = new HashSet<>();
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        return publisherName != null ? publisherName.equals(publisher.publisherName) : publisher.publisherName == null;
    }

    @Override
    public int hashCode() {
        return publisherName != null ? publisherName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherName='" + publisherName + '\'' +
                '}';
    }
}
