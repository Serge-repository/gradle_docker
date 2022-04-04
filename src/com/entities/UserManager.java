package com.entities;

public enum UserManager {
    ADMIN("I am admin", "abc123", "123xyz"),
    MANAGER("I am manager", "manager_login", "manager_password"),
    BUYER("I am buyer", "buyer_login", "buyer_password");

    String messageForUser;
    String login;
    String password;

    UserManager(String messageForUser, String login, String password) {
        this.messageForUser = messageForUser;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
