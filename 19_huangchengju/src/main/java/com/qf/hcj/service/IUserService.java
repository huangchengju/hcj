package com.qf.hcj.service;

import com.qf.hcj.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserService {

    public boolean regist(UserEntity ue);

    public UserEntity queryname(String name);

    public UserEntity queryemail(String email);

    public UserEntity login(String name,String password);

    public List<UserEntity> findUser();

    public boolean delete(int id);

    public boolean update(UserEntity ue);

    public UserEntity queryId(int id);

}
