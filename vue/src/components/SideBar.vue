<template>
    <div>
        <el-menu
                style="width: 200px; min-height: calc(100vh - 50px)"
                :default-active="path"
                router
                class="el-menu-vertical-demo">
            <el-sub-menu index="1" v-if="staff.role === 0">
                <template #title><i class="el-icon-setting"></i>系统管理</template>
                <el-menu-item index="/staff">员工管理</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="/flower">
                <i class="el-icon-menu"></i>
                <template #title>花品管理</template>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    import request from '@/utils/request'

    export default {
        name: 'SideBar',
        data(){
            return {
                path: this.$route.path,
                staff: {}
            }
        },
        created(){
            //权限管理
            let staffStr = sessionStorage.getItem("staff") || '{}'
            
            this.staff = JSON.parse(staffStr)
            // console.log(this.staff.staffId);


            //请求服务端确认用户信息是否合法
            request.get("http://localhost:8081/staff/" + this.staff.staffId).then(res => {
                if (res.code === '0'){
                    this.staff = res.data
                }
            })
        }
    }
</script>
