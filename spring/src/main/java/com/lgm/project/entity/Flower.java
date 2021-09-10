package com.lgm.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("flower")
@Data
public class Flower {
    @TableId(value = "flower_id", type = IdType.AUTO)
    private Integer flowerId;

    private String flowerName;

    private BigDecimal flowerPrice;

    private Integer staffId;

    private String flowerStock;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    private Integer provId;

}
