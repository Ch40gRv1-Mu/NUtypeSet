package com.example.nutypeset.mapper;

import com.example.nutypeset.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    int add(User user);
    int save(User user);
    User findByEmail(@Param("email") String email);


    int update(@Param("id") Integer id,@Param("password") String password);
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    User findByUserId(@Param("id") Integer id);
    int updateLatexInformation(Integer id,String pdfsrc,String latexFinalCode);



}
