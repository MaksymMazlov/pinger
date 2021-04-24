import Vue from 'vue';
import App from './app.vue';
import router from './router';
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome';
import '../content/scss/vendor.scss';
import * as storageConfig from './storage/StorageConfig';
import * as faConfig from './config/font-awesome-config'
import {setupAxiosInterceptors} from './config/axios-config';
import AccountService from "./service/AccountService";
import AccountResourceService from "./service/AccountResourceService";
import MonitoringEventService from "./service/MonitoringEventService";
import MonitoringByWeekService from "./service/MonitoringByWeekService";

const store = storageConfig.initVueXStore(Vue);
const accountService = new AccountService(store, router);

router.beforeEach((to, from, next) => {
    if (!to.matched.length) {
        next('/not-found');
    }

    if (to.meta && to.meta.authorities && to.meta.authorities.length > 0) {
        accountService.hasAnyAuthorityAndCheckAuth(to.meta.authorities).then(value => {
            if (!value) {
                sessionStorage.setItem('requested-url', to.fullPath);
                next('/forbidden');
            } else {
                next();
            }
        });
    } else {
        // no authorities, so just proceed
        next();
    }
});


faConfig.initFortAwesome(Vue);

Vue.component('font-awesome-icon', FontAwesomeIcon);

new Vue({
    el: '#app',
    components: {App},
    template: '<App/>',
    router,
    provide: {
        accountService: () => accountService,
        accountResourceService: () => new AccountResourceService(),
        monitoringEventService: () => new MonitoringEventService(),
        monitoringByWeekService: () => new MonitoringByWeekService()
    },
    store
});

setupAxiosInterceptors(() => console.log('Unauthorized!'));