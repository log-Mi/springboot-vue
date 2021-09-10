package com.lgm.project.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("prov_info")
@Data
public class ProvInfo {
    @TableId(value = "staff_id", type = IdType.AUTO)
    private Integer provId;

    private String provName;

    private String provTel;

    private String provAddress;
}
