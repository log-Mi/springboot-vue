package com.lgm.project.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgm.project.common.Result;
import com.lgm.project.entity.Staff;
import com.lgm.project.mapper.StaffMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

@CrossOrigin//跨域
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Resource
    StaffMapper staffMapper;


    @PostMapping("/register")
    public Result<?> register(@RequestBody Staff staff){
        Staff res = staffMapper.selectOne(Wrappers.<Staff>lambdaQuery().eq(Staff::getStaffName, staff.getStaffName()));
        if (res != null)
        {
            return Result.error("-1", "用户名重复");
        }
        if (staff.getPassword() == null)
        {
            staff.setPassword("12345");
        }
        staffMapper.insert(staff);
        return Result.success();
    }


    //登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody Staff staff){
        Staff res = staffMapper.selectOne(Wrappers.<Staff>lambdaQuery().eq(Staff::getStaffName, staff.getStaffName()).eq(Staff::getPassword, staff.getPassword()));
        if (res == null)
        {
            return Result.error("-1", "用户名或密码错误");
        }
        return Result.success(res);
    }

    //新增
    @PostMapping
    public Result<?> save(@RequestBody Staff staff){
        if (staff.getPassword() == null)
        {
            staff.setPassword("123456");
        }
        staffMapper.insert(staff);
        return Result.success();
    }

    //修改
    @PutMapping
    public Result<?> update(@RequestBody Staff staff){
        staffMapper.updateById(staff);
        return Result.success();
    }

    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search)
    {
        LambdaQueryWrapper<Staff> wrapper = Wrappers.<Staff>lambdaQuery();
        if (StrUtil.isNotBlank(search))
        {
            wrapper.like(Staff::getStaffName, search);
        }
        Page<Staff> staffPage = staffMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(staffPage);
    }

    //删除
    @DeleteMapping("/{staffId}")
    public Result<?> delete(@PathVariable Long staffId){
        staffMapper.deleteById(staffId);
        return Result.success();
    }
}
