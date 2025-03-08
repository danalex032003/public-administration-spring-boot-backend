package com.example.springbootapp.dto;

import com.example.springbootapp.model.user.User;
import com.example.springbootapp.util.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

    private Integer id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private Date createdAt;

    private Date updatedAt;

    private RoleEnum role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.role = user.getRole();
    }
}
