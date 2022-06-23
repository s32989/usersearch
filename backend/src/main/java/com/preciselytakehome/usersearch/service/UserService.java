package com.preciselytakehome.usersearch.service;

import com.preciselytakehome.usersearch.controller.request.RegisterUserRequest;
import com.preciselytakehome.usersearch.controller.request.SearchUserRequest;
import com.preciselytakehome.usersearch.data.UserDAO;
import com.preciselytakehome.usersearch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public boolean registerUser(RegisterUserRequest request){
        //ideally would like to validate request before reaching database
        return userDAO.registerUser(request.getFirstName(), request.getLastName(), request.getEmail());
    }
    public List<User> searchUser(SearchUserRequest request){
        return userDAO.searchUser(request.getSearchTerm());
    }
}
