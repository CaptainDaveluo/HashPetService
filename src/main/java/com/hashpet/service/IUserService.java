package com.hashpet.service;

import com.hashpet.pojo.User;

public interface IUserService {
    public void newUser(User user);
    public User getUserById(Integer userId);
    public void deleteUser(User user);
    public void updateUser(User user);
    public int isUserExist(String phoneNum);
    public User chechUser(User user);
    public void RegistSaller(User user);
}
