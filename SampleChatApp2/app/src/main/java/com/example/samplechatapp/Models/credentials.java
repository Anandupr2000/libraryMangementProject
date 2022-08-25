package com.example.samplechatapp.Models;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class credentials {
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String STUDENT_NAME_KEY = "studentfullname";
    private static final String STUDENT_CLASS_KEY = "studentclass";
    private static final String UID_KEY = "UID";
    private static String ID;
    private static DocumentReference documentReference;
    private static CollectionReference collectionRefenence;
    private static SharedPreferences sharedPreferences;
    private static String KEY = "logindata";
    private Gson gson = new Gson();//object for creating json file
    private static credentials instance;

    public static synchronized credentials getInstance(Context context) {
        if (instance == null) {
            instance = new credentials(context);
        }
        return instance;
    }

    public static synchronized credentials setInstance(Context context, String uid) {
        if (instance == null) {
            System.out.println("Writing data to storage");
            instance = new credentials(context,uid);
        }
        return instance;
    }

    public static CollectionReference getCollectionRefenence() {
        collectionRefenence = FirebaseFirestore.getInstance().collection("users");
        return collectionRefenence;
    }

    public credentials(Context context) {
//        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    private credentials(Context context, String uid) {

        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        System.out.println("sharedPreferences.getAll() => "+sharedPreferences.getAll());
        if (getLoginData() == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY, gson.toJson(uid));
            editor.commit();
            System.out.println("data sucessfully written in to storage");
        }

    }

    public String getLoginData() {
        return getSharedPreferences("logindata");
    }

    private String getSharedPreferences(String KEY) {
        Type type = new TypeToken<String>() {
        }.getType();
        String ldata = gson.fromJson(sharedPreferences.getString(KEY, null), type);
        return ldata;
    }

    public static String getID() {
        return ID;
    }

    public static String getUidKey() {
        return UID_KEY;
    }

    public static void setID(String ID) {
        credentials.ID = ID;
    }

    public static String getStudentClassKey() {
        return STUDENT_CLASS_KEY;
    }

    public static String getStudentNameKey() {
        return STUDENT_NAME_KEY;
    }

    public static String getEMAIL_KEY() {
        return EMAIL_KEY;
    }

    public static String getPASSWORD_KEY() {
        return PASSWORD_KEY;
    }

    public static DocumentReference getDocumentReference(String userName) {
        documentReference = FirebaseFirestore.getInstance().document("users/" + userName + "/");
        return documentReference;
    }
}

