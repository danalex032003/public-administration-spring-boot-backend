package com.example.springbootapp.controller;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.request.Request;
import com.example.springbootapp.model.request.RequestRequestDTO;
import com.example.springbootapp.model.user.User;
import com.example.springbootapp.service.DocumentService;
import com.example.springbootapp.service.RequestService;
import com.example.springbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private DocumentService documentService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println(user);
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return userService.verify(user);
    }

    @PostMapping("/muie")
    public String muie() {
        return "muie";
    }

    @PostMapping("/createrequest")
    public Request createRequest(
            @RequestBody RequestRequestDTO requestRequestDTO) {
        User user = userService.findById(requestRequestDTO.getUserId());
        Document document = documentService.findById(requestRequestDTO.getDocumentId());
        return requestService.create(user, document);
    }
}
