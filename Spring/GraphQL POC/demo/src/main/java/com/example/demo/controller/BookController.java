package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Employee;
import com.example.demo.model.Tables;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }

    @QueryMapping
    public List<Tables> getData(){
        return List.of(new Author("author-1", "Joshua", "Bloch"),
                new Book("book-1", "Effective Java", 416, "author-1"),
                new Employee()
        );
    }
}