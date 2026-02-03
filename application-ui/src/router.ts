import {createRouter, createWebHistory} from "vue-router";
import Index from "./components/Index.vue";
import About from "./components/About.vue";
import Login from "./components/Login.vue";

const routes = [
    {
        path: '/',
        name: 'home',
        component: Index
    },
    {
        path: '/about',
        name: 'about',
        component: About
    }, {
        path: '/login',
        name: 'login',
        component: Login
    }
]

var router = createRouter({

// history 模式（推荐，URL 干净无 #）
    history: createWebHistory(import.meta.env.BASE_URL),
    // hash 模式（老项目或服务器不支持 history 时用）
    // history: createWebHashHistory(),

    routes,

    scrollBehavior() {
        return {top: 0}
    }
});

export default router;