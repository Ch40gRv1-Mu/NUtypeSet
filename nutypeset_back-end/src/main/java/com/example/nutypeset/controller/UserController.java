package com.example.nutypeset.controller;

import com.example.nutypeset.model.entity.User;
import com.example.nutypeset.model.request.LoginRequest;
import com.example.nutypeset.model.request.UpdateLatexRequest;
import com.example.nutypeset.utils.JsonData;
import com.example.nutypeset.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import static java.lang.System.*;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public JsonData register (@RequestBody Map<String,String> userInfo) throws IOException {
        System.out.println( userInfo.get("username"));
        int rows = userService.save(userInfo);
        //System.out.println( rows);
       // System.out.println( userInfo.get("username"));
        return  rows == 1? JsonData.buildSuccess():JsonData.buildError("Register fail, please re" +
                "try again or use another email(*▔＾▔*)");
        //response.getWriter().print(rows == 1? JsonData.buildSuccess():JsonData.buildError("Register fail, please retry (*▔＾▔*)"));
    }


    @PostMapping("login")
    public  JsonData login( @RequestBody  LoginRequest loginRequest) throws IOException {
        //System.out.println(loginRequest);
        String token= userService.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());
        //System.out.println("token");
        //System.out.println(token);
        if (token!=null)
        {
            //response.getWriter().print(JsonData.buildSuccess(token));
            return JsonData.buildSuccess(token);
        }else{
           // response.getWriter().print("Login Fail, account/password is wrong");
            return JsonData.buildError("Login Fail, account/password is wrong");
        }

    }



    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){

        Integer id =(Integer) request.getAttribute("id");
        System.out.println("id");
        System.out.println(id);

        if (id == null){
            return JsonData.buildError("query fail");
        }

        User user = userService.findByUserId(id);
        System.out.println(user);
        return JsonData.buildSuccess(user);
    }

    @PostMapping("update_pdfsrc")
    public JsonData updatePdfsrc(HttpServletRequest request, @RequestBody UpdateLatexRequest updateLatexRequest){

        Integer id = (Integer) request.getAttribute("id");
        System.out.println("id");
        if (id == null){
            return JsonData.buildError("query fail");
        }
        String pdfsrc = updateLatexRequest.getPdfsrc();
        String latexFinalCode = updateLatexRequest.getLatexFinalCode();
        System.out.println(pdfsrc);


        int rows = userService.updateLatexInformation(id,pdfsrc,latexFinalCode);

        return rows == 1? JsonData.buildSuccess():JsonData.buildError("Update fail (*▔＾▔*)");
    }


    @RequestMapping("test")
    public void test(HttpServletResponse response)throws IOException {
        response.getWriter().print("Let Me try");
    }

    

    @RequestMapping("all")
    @ResponseBody
    public List<User> all() {
        return userService.getAll();
    }

}

