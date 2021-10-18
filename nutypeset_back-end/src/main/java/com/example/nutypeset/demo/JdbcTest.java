package com.example.nutypeset.demo;

import com.example.nutypeset.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8"
                    ,"root","123456");

            String sql = "select * from user";
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);

            List<User> list = new ArrayList<>();
            while ( rs.next()) {
                User user = new User();

                int id =rs.getInt("id");
                String username = rs.getString("username");
                String password= rs.getString("password");

                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);

                list.add(user);
            }

            for(User user: list) {
                System.out.println(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stat != null) {
                        stat.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }

    }
}
