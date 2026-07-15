package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String authorName;
    private int publishYear;
    private String publisher;
    private String genre;
    private int pageCount;

    public Book() {
        this.title = "Unknown";
        this.authorName = "Unknown";
        this.publishYear = 0;
        this.publisher = "Unknown";
        this.genre = "Unspecified";
        this.pageCount = 0;
    }

    public void updateData(String title, String authorName, int publishYear, String publisher, String genre, int pageCount) {
        this.title = title;
        this.authorName = authorName;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.genre = genre;
        this.pageCount = pageCount;
    }
}