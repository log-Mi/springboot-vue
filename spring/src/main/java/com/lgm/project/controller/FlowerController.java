package com.lgm.project.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgm.project.common.Result;
import com.lgm.project.entity.Flower;
import com.lgm.project.entity.Flower;
import com.lgm.project.mapper.FlowerMapper;
import com.lgm.project.mapper.FlowerMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin//跨域
@RestController
@RequestMapping("/flower")
public class FlowerController {
    @Resource
    FlowerMapper flowerMapper;


    //新增
    @PostMapping
    public Result<?> save(@RequestBody Flower flower){
        flowerMapper.insert(flower);
        return Result.success();
    }

    //修改
    @PutMapping
    public Result<?> update(@RequestBody Flower flower){
        flowerMapper.updateById(flower);
        return Result.success();
    }

    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search)
    {
        LambdaQueryWrapper<Flower> wrapper = Wrappers.<Flower>lambdaQuery();
        if (StrUtil.isNotBlank(search))
        {
            wrapper.like(Flower::getFlowerName, search);
        }
        Page<Flower> flowerPage = flowerMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(flowerPage);
    }

    //删除
    @DeleteMapping("/{flowerId}")
    public Result<?> delete(@PathVariable Long flowerId){
        flowerMapper.deleteById(flowerId);
        return Result.success();
    }
}
