package com.example.samplechatapp.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class alllists {

    public static alllists instance;
    private static Book temp = new Book();
    private static ArrayList<Book> favList = new ArrayList<>();
    private static ArrayList<Book> allBookList = new ArrayList<>();
    private static ArrayList<Book> categoryArrayList = new ArrayList<>();
    private static Map<String ,ArrayList<Book>> userbooklist= new HashMap<>();


    public static synchronized alllists getInstance() {

        if (instance != null) {
            return instance;
        } else {
            instance = new alllists();
            return instance;
        }
    }

    private alllists() {
    }

    public static ArrayList<Book> getCategoryArrayList() {
        return categoryArrayList;
    }

    public static void setCategoryArrayList(ArrayList<Book> categoryArrayList) {
        alllists.categoryArrayList = categoryArrayList;
    }

    public static Book getTemp() {
        return temp;
    }

    public static void setTemp(Book temp) {
        alllists.temp = temp;
    }

    public static Map<String, ArrayList<Book>> getuserbooklist() {
        return userbooklist;
    }

    public static void setuserbooklist(Map<String,ArrayList<Book>> userbooklist) {
//        TODO : Make a HashMap and save the values there ,then convert the key set to arraylist to get list of user created booklist
        alllists.userbooklist = userbooklist;
    }

    public static Set<String> getuserbooklistKeys(){

        return userbooklist.keySet();
    }

//    public static Map<String, ArrayList<Book>> getOne(String s) {
//        return userbooklist;
//    }
    public static void addArrayList(String key,ArrayList<Book> value){
        userbooklist.put(key,value);
    }

    public static ArrayList<Book> getFavList() {
        return favList;
    }

    private static void setFavList(ArrayList<Book> favList) {
        alllists.favList = favList;
    }

    public static void addtoFav(Book fav) {
        favList.add(fav);
    }

    public static void removefromFav(Book fav) {
        favList.remove(fav);
    }

    public static void addBooktoAllBook(Book book) {
        allBookList.add(book);
    }

    private static void removeBookfromAllBook(Book book) {
        allBookList.remove(book);
    }

    public static ArrayList<Book> getAllBookList() {
        return allBookList;
    }

    public static void setAllBookList(ArrayList<Book> allBookList) {
        alllists.allBookList = allBookList;
    }
}
