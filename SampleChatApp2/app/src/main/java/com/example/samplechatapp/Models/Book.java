package com.example.samplechatapp.Models;

import java.util.ArrayList;

public class Book {
    //    public static int id;
//    private static String name;
//    private static String imageUrl;
//    private static String shortDesc;
//    private static String  desc;
    public int id;
    private String name;
    private String author;
    private String imageUrl;
    private String downloadUrl;
    private String shortDesc;
    private String desc;
    private String price;
    private boolean isFav = true;
    private String publishYear;

    private float isbn;
    private String category = "Nothing set";
    private static ArrayList<Book> booksArrayList = new ArrayList<Book>();
    private static int currentfragementid;


    public Book() {
    }

    public Book(int id, String name, String author, String imageUrl,
                String downloadUrl, String shortDesc, String desc, String price,
                String publishYear, float ISBN) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.imageUrl = imageUrl;
        this.downloadUrl = downloadUrl;
        this.shortDesc = shortDesc;
        this.desc = desc;
        this.price = price;
        this.publishYear = publishYear;
        this.isbn = ISBN;
    }
    public Book(int id, String name, String author, String imageUrl, String shortDesc, String desc) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.imageUrl = imageUrl;
        this.shortDesc = shortDesc;
        this.desc = desc;
    }


    public static int getCurrentfragementid() {
        return currentfragementid;
    }

    public static void setCurrentfragementid(int fragementid) {
        currentfragementid = fragementid;
    }

    private boolean isExpanded = false;
    public boolean getFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public static ArrayList<Book> getBooksArrayList() {
        return booksArrayList;
    }

    public static void setBooksArrayList(ArrayList<Book> arrayList) {
        Book.booksArrayList = arrayList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", author=" + author + ", imageUrl=" + imageUrl + ", downloadUrl=" + downloadUrl + ", shortDesc=" + shortDesc + ", desc=" + desc + '}';
    }

}
