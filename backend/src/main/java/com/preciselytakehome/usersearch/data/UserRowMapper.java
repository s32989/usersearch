package com.preciselytakehome.usersearch.data;

import com.preciselytakehome.usersearch.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<List<User>> {
    @Override
    public List<User> mapRow(ResultSet resultSet, int i) throws SQLException {
        List<User> userList = new ArrayList<>();
        do {
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String email = resultSet.getString("email");

            User user = new User(firstname, lastname, email);
            userList.add(user);
        }
        while (resultSet.next());
        return userList;


    }

}
