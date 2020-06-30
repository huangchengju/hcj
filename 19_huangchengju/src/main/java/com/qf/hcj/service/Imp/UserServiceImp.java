package com.qf.hcj.service.Imp;

import com.qf.hcj.entity.UserEntity;
import com.qf.hcj.mapper.UserMapper;
import com.qf.hcj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    UserMapper userMapper;
    public boolean regist(UserEntity ue) {
        return userMapper.regist(ue);
    }

    public UserEntity queryname(String name) {
        return userMapper.queryname(name);
    }

    public UserEntity queryemail(String email) {
        return userMapper.queryemail(email);
    }

    public UserEntity login(String name, String password) {
        return userMapper.login(name,password);
    }

    public List<UserEntity> findUser() {
        return userMapper.findUser();
    }

    public boolean delete(int id) {
        return userMapper.delete(id);
    }

    public boolean update(UserEntity ue) {
        return userMapper.update(ue);
    }

    public UserEntity queryId(int id) {
        return userMapper.queryId(id);
    }


}
