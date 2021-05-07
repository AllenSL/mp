package com.mx.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String mail;
    private String password;
    private String description;
    private String updateTime;
    private String createTime;
}
