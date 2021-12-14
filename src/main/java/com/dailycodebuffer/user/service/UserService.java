package com.dailycodebuffer.user.service;

import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.repository.UserRepository;
import com.dailycodebuffer.user.valueObjects.Department;
import com.dailycodebuffer.user.valueObjects.ResponseTemplateValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        log.info("Inside saveUser method of UserService class");
        return userRepository.save(user);
    }

    public ResponseTemplateValueObject getUserWithDepartment(Long userId) {
        ResponseTemplateValueObject responseTemplateValueObject = new ResponseTemplateValueObject();
        User user = userRepository.findByUserId(userId);

        // call another microservice to get the department object for the user
        Department department = restTemplate.getForObject("http://department-service/departments/" + user.getDepartmentId(),
                Department.class);

        responseTemplateValueObject.setUser(user);
        responseTemplateValueObject.setDepartment(department);

        return responseTemplateValueObject;
    }
}
