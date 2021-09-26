package cn.it.service.impl;

import cn.it.entity.User;
import cn.it.mapper.UserMapper;
import cn.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luis
 * @date 2021/9/19 16:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUser() {
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public User insertUser(User user) {
        return null;
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        return userMapper.getUserByNameOrEmail(str);
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
