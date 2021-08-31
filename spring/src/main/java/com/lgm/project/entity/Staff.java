package com.lgm.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("staff")
@Data
public class Staff {
    @TableId(value = "staff_id", type = IdType.AUTO)
    private Integer staffId;

    private  String staffName;

    private String password;

    private Integer age;

    private String sex;

}
