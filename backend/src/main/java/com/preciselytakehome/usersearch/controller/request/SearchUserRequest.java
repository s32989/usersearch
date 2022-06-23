package com.preciselytakehome.usersearch.controller.request;

public class SearchUserRequest {
    private String searchTerm;

    public SearchUserRequest(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    public SearchUserRequest(){

    }

    public String getSearchTerm() {
        return searchTerm;
    }
}
