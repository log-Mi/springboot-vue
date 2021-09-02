<template>
    <div style="width: 100%; height :100vh; background-color: skyblue; overflow: hidden">
        <div style="width: 400px; margin:150px auto">
            <div style="color: #fff; fontsize: 30px; text-align: center; padding: 30px 0">
                注册
            </div>
            <el-form ref="form" :model="form" size="normal" :rules="rules">
                <el-form-item prop="staffName">
                    <el-input prefix-icon="el-icon-user-solid" v-model="form.staffName" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item prop="confirm">
                    <el-input prefix-icon="el-icon-lock" v-model="form.confirm" show-password placeholder="请确认密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%" type="primary" @click="register">注 册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import request from "@/utils/request"

export default {
    name: "Register",
    data(){
        return {
            form:{},
            rules: {
                staffName: [
                    {required: true, message: '请输入用户名', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'}
                ],
                confirm: [
                    {required: true, message: '请确认密码', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        register(){console.log(1);
            this.$refs['form'].validate((valid) => {
                
                if (valid) {
                    if (this.form.password !== this.form.confirm){
                        this.$message({
                                type: "error",
                                message: "两次输入密码不一致"
                            })
                        return
                    }
                    request.post("http://localhost:8081/staff/register", this.form).then(res => {
                        // console.log(res);
                        if (res.code === '0'){
                            this.$message({
                                type: "success",
                                message: "注册成功"
                            })
                            this.$router.push('/login')
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