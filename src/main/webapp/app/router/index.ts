import Vue from 'vue';
import Component from 'vue-class-component';
import Router from 'vue-router';

Component.registerHooks([
    'beforeRouteEnter',
    'beforeRouteLeave',
    'beforeRouteUpdate', // for vue-router 2.2+
]);

const Home = () => import('../core/home.vue');
const Login = () => import('../core/login.vue');
const Error = () => import('../core/error.vue');

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/page/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/not-found',
            name: 'NotFound',
            component: Error
        }]
});