package main.java.com.library.model;

import java.util.HashSet;
import java.util.Set;

public class Book {
    private long id;

    private String name;

    private Set<Author> authors = new HashSet();

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set getAuthors() {
        return authors;
    }

    public String getName() {
        return name;
    }

   // public void addAuthor(Author author) {
  //      this.authors.add(author);
  //  }

    public Book() {

    }

    public Book(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return (int)id;
    }
}
