package com.preciselytakehome.usersearch.data;

public class SQLTemplates {

    public static final String registerUser = "INSERT INTO user_account (firstname, lastname, email) VALUES (?, ?, ?)";
    public static final String searchUser = "SELECT firstname, lastname, email FROM user_account WHERE firstname LIKE ('%' || ? || '%') OR lastname LIKE ('%' || ? || '%') OR email LIKE ('%' || ? || '%')";
}
