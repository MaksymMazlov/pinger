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
const Registration = () => import('../core/registration.vue');
const Error = () => import('../core/error.vue');

const ResourcesPage = () => import('../page/ResourcesPage.vue');

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
            path: '/page/account/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/page/account/registration',
            name: 'Registration',
            component: Registration
        },
        {
            path: '/page/resource',
            name: 'AccountResource',
            component: ResourcesPage
        },
        {
            path: '/not-found',
            name: 'NotFound',
            component: Error
        }]
});