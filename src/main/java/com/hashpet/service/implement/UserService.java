package com.hashpet.service.implement;

import com.hashpet.dao.UserMapper;
import com.hashpet.pojo.User;
import com.hashpet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private UserMapper userDao;

    public UserMapper getUserDao() {
        return userDao;
    }

    public void setUserDao(UserMapper userDao) {
        this.userDao = userDao;
    }

    public void newUser(User user){
        userDao.insert(user);
    }


    public User getUserById(Integer userId){
        return  userDao.selectByPrimaryKey(userId);
    }

    public void deleteUser(User user) {

    }

    public void updateUser(User user) {

    }

    public User chechUser(User user) {
        return userDao.checkUser(user);
    }

    public int isUserExist(String phoneNum) {
        return userDao.isUserExist(phoneNum);
    }

    public void RegistSaller(User user) {
        user.setUsertype("1");
        userDao.updateByPrimaryKeySelective(user);
    }
}
