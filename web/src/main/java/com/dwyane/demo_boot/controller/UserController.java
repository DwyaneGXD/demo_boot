package com.dwyane.demo_boot.controller;

import com.dwyane.demo_boot.domain.User;
import com.dwyane.demo_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /**
     * 采用构造器的注入方式
     * 好处：1，不能修改；2，尽早的初始化
     */
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name){

        User user = new User();
        user.setName(name);
        if (userRepository.save(user)){
            System.out.printf("用户对象：%s 保存成功！", user);
        }


        return user;
    }
}
