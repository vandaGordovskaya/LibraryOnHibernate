package main.java.com.library.model;

import java.util.HashSet;
import java.util.Set;

public class Author {
    private long id;

    private String name;

    private Set books = new HashSet();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getBooks() {
        return books;
    }

    public void setBooks(Set books) {
        this.books = books;
    }

    //public void addBook(Book book) {
    //    this.books.add(book);
   // }

    public Author() {

    }

    public Author(String name) {
        this.name = name;
    }

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return id == author.id;
    }

    @Override
    public int hashCode() {
        return (int)id;
    }
}
