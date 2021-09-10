package com.lgm.project.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgm.project.common.Result;
import com.lgm.project.entity.ProvInfo;
import com.lgm.project.mapper.ProvInfoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin//跨域
@RestController
@RequestMapping("/provInfo")
public class ProvInfoController {
    @Resource
    ProvInfoMapper provInfoMapper;


    //新增
    @PostMapping
    public Result<?> save(@RequestBody ProvInfo provInfo){
        provInfoMapper.insert(provInfo);
        return Result.success();
    }

    //修改
    @PutMapping
    public Result<?> update(@RequestBody ProvInfo provInfo){
        provInfoMapper.updateById(provInfo);
        return Result.success();
    }

    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search)
    {
        LambdaQueryWrapper<ProvInfo> wrapper = Wrappers.<ProvInfo>lambdaQuery();
        if (StrUtil.isNotBlank(search))
        {
            wrapper.like(ProvInfo::getProvName, search);
        }
        Page<ProvInfo> provInfoPage = provInfoMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(provInfoPage);
    }

    //删除
    @DeleteMapping("/{provInfoId}")
    public Result<?> delete(@PathVariable Long provInfoId){
        provInfoMapper.deleteById(provInfoId);
        return Result.success();
    }
}
