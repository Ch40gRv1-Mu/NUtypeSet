package com.example.nutypeset.service.impl;

import com.example.nutypeset.mapper.UserMapper;
import com.example.nutypeset.model.entity.User;
import com.example.nutypeset.service.UserService;
import com.example.nutypeset.utils.CommonUtils;
import com.example.nutypeset.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public List<User> getAll() {

        return userMapper.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public int save(Map<String,String> userInfo){

       User user = parseToUser(userInfo);
       if (user != null){
           try{return userMapper.save(user);

           }catch (Exception e) {
               e.printStackTrace();
            return -1;
       }



    }
       else{
           return -1;
       }

    }

    private User parseToUser(Map<String,String> userInfo){
        if (userInfo.containsKey("email")&&userInfo.containsKey("password")){
            User user= new User();
            user.setUsername(userInfo.get("username"));
            //hash the password for storage
            user.setPassword(CommonUtils.String2SHA256(userInfo.get("password")));
            user.setPhone(userInfo.get("phone"));
            user.setEmail(userInfo.get("email"));
            return user;


        }
        return null;
    }

    @Override
    public String findByEmailAndPassword(String email, String  password){
        //System.out.println("findByEmailAndPassword");
        User user = userMapper.findByEmailAndPassword(email,CommonUtils.String2SHA256(password));
        if (user == null) {
            return null;
        }else{


            String token = JWTUtils.geneJsonWebToken(user);
            //System.out.println("findByEmailAndPassword token");
            //System.out.println(token);
            return token;
        }

    }

    @Override
    public User findByUserId(Integer id){
        User user = userMapper.findByUserId(id);
        user.setPassword("");
        user.setId(-1);
        return user;

    }

    @Override
    public int updateLatexInformation(Integer id,String pdfsrc,String latexFinalCode){
        int result = userMapper.updateLatexInformation(id, pdfsrc, latexFinalCode);
        return result;
    }


}
