package com.library.web.controllers;

public class Validate {
    public static String checkRegUser(String userName, String pass) {
        boolean regUser = false;
        String adminUser = "admin";
        String readUser = "reader";
        String unknUser = "unkn";
        String adminPass = "123456";
        String readPass = "0000";

        if(userName.equals(adminUser)) {
            if(pass.equals(adminPass)){
                return adminUser;
            } else {
                return unknUser;
            }
        } else if(userName.equals(readUser)) {
            if(pass.equals(readPass)) {
                return readUser;
            } else {
                return unknUser;
            }
        } else {
            return unknUser;
        }
    }
}
