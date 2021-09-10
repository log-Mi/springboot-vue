import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'


const routes = [{
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/login',
    children: [{
        path: 'staff',
        name: 'Staff',
        component: () =>
            import ("@/views/Staff")
    }, {
        path: 'flower',
        name: 'Flower',
        component: () =>
            import ("@/views/Flower")
    }, {
        path: 'provInfo',
        name: 'ProvInfo',
        component: () =>
            import ("@/views/ProvInfo")
    }]
}, {
    path: '/login',
    name: 'Login',
    component: () =>
        import ("@/views/Login")
}, {
    path: '/register',
    name: 'Register',
    component: () =>
        import ("@/views/Register")
}]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router