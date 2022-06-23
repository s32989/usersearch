package com.preciselytakehome.usersearch.controller;

import com.preciselytakehome.usersearch.controller.request.RegisterUserRequest;
import com.preciselytakehome.usersearch.controller.request.SearchUserRequest;
import com.preciselytakehome.usersearch.model.User;
import com.preciselytakehome.usersearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class UserSearchController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> test(){
        HashMap<String, String> body = new HashMap<>();
        body.put("hello","world");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request){
        HashMap<String, String> body = new HashMap<>();
        if(userService.registerUser(request)) {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchUser(@RequestBody SearchUserRequest request){
        List<User> resp = userService.searchUser(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
