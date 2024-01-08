package com.example.taskManagement.Service;

import com.example.taskManagement.Model.User;

public interface UserService {

    public User addUser(User user) ;

    public User getUserById(int id);

}
