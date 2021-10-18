package com.example.nutypeset.service;
import com.example.nutypeset.model.entity.User;

import java.util.List;
import java.util.Map;
public interface UserService {
    public List<User> getAll();
    User findByEmail( String email);
    int save(Map<String,String> userInfo);
    String findByEmailAndPassword(String email,String password);
    User findByUserId(Integer userId);
    int updateLatexInformation(Integer id,String pdfsrc,String latexFinalCode);
}
