<template>
    <div style="width: 100%; height :100vh; background-color: skyblue; overflow: hidden">
        <div style="width: 400px; margin:150px auto">
            <div style="color: #fff; fontsize: 30px; text-align: center; padding: 30px 0">
                欢迎使用花店管理系统
            </div>
            <el-form ref="form" :model="form" size="normal" :rules="rules">
                <el-form-item prop="staffName">
                    <el-input prefix-icon="el-icon-user-solid" v-model="form.staffName" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import request from "@/utils/request"

export default {
    name: "Login",
    data(){
        return {
            form:{},
            rules: {
                staffName: [
                    {required: true, message: '请输入用户名', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'}
                ]
            }
        }
    },
    created() {
        sessionStorage.removeItem('staff')
    },  
    methods: {
        login(){
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    request.post("http://localhost:8081/staff/login", this.form).then(res => {
                        // console.log(res);
                        if (res.code === '0'){
                            this.$message({
                                type: "success",
                                message: "登录成功"
                            })
                            sessionStorage.setItem('staff', JSON.stringify(res.data))
                            console.log(res.data);
                            this.$router.push('/flower')
                        }else{
                        this.$message({
                            type: "error",
                            message: res.msg
                            })
                        }
                    })
                }
            })
                    
        }
    },
}
</script>

<style>

</style>