package com.entities;

public class UserFactory {
    public static UserManager currentlySelectedUser = UserManager.ADMIN; //по дефолту ставим админа
    public static boolean isUserTheSame;

    public static void chooseCurrentUser(UserManager user) {
        if (currentlySelectedUser==null || currentlySelectedUser != user){
            currentlySelectedUser = user;
            isUserTheSame = false;
        } else {
            isUserTheSame = true;
        }
    }

}
