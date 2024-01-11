package com.example.cencusdatacollectionapp;

public class DetailList {
    private static String name;
    private static String age;
    private static String gender;
    private static String photo;


    public DetailList(String name, String age, String gender, String photo){
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.photo=photo;
    }

    public static String getName(){

        return name;
    }

    public static String getAge(){
        return age;
    }
    public static String getGender(){
        return gender;
    }
    public static String getPhoto(){
        return photo;
    }
}