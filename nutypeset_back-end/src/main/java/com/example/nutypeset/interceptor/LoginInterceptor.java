package com.example.nutypeset.interceptor;




import com.example.nutypeset.utils.JWTUtils;
import com.example.nutypeset.utils.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    /***
     * Method before entering controller
     * AOP
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {

            String accessToken = request.getHeader("token");
            System.out.println( request.getHeader("token"));
            if (accessToken == null) {
                accessToken = request.getParameter("token");
            }

            if (StringUtils.isNotBlank(accessToken)) {
                Claims claims = JWTUtils.checkJWT(accessToken);
                if (claims == null) {
                    //expired
                    sendJsonMessage(response, JsonData.buildError("Login expired, login in again"));

                    return false;

                }
                Integer id = (Integer) claims.get("id");
                String username = (String) claims.get("username");

                request.setAttribute("id", id);
                request.setAttribute("username", username);
                return true;

            }
        }catch (Exception e){
            e.printStackTrace();
            // login fail
        }
        sendJsonMessage(response, JsonData.buildError("Login fail, login in again"));
        return false;

    }

    public static void sendJsonMessage(HttpServletResponse response, Object obj)
    {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer= response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
