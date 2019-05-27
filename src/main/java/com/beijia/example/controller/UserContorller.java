package com.beijia.example.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.beijia.example.entity.User;
import com.beijia.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserContorller {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Page usersList(){
        return userService.selectPage(new Page<User>());
    }

    @RequestMapping(value = "/update" ,method = RequestMethod.PUT)
    public String updateUser(User user){
        if(userService.updateById(user)){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User selectUserById(@PathVariable long id){
        return userService.selectById(id);
    }

    @RequestMapping(value ="/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id){
        userService.deleteById(id);
        return "删除成功";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveUser(User user){
        if(userService.insert(user)){
            return "新增成功";
        }else{
            return "新增失败";
        }
    }
}
