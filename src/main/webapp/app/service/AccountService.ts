import axios from 'axios';
import {Store} from 'vuex';
import VueRouter from "vue-router";

export default class AccountService {
    constructor(private store: Store<any>,
                private router: VueRouter) {
    }

    public retrieveAccount(): Promise<boolean> {
        return new Promise(resolve => {
            axios
                .get('api/account')
                .then(response => {
                    this.store.commit('authenticate');
                    const account = response.data;
                    if (account) {
                        this.store.commit('authenticated', account);
                        if (sessionStorage.getItem('requested-url')) {
                            this.router.replace(sessionStorage.getItem('requested-url'));
                            sessionStorage.removeItem('requested-url');
                        }
                        this.router.push('/');
                    } else {
                        this.store.commit('logout');
                        this.router.push('/');
                        sessionStorage.removeItem('requested-url');
                    }
                    resolve(true);
                })
                .catch(() => {
                    this.store.commit('logout');
                    resolve(false);
                });
        });
    }
}