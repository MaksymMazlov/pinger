import Vue from 'vue';
import App from './app.vue';
import router from './router';
import '../content/scss/vendor.scss';
import * as storageConfig from './storage/StorageConfig';
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