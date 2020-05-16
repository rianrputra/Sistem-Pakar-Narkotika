package com.rianprojek.sistempakarnarkotika.Model;

import java.util.List;

/**
 * Created by Rian on 10/09/2018.
 */

public class ResponseUser {
    private static String username;
    private static String email;

    String error,message;
    Boolean result;
    List<ModelPengguna> data;


    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getResult() {
        return result;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ResponseUser.username = username;
    }

    public List<ModelPengguna> getData() {
        return data;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ResponseUser.email = email;
    }
}
