import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login";
import First from "../views/First";
import Home from "../views/Home";
import Register from "../views/Register";
import AirSearch from "../views/AirSearch";
import Charts from "../views/Charts";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    },
    {
        path: '/home',
        name: 'home',
        component: Home
    },
    {
        path: '/first',
        name: 'first',
        component: First
    },
    {
        path: '/search',
        name: 'search',
        component: AirSearch
    },
    {
        path: '/charts',
        name: 'charts',
        component: Charts
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    // to 将要访问的路径
    // from 从哪个路径跳转而来
    // next 是一个函数表示放行
    if (to.path === '/login' || to.path === '/register' ||to.path==='/charts') return next();
    // 获取token
    const tokenStr = window.sessionStorage.getItem('token')
    if (!tokenStr) return next('/login');
    next()
})

export default router
