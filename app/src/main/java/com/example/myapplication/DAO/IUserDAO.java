package com.example.myapplication.DAO;

import com.example.myapplication.Model.User;

public interface IUserDAO {
    Boolean login(String _userName, String _password);
    void insert(User user);
}
