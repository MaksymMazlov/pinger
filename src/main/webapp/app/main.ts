import Vue from 'vue';
import App from './app.vue';
import router from './router';
import '../content/scss/vendor.scss';

router.beforeEach((to, from, next) => {
    if (!to.matched.length) {
        next('/not-found');
    }

    next();
});

new Vue({
    el: '#app',
    components: {App},
    template: '<App/>',
    router
});