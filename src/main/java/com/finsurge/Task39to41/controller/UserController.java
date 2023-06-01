package com.finsurge.Task39to41.controller;
import com.finsurge.Task39to41.dto.UserDTO;
import com.finsurge.Task39to41.model.User;
import com.finsurge.Task39to41.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserController(UserRepository userRepository,MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/count")
    public long getTotalDocumentCount() {
        return userRepository.count();
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/saveUser")
    public String saveProject(@RequestBody User user) {
        userRepository.save(user);
        return "User Added Successfully";
    }

    @GetMapping("read/{id}")
    public UserDTO returnUser(@PathVariable String id){
        User user=userRepository.findById(id).get();
        UserDTO userdto=new UserDTO();
        userdto.setName(user.getName());
        return userdto;
    }

    @GetMapping("/readField/{fieldName}")
    public List<Object> getFieldValues(@PathVariable String fieldName) {
        List <Object> list=mongoTemplate.query(User.class)
                .distinct(fieldName)
                .as(Object.class)
                .all();
        if(list.isEmpty())
        {
            list.add("Sorry There is no particular field in this collection");
        }
        return list;
    }
}
