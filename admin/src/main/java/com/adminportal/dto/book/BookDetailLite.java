package com.adminportal.dto.book;

import com.adminportal.domain.Book;


public class BookDetailLite {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private String language;
    private String category;
    private String numberOfPages;
    private String format;
    private int isbn;
    private double shippingWeight;
    private double listPrice;
    private double ourPrice;
    private boolean active= true;
    private String description;
    private int inStockNumber;



    public BookDetailLite(Book book){
        id =book.getId();
        title = book.getTitle();
        author =book.getAuthor();
        publisher = book.getPublisher();
        publicationDate = book.getPublicationDate();
        language = book.getLanguage();
        numberOfPages = book.getNumberOfPages();
        category =book.getCategory();
        format = book.getFormat();
        isbn = book.getIsbn();
        shippingWeight = book.getShippingWeight();
        listPrice = book.getListPrice();
        ourPrice = book.getOurPrice();
        active = book.isActive();
        description = book.getDescription();
        inStockNumber = book.getInStockNumber();

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInStockNumber() {
        return inStockNumber;
    }

    public void setInStockNumber(int inStockNumber) {
        this.inStockNumber = inStockNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public double getShippingWeight() {
        return shippingWeight;
    }

    public void setShippingWeight(double shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
