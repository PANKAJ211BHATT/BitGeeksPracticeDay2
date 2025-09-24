package com.bitGeeksPracticeday2.demo.Controller;


import com.bitGeeksPracticeday2.demo.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    public List<UserModel> users = new ArrayList<>();

    @GetMapping
    public List<UserModel> getUsers(@RequestParam(defaultValue = "") String name){
        if(name.isEmpty()){
            return users;
        }
        return users.stream().filter(UserModel -> UserModel.getName().equalsIgnoreCase(name))
                .toList();

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable int id) {
        if (id >= 0 && id < users.size()) {
            return ResponseEntity.ok(users.get(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserModel> CreateUser(@RequestBody UserModel user){
        users.add(user);
        return ResponseEntity.status(201).body(user);
    }
}
