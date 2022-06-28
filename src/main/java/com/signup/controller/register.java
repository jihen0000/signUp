package com.signup.controller;

import org.json.simple.JSONObject;

import com.signup.repository.UserRespository;
import com.signup.security.AppSecurityConfig;
import com.signup.service.UserService;
import com.signup.modele.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins="*")
@RestController

public class register {
    @Autowired
    private UserRespository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public  String addNewUser (@RequestBody MyUser user) {

        MyUser u = userService.findUserByUserName(user.getUsername());
        if(u==null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "200";
        }


        return "400";

    }
  
    @GetMapping("/register/all")
    public List<MyUser> getAll(){
        return userRepository.findAll();
    }
    @DeleteMapping("/cancel/{username}")
    public List<MyUser> cancelRegestration(@PathVariable String username){
    	userRepository.deleteById(username);
    	return userRepository.findAll();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @GetMapping("/user")
    public JSONObject getUser(@RequestHeader(value="Authorization") String jwt){
    	JSONObject content= new JSONObject();
        jwt=jwt.replaceFirst("Bearer","");
        MyUser user = userService.findUserByUserName(AppSecurityConfig.decodeJWT(jwt).getSubject());
        user.setPassword(null);
        System.out.println(jwt);
        System.out.println(user);
        content.put("user", user);
        return content;
    }




}
