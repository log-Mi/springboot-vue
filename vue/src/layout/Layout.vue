<template>
    <div>
    <!-- 头部 -->
    <MyHeader />

    <!-- 主体部分 -->
    <div style="display: flex">

      <!-- 侧边栏 -->
      <SideBar/>

      <!-- 内容区域 -->
      <router-view style="flex:1"/>

    </div>
  </div>
</template>

<script>
import MyHeader from '@/components/MyHeader'
import SideBar from '@/components/SideBar'
export default {
    name: 'App',
    components: {
        MyHeader,
        SideBar
    }
}
</script>

<style>

</style>