package com.website.hoho.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  {
    private Integer uid;
    private String username;
    private String password;
    private String email;
    private Integer authority;
    private Date createTime;
    private String permission;
}
