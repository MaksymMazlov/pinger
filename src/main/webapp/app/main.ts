import Vue from 'vue';
import App from './app.vue';
import router from './router';
import '../content/scss/vendor.scss';
new Vue({
    el: '#app',
    components: {App},
    template: '<App/>',
    router
});