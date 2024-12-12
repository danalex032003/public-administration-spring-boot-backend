package com.example.springbootapp.util;

import com.example.springbootapp.model.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.AccessDeniedException;

public class UserValidatorUtil {
    public static Boolean validateUser(User user) throws AccessDeniedException {
        UserDetails authenticatedUser = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return authenticatedUser.getUsername().equals(user.getUsername());
    }
}
