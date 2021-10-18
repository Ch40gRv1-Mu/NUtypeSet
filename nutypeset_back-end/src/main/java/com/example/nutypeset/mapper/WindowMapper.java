package com.example.nutypeset.mapper;

import com.example.nutypeset.model.entity.Window;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WindowMapper {
    Window  find(Window window);
    int add(Window user);
    int update(Window window);
}
