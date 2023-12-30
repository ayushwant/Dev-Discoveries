package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

public record Author(String id, String firstName, String lastName) implements Tables {

    private static List<Author> Authors = Arrays.asList(
            new Author("author-1", "Joshua", "Bloch"),
            new Author("author-2", "Douglas", "Adams"),
            new Author("author-3", "Bill", "Bryson")
    );

    public static Author getById(String id) {
        return Authors.stream()
                .filter(Author -> Author.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}