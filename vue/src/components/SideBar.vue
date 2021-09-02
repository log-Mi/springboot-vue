<template>
    <div>
        <el-menu
                style="width: 200px; min-height: calc(100vh - 50px)"
                :default-active="path"
                router
                class="el-menu-vertical-demo">
            <el-sub-menu index="1">
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


    export default {
        name: 'SideBar',
        data(){
            return {
                path: this.$route.path
            }
        }
    }
</script>
