/*
 * @Author: your name
 * @Date: 2020-07-10 15:55:00
 * @LastEditTime: 2020-07-10 20:13:38
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \Sword Finger Offerc:\Users\miaoz\Desktop\NEU Soft Big Data\AirLine\Big-data-based-Airline-Site\frontend\src\router\index.js
 */ 
import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login";
import First from "../views/First";
import Home from "../views/Home";
import Register from "../views/Register";
import AirSearch from "../views/AirSearch";
import Charts from "../views/Charts";
import OrderManagement from "../views/OrderManagement";
import UserInfo from "../views/UserInfo";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/charts'
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
        path: '/search',
        name: 'search',
        component: AirSearch
    },
    {
        path: '/orders',
        name: 'orders',
        component: OrderManagement
    },
    {
        path: '/user',
        name: 'user',
        component: UserInfo
    },

    {
        path: '/charts',
        name: 'charts',
        component: Charts
    },
    {
        path: '/first',
        name: 'first',
        component: First
    },
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
    if (to.path === '/login' || to.path === '/register' || to.path === '/charts') return next();
    // 获取token
    const tokenStr = window.sessionStorage.getItem('token')
    if (!tokenStr) return next('/charts');
    next()
})

export default router
