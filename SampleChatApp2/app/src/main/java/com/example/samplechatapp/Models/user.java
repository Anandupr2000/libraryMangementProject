package com.example.samplechatapp.Models;


import com.example.samplechatapp.databinding.ActivityProfileBinding;

public class user {
    private  String name,uID,email,profileImage,phnNo,uclass;
    ActivityProfileBinding binding;
//    for working with firebase empty constructor is required
public user(){
    }

    public user(String uname, String id,String uemail,String profilePic) {
        name = uname;
        uID = id;
        profileImage = profilePic;
        email = uemail;
    }

    public String getUclass() {
        return uclass;
    }

    public void setUclass(String uclass) {
        this.uclass = uclass;
    }

    public String getPhnNo() {
        return phnNo;
    }

    public void setPhnNo(String phnNo) {
        this.phnNo = phnNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
