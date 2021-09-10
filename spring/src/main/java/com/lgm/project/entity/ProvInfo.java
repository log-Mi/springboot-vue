package com.lgm.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("prov_info")
@Data
public class ProvInfo {
    private Integer provId;

    private String provName;

    private String provTel;

    private String provAddress;
}
