import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入element
import ElementPlus from 'element-plus'
//引入element全部样式
import 'element-plus/dist/index.css'
import '@/assets/css/global.css'
//使用中文
import zhCn from 'element-plus/es/locale/lang/zh-cn'

createApp(App)
    .use(store)
    .use(router)
    .use(ElementPlus, {
        locale: zhCn,
        size: 'small'
    }).mount('#app');