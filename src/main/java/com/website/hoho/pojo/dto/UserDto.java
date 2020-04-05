package com.website.hoho.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String username;
    private String password;
    private String permission;
}
