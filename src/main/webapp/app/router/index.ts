import Vue from 'vue';
import Component from 'vue-class-component';
import Router from 'vue-router';
import {Authority} from "../model/Authority";

Component.registerHooks([
    'beforeRouteEnter',
    'beforeRouteLeave',
    'beforeRouteUpdate', // for vue-router 2.2+
]);

const Home = () => import('../core/home.vue');
const Login = () => import('../core/login.vue');
const Registration = () => import('../core/registration.vue');
const Error = () => import('../core/error.vue');
const Forbidden = () => import('../core/forbidden.vue');

const ResourcesPage = () => import('../page/ResourcesPage.vue');
const CreateResourcePage = () => import('../page/CreateResourcePage.vue');
const ResourceDetailsPage = () => import('../page/ResourceDetailsPage.vue');

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
            name: 'ResourcesPage',
            component: ResourcesPage,
            meta: { authorities: [Authority.USER] }
        },
        {
            path: '/page/resource/new',
            name: 'CreateResourcePage',
            component: CreateResourcePage,
            meta: { authorities: [Authority.USER] }
        },
        {
            path: '/page/resource/:resourceId/details',
            name: 'ResourceDetailsPage',
            component: ResourceDetailsPage,
            meta: { authorities: [Authority.USER] }
        },
        {
            path: '/not-found',
            name: 'NotFound',
            component: Error
        },
        {
            path: '/forbidden',
            name: 'Forbidden',
            component: Forbidden
        }]
});