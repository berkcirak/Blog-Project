package com.example.WebSite.request;

import com.example.WebSite.entity.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateUserRequest(
        String username,
        String password,
        Set<Role> authorities) {


}
