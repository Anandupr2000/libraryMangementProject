package com.example.samplechatapp.Models;


import android.net.Uri;

public class utils {
    private static String name, uID, email,isPic,PhoneNo;
    private static Uri profileImage;
    private static utils instance;



    //    for working with firebase empty constructor is required

    public utils() {
    }


    private utils(String name, String uID, String email, Uri profileImage) {
        this.name = name;
        this.uID = uID;
        this.profileImage = profileImage;
        this.email = email;
    }


    public static synchronized utils getInstance() {
        if (instance == null) {
            instance = new utils();
            return instance;
        } else {
            return instance;
        }
    }

    public static String getPhoneNo() {
        return PhoneNo;
    }

    public static void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public static String getIsPic() {
        return isPic;
    }

    public static void setIsPic(String isPic) {
        utils.isPic = isPic;
    }

    public static synchronized void setInstance(utils instance) {
        utils.instance = instance;
    }

    public static void setUser(utils u) {
        name = u.name;
        uID = u.uID;
        profileImage = u.profileImage;
        email = u.email;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String e) {
        email = e;
    }

    public static String getName() {
        return name;
    }

    public static String getuID() {
        return uID;
    }

    public static void setuID(String ID) {
        uID = ID;
    }

    public static void setName(String uname) {
        name = uname;
    }

    public static Uri getProfileImage() {
        return profileImage;
    }

    public static void setProfileImage(Uri profileImg) {
        profileImage = profileImg;
    }
}
