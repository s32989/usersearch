package com.preciselytakehome.usersearch.data;
import com.preciselytakehome.usersearch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public boolean registerUser(String firstName, String lastName, String email){
        try {
            jdbcTemplate.update(SQLTemplates.registerUser, firstName, lastName, email);
            return true;
        }catch(DataAccessException e){
            return false;
        }
    }

    public List<User> searchUser(String searchTerm){
        List<User> users;
        users = jdbcTemplate.queryForObject(SQLTemplates.searchUser, new UserRowMapper(), searchTerm, searchTerm, searchTerm);
        return users;
    }

}
