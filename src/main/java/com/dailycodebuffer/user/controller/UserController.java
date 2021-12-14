package com.dailycodebuffer.user.controller;

import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.service.UserService;
import com.dailycodebuffer.user.valueObjects.ResponseTemplateValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser method of UserController class");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateValueObject getUserWithDepartment(@PathVariable(name = "id") Long userId) {
        log.info("Inside getUserWithDepartment method of UserController class");
        return userService.getUserWithDepartment(userId);
    }
}
