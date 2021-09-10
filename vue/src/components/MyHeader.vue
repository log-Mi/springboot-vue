<template>
    <div class="my-header">
        <div class="title">花店管理系统</div>
        <div style="flex: 1"></div>
        <div style="width: 100px">
            <el-dropdown style="padding-top: 30px">
              <span class="el-dropdown-link" style="color: #fff">
                {{staffName}}
                <i class="el-icon-arrow-down el-icon--right"></i>
              </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item>个人中心</el-dropdown-item>
                        <el-dropdown-item @click="logout">退出账号</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
    import router from '../router'
    export default {
        name: 'MyHeader',
        computed:{
            staffName(){
                let tmp = JSON.parse(sessionStorage.getItem('staff'))
                if (!tmp){
                    router.push('/login')
                }
                // console.log(tmp);
                return tmp.staffName
            }
        },
        methods: {
            logout(){
                this.$router.push('/login')
            }
        },
    }
</script>

<style lang="css">
    .my-header{
        height: 100px; 
        line-height: 50px; 
        border-bottom: 1px solid #ccc; 
        display: flex; 
        background-color: #242f42;
    }
    .my-header .title{
        width: 200px; 
        padding-left: 50px; 
        padding-top: 20px; 
        font-weight: bold; 
        color: #fff; 
        font-size: 24px
    }
</style>