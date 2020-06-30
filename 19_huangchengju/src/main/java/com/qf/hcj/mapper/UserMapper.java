package com.qf.hcj.mapper;

import com.qf.hcj.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    public boolean regist(UserEntity ue) ;

    public UserEntity queryname(String name);

    public UserEntity queryemail(String email);

    public UserEntity login(@Param("name") String name, @Param("password")String password);

    public List<UserEntity> findUser();

    public boolean delete(int id);

    public boolean update(UserEntity ue);

    public UserEntity queryId(int id);

}
