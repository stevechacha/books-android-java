package com.example.mybooks;

class Book {
    public String id;
    public String title;
    public String subTitle;

    public Book(String id, String title, String subTitle, String[] authors, String publisher, String publishedDate) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    public String [] authors;
    public String publisher;
    public String publishedDate;
}
