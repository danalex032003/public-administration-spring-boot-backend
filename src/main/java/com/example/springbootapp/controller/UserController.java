package com.example.springbootapp.controller;

import com.example.springbootapp.dto.UserDTO;
import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.request.Request;
import com.example.springbootapp.dto.RequestDTO;
import com.example.springbootapp.model.user.User;
import com.example.springbootapp.service.DocumentService;
import com.example.springbootapp.service.JwtService;
import com.example.springbootapp.service.RequestService;
import com.example.springbootapp.service.UserService;
import com.example.springbootapp.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String response = userService.verify(user);
        return ResponseEntity.ok(new ApiResponse(response));
    }

    @PostMapping("/createrequest")
    public Request createRequest(
            @RequestBody RequestDTO requestRequestDTO) {
        User user = userService.findById(requestRequestDTO.getUserId());
        Document document = documentService.findById(requestRequestDTO.getDocumentId());
        return requestService.create(user, document);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getLoggedInUser(@RequestHeader("Authorization") String authHeader) {
        // Extract JWT token from "Bearer <token>"
        String token = authHeader.replace("Bearer ", "");
        String username = jwtService.extractUsername(token);

        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(new UserDTO(user));
    }

    public User getLoggedInUser(HttpServletRequest request) {
        // Extract JWT from the Authorization header
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String jwt = authHeader.substring(7); // Remove "Bearer " prefix

        return userService.getLoggedInUser(jwt);
    }

    @GetMapping("/ok")
    public ResponseEntity<?> ok() {
        return ResponseEntity.ok("OK");
    }
}
