import Vue from 'vue';
import App from './app.vue';
import router from './router';
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome';
import '../content/scss/vendor.scss';
import * as storageConfig from './storage/StorageConfig';
import * as faConfig from './config/font-awesome-config'
import AccountService from "./service/AccountService";
import AccountResourceService from "./service/AccountResourceService";

router.beforeEach((to, from, next) => {
    if (!to.matched.length) {
        next('/not-found');
    }

    next();
});

const store = storageConfig.initVueXStore(Vue);
const accountService = new AccountService(store, router);
faConfig.initFortAwesome(Vue);

Vue.component('font-awesome-icon', FontAwesomeIcon);

new Vue({
    el: '#app',
    components: {App},
    template: '<App/>',
    router,
    provide: {
        accountService: () => accountService,
        accountResourceService: () => new AccountResourceService()
    },
    store
});